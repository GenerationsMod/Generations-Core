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
    mavenCentral()
    mavenLocal()
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
    compileOnly("org.jetbrains:annotations:24.0.1")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionForge")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-forge-latest:${project.properties["devauth_version"]}")

    modApi("earth.terrarium:botarium-forge-${minecraftVersion}:${project.properties["botarium_version"]}")

    shadow(forgeRuntimeLibrary(implementation("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!)

    shadow(forgeRuntimeLibrary(implementation("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!)
    shadow(forgeRuntimeLibrary(implementation("org.apache.commons", "commons-compress", "${project.properties["rareCandyCommonCompress"]}"))!!)
    shadow(forgeRuntimeLibrary(implementation("de.javagl", "jgltf-model", "${project.properties["rareCandyJgltfModel"]}"))!!)
    shadow(forgeRuntimeLibrary(implementation("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!)
    shadow(forgeRuntimeLibrary(implementation("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!)
    shadow(forgeRuntimeLibrary(implementation("com.google.flatbuffers", "flatbuffers-java", "23.3.3"))!!)


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
            artifactId = "${project.properties["archives_base_name"]}" + "-Forge-" + rootProject.version
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