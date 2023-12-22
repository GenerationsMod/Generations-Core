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
    runs.create("datagen") {
        server()
        name("Data Generation")
        vmArg("-Dfabric-api.datagen")
        vmArg("-Dfabric-api.datagen.output-dir=${project(":fabric").file("src/main/generated/resources").absolutePath}")
        vmArg("-Dfabric-api.datagen.modid=generations_core")

        runDir("build/datagen")
    }
}

repositories {
    maven("https://maven.terraformersmc.com/releases/")
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
    implementation("shadowCommon"("de.javagl", "jgltf-model", "${project.properties["rareCandyJgltfModel"]}"))!!
    implementation("shadowCommon"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
    implementation("shadowCommon"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
    implementation("shadowCommon"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!
    implementation("shadowCommon"("com.thebombzen:jxlatte:1.1.0")!!)
    implementation("shadowCommon"("com.github.Chocohead:Fabric-ASM:v2.3")!!)

    modApi("earth.terrarium:botarium-fabric-$minecraftVersion:${project.properties["botarium_version"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")

    //Cobblemon
    modApi("com.cobblemon:fabric:${project.properties["cobblemon_version"]}")
    modApi("net.fabricmc:fabric-language-kotlin:1.10.15+kotlin.1.9.21")
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
        curseforge(project.properties["curseforge_token"].toString())
        modrinth(project.properties["modrinth_token"].toString())
        github(project.properties["github_token"].toString())
    }

    curseID.set("860936")
    modrinthID.set("e1GvDbBX")
    githubRepo.set("https://github.com/GenerationsMod/Generations-Core")
    setReleaseType(ReleaseType.BETA)
    version.set(project.version.toString())
    displayName.set("$jarName-${version.get()}")
    changelog.set("test changelog")
    artifact.set(tasks.remapJar)
    setGameVersions(minecraftVersion)
    setLoaders(ModLoader.FABRIC, ModLoader.QUILT)
    setCurseEnvironment(CurseEnvironment.BOTH)
    val depends = mutableListOf(
        "fabric-api",
        "fabric-language-kotlin",
        "architectury-api",
        "cobblemon",
        "botarium",
    )
    curseDepends.required.set(depends)
    modrinthDepends.required.set(depends)
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
                username = project.properties["repoLogin"]?.toString()
                password = project.properties["repoPassword"]?.toString()
            }
        }
    }
}