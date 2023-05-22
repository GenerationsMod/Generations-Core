plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val minecraftVersion = project.properties["minecraft_version"] as String

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentFabric").extendsFrom(configurations["common"])
}

loom.accessWidenerPath.set(project(":common").loom.accessWidenerPath)

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")
    maven("https://maven.bai.lol")
    maven("https://jitpack.io")
    maven("https://maven.generations.gg/snapshots")
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")
    modApi("net.fabricmc.fabric-api:fabric-api:${rootProject.properties["fabric_api_version"]}")
    modApi("dev.architectury:architectury-fabric:${rootProject.properties["architectury_version"]}")
    compileOnly("org.jetbrains:annotations:24.0.1")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionFabric")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${rootProject.properties["devauth_version"]}")

    include(implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false})

    include(implementation("org.tukaani:xz:${project.properties["rareCandyXZ"]}")!!)
    include(implementation("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}")!!)
    include(implementation("de.javagl:jgltf-model:${project.properties["rareCandyJgltfModel"]}")!!)
    include(implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){ isTransitive = false })
    include(implementation("org.msgpack:msgpack-core:${project.properties["rareCandyMsgPackCore"]}")!!)
    include(implementation("com.google.flatbuffers:flatbuffers-java:23.3.3")!!)

    modImplementation("earth.terrarium:botarium-fabric-${minecraftVersion}:${rootProject.properties["botarium_version"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:fabric-0.2.0")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
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
            artifactId = "${project.properties["archives_base_name"]}" + "-Fabric-" + rootProject.version
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            val releasesRepoUrl = "https://maven.generations.gg/releases"
            val snapshotsRepoUrl = "https://maven.generations.gg/snapshots"
            url = uri(if (rootProject.version.toString().endsWith("SNAPSHOT") || rootProject.version.toString().startsWith("0")) snapshotsRepoUrl else releasesRepoUrl)
            name = "Generations-Repo"
            credentials {
                username = project.properties["repoLogin"]?.toString()
                password = project.properties["repoPassword"]?.toString()
            }
        }
    }
}