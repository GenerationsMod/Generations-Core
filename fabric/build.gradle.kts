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

loom {
    silentMojangMappingsLicense()
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

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
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")
    modApi("net.fabricmc.fabric-api:fabric-api:${rootProject.properties["fabric_api_version"]}")
    modApi("dev.architectury:architectury-fabric:${rootProject.properties["architectury_version"]}")
    compileOnly("org.jetbrains:annotations:24.0.1")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionFabric")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${rootProject.properties["devauth_version"]}")

    shadow(implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false})

    shadow(implementation("org.tukaani:xz:${project.properties["rareCandyXZ"]}")!!)
    shadow(implementation("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}")!!)
    shadow(implementation("de.javagl:jgltf-model:${project.properties["rareCandyJgltfModel"]}")!!)
    shadow(implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){ isTransitive = false })
    shadow(implementation("org.msgpack:msgpack-core:${project.properties["rareCandyMsgPackCore"]}")!!)
    shadow(implementation("com.google.flatbuffers:flatbuffers-java:23.3.3")!!)

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
        isZip64 = true
        configurations {
            project.configurations.getByName("shadowCommon")
        }
        archiveClassifier.set("dev-shadow")
        dependencies {
            exclude(dependency("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}"))
            exclude(dependency("org.lwjgl:lwjgl:${project.properties["lwjgl"]}"))
            exclude(dependency("asm:asm:${project.properties["asm"]}"))
            exclude(dependency("asm:asm-commons:${project.properties["asmCommons"]}"))
            exclude(dependency("asm:asm-tree:${project.properties["asmTree"]}"))
        }
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

java {
    if (JavaVersion.current() < JavaVersion.VERSION_17) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
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