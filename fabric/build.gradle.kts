import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val minecraftVersion = project.properties["minecraft_version"] as String
val jarName = base.archivesName.get() + "-Fabric"

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentFabric").extendsFrom(configurations["common"])
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
    mixin.useLegacyMixinAp.set(false)
}

fabricApi.configureDataGeneration()

repositories {
    maven("https://maven.terraformersmc.com/releases/")
    mavenCentral()
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    modApi("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_api_version"]}+$minecraftVersion")
    modApi("dev.architectury:architectury-fabric:${project.properties["architectury_version"]}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionFabric")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")

    implementation("shadowCommon"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!
    implementation("shadowCommon"("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!
    implementation("shadowCommon"("org.lwjgl:lwjgl-assimp:3.3.1")!!)
    implementation("shadowCommon"("org.lwjgl:lwjgl-assimp:3.3.1:natives-windows")!!)
    implementation("shadowCommon"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
    implementation("shadowCommon"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
    implementation("shadowCommon"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!
    implementation("shadowCommon"("com.thebombzen:jxlatte:1.1.0")!!)
    implementation("shadowCommon"("com.github.Chocohead:Fabric-ASM:v2.3")!!)

    modApi("earth.terrarium.botarium:botarium-fabric-$minecraftVersion:${project.properties["botarium_version"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")

    //Cobblemon
    modApi("com.cobblemon:fabric:${project.properties["cobblemon_version"]}")
    modApi("net.fabricmc:fabric-language-kotlin:1.10.19+kotlin.1.9.23")
    modRuntimeOnly("com.jozufozu.flywheel:flywheel-fabric-$minecraftVersion:${project.properties["flywheel_fabric_version"]}")
}

tasks {
    base.archivesName.set(jarName)
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }

        from(rootProject.file("common/src/main/resources")) {
            include("**/**")
            duplicatesStrategy = DuplicatesStrategy.WARN
        }
    }

    shadowJar {
        exclude(mutableListOf(
            "generations/gg/generations/core/generationscore/fabric/datagen/**",
            "data/forge/**",
            "architectury.common.json",
            ".cache"
        ))
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }

    jar.get().archiveClassifier.set("dev")

    sourcesJar {
        val commonSources = project(":common").tasks.sourcesJar
        dependsOn(commonSources)
        from(commonSources.get().archiveFile.map { zipTree(it) })
    }
}

publisher {
    apiKeys {
        curseforge(getPublishingCredentials().first)
        modrinth(getPublishingCredentials().second)
        github(project.properties["github_token"].toString())
    }

    curseID.set("860936")
    modrinthID.set("AxvRzJ70")
    githubRepo.set("https://github.com/GenerationsMod/Generations-Core")
    setReleaseType(ReleaseType.BETA)
    version.set(project.version.toString())
    displayName.set("$jarName-${version.get()}")
    changelog.set("")
    artifact.set(tasks.remapJar)
    setGameVersions(minecraftVersion)
    setLoaders(ModLoader.FABRIC, ModLoader.QUILT)
    setCurseEnvironment(CurseEnvironment.BOTH)
    setJavaVersions("java 17", "java 18")
    val depends = mutableListOf(
        "fabric-api",
        "fabric-language-kotlin",
        "architectury-api",
        "cobblemon",
        "botarium",
    )
    curseDepends.required.set(depends)
    curseDepends.optional.set(mutableListOf("wthit"))
    modrinthDepends.required.set(depends)
    modrinthDepends.optional.set(mutableListOf("wthit"))
}

components {
    java.run {
        if (this is AdhocComponentWithVariants)
            withVariantsFromConfiguration(project.configurations.shadowRuntimeElements.get()) { skip() }
    }
}

publishing {
    publications.create<MavenPublication>("mavenFabric") {
        artifactId = jarName
        from(components["java"])
    }

    repositories {
        mavenLocal()
        maven {
            val releasesRepoUrl = "https://maven.generations.gg/releases"
            val snapshotsRepoUrl = "https://maven.generations.gg/snapshots"
            url = uri(if (project.version.toString().endsWith("SNAPSHOT") || project.version.toString().startsWith("0")) snapshotsRepoUrl else releasesRepoUrl)
            name = "Generations-Repo"
            credentials {
                username = getGensCredentials().first
                password = getGensCredentials().second
            }
        }
    }
}

private fun getGensCredentials(): Pair<String?, String?> {
    val username = (project.findProperty("gensUsername") ?: System.getenv("GENS_USERNAME") ?: "") as String?
    val password = (project.findProperty("gensPassword") ?: System.getenv("GENS_PASSWORD") ?: "") as String?
    return Pair(username, password)
}

private fun getPublishingCredentials(): Pair<String?, String?> {
    val curseForgeToken = (project.findProperty("curseforge_token") ?: System.getenv("CURSEFORGE_TOKEN") ?: "") as String?
    val modrinthToken = (project.findProperty("modrinth_token") ?: System.getenv("MODRINTH_TOKEN") ?: "") as String?
    return Pair(curseForgeToken, modrinthToken)
}