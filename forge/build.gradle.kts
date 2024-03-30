import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
}

architectury {
    platformSetupLoomIde()
    forge()
}

val minecraftVersion = project.properties["minecraft_version"] as String
val jarName = base.archivesName.get() + "-Forge"

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentForge").extendsFrom(configurations["common"])
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)

    forge {
        convertAccessWideners.set(true)
        extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)
        mixinConfig("GenerationsCore-common.mixins.json")
    }

    runs.create("data") {
            data()
            programArgs("--all", "--mod", "generations_core")
            programArgs("--output", project(":common").file("src/main/generated/resources").absolutePath)
            programArgs("--existing", project(":common").file("src/main/resources").absolutePath)
    }
}

repositories {
    maven("https://thedarkcolour.github.io/KotlinForForge/")
    mavenCentral()
}

dependencies {
    forge("net.minecraftforge:forge:$minecraftVersion-${project.properties["forge_version"]}")
    //forge("net.neoforged:forge:$minecraftVersion-${project.properties["neoforge_version"]}")
    modApi("dev.architectury:architectury-forge:${project.properties["architectury_version"]}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionForge")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-forge-latest:${project.properties["devauth_version"]}")

    modApi("earth.terrarium.botarium:botarium-forge-$minecraftVersion:${project.properties["botarium_version"]}")!!

    forgeRuntimeLibrary("shadowCommon"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!
    forgeRuntimeLibrary("shadowCommon"("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!
    forgeRuntimeLibrary("org.lwjgl:lwjgl-assimp:3.3.2")
    forgeRuntimeLibrary("org.lwjgl:lwjgl-assimp:3.3.2:natives-windows")
    forgeRuntimeLibrary("shadowCommon"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
    forgeRuntimeLibrary("shadowCommon"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("com.thebombzen:jxlatte:1.1.0")!!)

    modCompileOnly("mcp.mobius.waila:wthit-api:forge-${project.properties["WTHIT"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:forge-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:forge-${project.properties["badPackets"]}")

    modRuntimeOnly("curse.maven:spit-it-out-857141:4888754")

    modRuntimeOnly("curse.maven:worldedit-225608:4586218")


    //Cobblemon
    implementation("thedarkcolour:kotlinforforge:4.10.0")
    modImplementation("com.cobblemon:forge:${project.properties["cobblemon_version"]}")
}

tasks {
    base.archivesName.set(jarName)
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("fabric.mod.json",
            "generations/gg/generations/core/generationscore/forge/datagen/**",
            "architectury.common.json",
            ".cache/**")
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
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
    setLoaders(ModLoader.FORGE, ModLoader.NEOFORGE)
    setCurseEnvironment(CurseEnvironment.BOTH)
    setJavaVersions("17", "18")
    val depends = mutableListOf(
        "architectury-api",
        "kotlin-for-forge",
        "cobblemon",
        "botarium",
    )
    curseDepends.required.set(depends)
    curseDepends.optional.set(mutableListOf("wthit-forge"))
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
    publications.create<MavenPublication>("mavenForge") {
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