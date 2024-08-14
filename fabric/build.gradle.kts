import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType

plugins {
    id("com.github.johnrengelman.shadow")
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val minecraftVersion = project.properties["minecraft_version"] as String

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

configurations {
    create("common")
    "common" {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    create("shadowBundle")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentFabric").extendsFrom(configurations["common"])
    "shadowBundle" {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

loom.accessWidenerPath.set(project(":common").loom.accessWidenerPath)

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
    "shadowBundle"(project(":common", "transformProductionFabric"))

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")

    implementation("shadowBundle"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!
//    implementation("shadowBundle"("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!
//    implementation("shadowBundle"("org.lwjgl:lwjgl-assimp:3.3.2")!!)
//    implementation("shadowBundle"("org.lwjgl:lwjgl-assimp:3.3.2:natives-windows")!!)
//    implementation("shadowBundle"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
//    implementation("shadowBundle"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
//    implementation("shadowBundle"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!

    implementation("shadowBundle"("com.github.Chocohead:Fabric-ASM:v2.3")!!)

    modApi("earth.terrarium.botarium:botarium-fabric-$minecraftVersion:${project.properties["botarium_version"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")
    implementation("shadowBundle"("com.github.ben-manes.caffeine:caffeine:3.1.8")!!)

    //Cobblemon
    modApi("com.cobblemon:fabric:${project.properties["cobblemon_version"]}")
    modApi("net.fabricmc:fabric-language-kotlin:1.12.0+kotlin.2.0.10")
    modRuntimeOnly("com.jozufozu.flywheel:flywheel-fabric-$minecraftVersion:${project.properties["flywheel_fabric_version"]}")
}

tasks {
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
            "data/generations_core/forge/**",
            "architectury.common.json",
            ".cache/**"
        ))
        configurations = listOf(project.configurations.getByName("shadowBundle"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
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
    //githubRepo.set("https:github.com/GenerationsMod/Generations-Core")
    setReleaseType(ReleaseType.BETA)
    projectVersion.set(project.version.toString() + "-${project.name}")
    displayName.set(base.archivesName.get() + "-Fabric")
    changelog.set(projectDir.toPath().parent.resolve("CHANGELOG.md").toFile().readText())
    artifact.set(tasks.remapJar)
    setGameVersions(minecraftVersion)
    setLoaders(ModLoader.FABRIC, ModLoader.QUILT)
    setCurseEnvironment(CurseEnvironment.BOTH)
    setJavaVersions(JavaVersion.VERSION_17, JavaVersion.VERSION_18, JavaVersion.VERSION_19, JavaVersion.VERSION_20, JavaVersion.VERSION_21, JavaVersion.VERSION_22)
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

private fun getPublishingCredentials(): Pair<String?, String?> {
    val curseForgeToken = (project.findProperty("curseforge_token") ?: System.getenv("CURSEFORGE_TOKEN") ?: "") as String?
    val modrinthToken = (project.findProperty("modrinth_token") ?: System.getenv("MODRINTH_TOKEN") ?: "") as String?
    return Pair(curseForgeToken, modrinthToken)
}