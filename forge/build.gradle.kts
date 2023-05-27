plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    forge()
}

val minecraftVersion = project.properties["minecraft_version"] as String

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
        mixinConfig("GenerationsCore.mixins.json")
    }

    runs {
        create("data") {
            data()
            programArgs("--all", "--mod", "generations_core")
            programArgs("--output", project(":common").file("src/main/generated/resources").absolutePath)
            programArgs("--existing", project(":common").file("src/main/resources").absolutePath)
        }
    }
}

repositories {
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
    maven("https://cursemaven.com") {
        content {
            includeGroup("curse.maven")
        }
    }
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")
    maven("https://jitpack.io") // BinarySMD
    maven("https://maven.generations.gg/snapshots")
    maven("https://maven.bai.lol")
}

dependencies {
    forge("net.minecraftforge:forge:${project.properties["forge_version"]}")
    modApi("dev.architectury:architectury-forge:${project.properties["architectury_version"]}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionForge")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-forge-latest:${project.properties["devauth_version"]}")

    modApi(include("earth.terrarium:botarium-forge-${minecraftVersion}:${project.properties["botarium_version"]}")!!)

    forgeRuntimeLibrary("shadowCommon"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!
    forgeRuntimeLibrary("shadowCommon"("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!
//    forgeRuntimeLibrary(include("org.apache.commons", "commons-compress", "${project.properties["rareCandyCommonCompress"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("de.javagl", "jgltf-model", "${project.properties["rareCandyJgltfModel"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
    forgeRuntimeLibrary("shadowCommon"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!
    forgeRuntimeLibrary("shadowCommon"("com.fasterxml.jackson.core:jackson-databind:2.15.1")!!)
    forgeRuntimeLibrary("shadowCommon"("com.fasterxml.jackson.core:jackson-annotations:2.15.1")!!)
    forgeRuntimeLibrary("shadowCommon"("com.fasterxml.jackson.core:jackson-core:2.15.1")!!)

    modCompileOnly("mcp.mobius.waila:wthit-api:forge-${project.properties["WTHIT"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:forge-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:forge-${project.properties["badPackets"]}")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("fabric.mod.json")
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

components {
    java.run {
        if (this is AdhocComponentWithVariants)
            withVariantsFromConfiguration(project.configurations.shadowRuntimeElements.get()) { skip() }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenCommon") {
            artifactId = "${project.properties["archives_base_name"]}" + "-Forge"
            from(components["java"])
        }
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