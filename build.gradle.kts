import net.msrandom.stubs.GenerateStubApi

plugins {
    id("earth.terrarium.cloche") version "0.10.13"
    kotlin("jvm") version "1.9.22"
}

repositories {
    cloche.librariesMinecraft()

    mavenCentral()

    cloche {
        main()

        mavenFabric()
        mavenNeoforged()
        mavenNeoforgedMeta()
    }

    mavenCentral()
    mavenLocal()
    maven("https://repo1.maven.org/maven2")
    maven("https://jitpack.io")
    maven("https://maven.generations.gg/snapshots")
    maven("https://maven.generations.gg/releases")
    maven("https://generationsmaven.firstdark.dev/snapshots")
    maven("https://generationsmaven.firstdark.dev/releases")
    maven("https://maven.shedaniel.me")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
    maven("https://cursemaven.com").content { includeGroup("curse.maven") }
    maven("https://api.modrinth.com/maven").content { includeGroup("maven.modrinth") }
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://maven.parchmentmc.org")
    maven("https://maven2.bai.lol").content {
        includeGroup("lol.bai")
        includeGroup("mcp.mobius.waila")
    }
    maven("https://maven.tterrag.com/")
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")
    maven("https://maven.neoforged.net/releases")
    maven("https://jm.gserv.me/repository/maven-public/")
    maven(
        // location of the maven that hosts JEI files since January 2023
        url = "https://maven.blamejared.com/"
    )
}

version = project.properties["mod_version"] as String
group = project.properties["maven_group"] as String

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.1")
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")
}

cloche {
    minecraftVersion = properties["minecraft_version"] as String

    mappings {
        official()
        parchment(properties["parchment"] as String)
    }

    metadata {
        modId = "generations_core"
        name = "Generations-Core"
        description = "Core mod for Generations. Required for all other Generations mods.\n" +
                "Contains Blocks, Items, Entities, and other things that are used by all other Generations mods."
        author("Joseph T. McQuigg (JT122406)")
        author("Waterpicker")
        author("Stampede")
        contributor("Greg_lor")
        contributor("Hydos")
        contributor("Dysthmic")
        license = "GNU GPL 3.0"
        issues = "https://github.com/GenerationsMod/Generations-Core/issues"
        sources = "https://github.com/GenerationsMod/Generations-Core/issues"
        icon = "generations.png"
    }

    common {
        dependencies {
            modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")

            modImplementation("earth.terrarium.common_storage_lib:common-storage-lib-common-1.21.1:0.0.7")

            implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}

            modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
            modCompileOnly(fileTree(mapOf("dir" to "libs", "include" to "*.jar")))

            modCompileOnly("me.shedaniel:RoughlyEnoughItems-api:${project.properties["rei"]}")
            modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin:${project.properties["rei"]}")

            modCompileOnlyApi(module("mezz.jei", "jei-${project.properties["minecraft_version"]}-lib", "${project.properties["jei"]}"))
            modCompileOnlyApi("mezz.jei:jei-${project.properties["minecraft_version"]}-common-api:${project.properties["jei"]}")
            modRuntimeOnly("mezz.jei:jei-${project.properties["minecraft_version"]}-common:${project.properties["jei"]}")
            modCompileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")

//            modImplementation("net.impactdev.impactor.api:economy:5.1.1+1.20.1")
            //Cobblemon
        }
    }

    neoforge {
        loaderVersion = properties["neoforge_version"] as String

        metadata {
            modLoader = "kotlinforforge"
            loaderVersion("[5.3)")
            dependency {
                modId = "neoforge"
                required = true
                version("[21.1.84,)")
            }
            dependency {
                modId = "minecraft"
                required = true
                version("[1.21.1)")
            }
            dependency {
                modId="common_storage_lib"
                required=true
                version("[0.0.7,)")
            }
            dependency {
                modId="wthit"
                required=false
                version("[8.15.0,)")
            }
        }

        dependencies {
            modImplementation("earth.terrarium.common_storage_lib:common-storage-lib-neoforge-1.21.1:0.0.7")

            include("gg.generations:RareCandy:${project.properties["rareCandy"]}")

            modCompileOnly("mcp.mobius.waila:wthit-api:neo-${project.properties["WTHIT"]}")
            modRuntimeOnly("mcp.mobius.waila:wthit:neo-${project.properties["WTHIT"]}")
            modRuntimeOnly("lol.bai:badpackets:neo-${project.properties["badPackets"]}")

//        modLocalRuntime("curse.maven:spit-it-out-857141:4888754")

            modImplementation("curse.maven:worldedit-225608:5830452")


//    modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-forge:${project.properties["rei"]}")
            modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-neoforge:${project.properties["rei"]}")
            modCompileOnlyApi("mezz.jei:jei-${project.properties["minecraft_version"]}-neoforge-api:${project.properties["jei"]}")

            when (project.properties["recipe_viewer"]) {
                "rei" -> modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-neoforge:${project.properties["rei"]}")
                "jei" -> modRuntimeOnly("mezz.jei:jei-${project.properties["minecraft_version"]}-neoforge:${project.properties["jei"]}")
            }

            //Cobblemon
            implementation("thedarkcolour:kotlinforforge-neoforge:5.8.0")
            modImplementation("com.cobblemon:neoforge:${project.properties["cobblemon_version"]}")

            implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.5")
            include("org.jetbrains.kotlinx:kotlinx-io-core:0.3.5")
        }

        runs {
            server()
            client()
            data()
        }
    }

    fabric {
        metadata {
            entrypoint("preLaunch") {
                adapter = "kotlin"
                value = "generations.gg.generations.core.generationscore.fabric.GenerationsCoreFabric"
            }
            entrypoint("main") {
                adapter = "kotlin"
                value = "generations.gg.generations.core.generationscore.fabric.GenerationsCoreFabric"
            }
            entrypoint("client") {
                adapter = "kotlin"
                value = "generations.gg.generations.core.generationscore.fabric.client.GenerationsCoreClientFabric"
            }
            entrypoint("fabric-datagen", "generations.gg.generations.core.generationscore.fabric.datagen.DataGen")

            entrypoint("rei_client", "generations.gg.generations.core.generationscore.common.compat.rei.ReiCompatClient")
            entrypoint("jei_mod_plugin", "generations.gg.generations.core.generationscore.common.compat.jei.GenerationreJeiCompat")
            mixins.from(
                "GenerationsCore.mixins.json",
                "GenerationsCore-common.mixins.json"
            )

            dependencies {
                modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")


                include("gg.generations:RareCandy:${project.properties["rareCandy"]}")

                implementation("com.github.Chocohead:Fabric-ASM:v2.3")
                include("com.github.Chocohead:Fabric-ASM:v2.3")

                modImplementation("earth.terrarium.common_storage_lib:common-storage-lib-fabric-1.21.1:0.0.7")

                modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
                modRuntimeOnly("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")

                modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:${project.properties["rei"]}")

                modCompileOnlyApi("mezz.jei:jei-${project.properties["minecraft_version"]}-fabric-api:${project.properties["jei"]}")


                when(project.properties["recipe_viewer"]) {
                    "rei" -> modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:${project.properties["rei"]}")
                    "jei" -> modRuntimeOnly("mezz.jei:jei-${project.properties["minecraft_version"]}-fabric:${project.properties["jei"]}")
                }

                //Cobblemon
                modApi("com.cobblemon:fabric:${project.properties["cobblemon_version"]}")
                modApi("net.fabricmc:fabric-language-kotlin:1.12.0+kotlin.2.0.10")
            }

            dependency {
                modId = "fabricloader"
                version(">=0.15.3")
            }
            dependency {
                modId = "minecraft"
                version(">=1.21.1")
            }
            dependency {
                modId = "cobblemon"
                version("=1.6.1+1.21.1")
            }
            dependency {
                modId = "common_storage_lib"
                version(">=0.0.7")
            }

            custom(
                "custom" to mutableMapOf(
                    "icon" to mutableMapOf(
                        "image" to "generations.png"
                    )
                )
            )
        }

        data {

        }

        includedClient()

        loaderVersion = stringProp("fabric_loader_version")

        dependencies {
            fabricApi(stringProp("fabric_api_version")) // Optional
        }

//        "suggests": { TODO: COnvert this once cloche supports.
//            "wthit": ">=8.15.0"
//        }

        runs {
            server()
            client()
            data()
        }
    }
}

fun stringProp(name: String): String = properties[name] as String

kotlin {
    jvmToolchain(21)
}

tasks.named(name = "createCommonApiStub", type = GenerateStubApi::class) {
    excludes.add("com.cobblemon")
    excludes.add("generations.gg.generations.core.generationscore.common.world.recipe")
}

tasks.register("relocateKotlinFiles") {
    group = "maintenance"
    description = "Moves Kotlin files from java/ to kotlin/ for each source set."

    doLast {
        val sourceSets = listOf("common", "fabric", "neoforge")
        val root = project.file("src")

        sourceSets.forEach { name ->
            val javaDir = File(root, "$name/main/java")
            val kotlinDir = File(root, "$name/main/kotlin")

            if (!javaDir.exists()) {
                println("Skipping $name: no java dir")
                return@forEach
            }

            javaDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { ktFile ->
                val relative = ktFile.relativeTo(javaDir)
                val target = File(kotlinDir, relative.path)

                println("Moving: ${ktFile.absolutePath} -> ${target.absolutePath}")
                target.parentFile.mkdirs()
                ktFile.copyTo(target, overwrite = true)
                ktFile.delete()
            }
        }
    }
}

tasks.register("cleanEmptyJavaDirs") {
    group = "maintenance"
    description = "Recursively deletes empty directories in java source folders."

    doLast {
        val sourceRoots = listOf("common", "fabric", "neoforge").map { File("src/$it/main/java") }

        sourceRoots.forEach { root ->
            if (!root.exists()) {
                println("Skipping ${root.path}: does not exist")
                return@forEach
            }

            // Walk bottom-up to remove nested empty directories
            root.walkBottomUp().filter { it.isDirectory && it.listFiles()?.isEmpty() == true }.forEach { dir ->
                println("Deleting empty directory: ${dir.absolutePath}")
                dir.delete()
            }
        }
    }
}

tasks.register("printSourceDirs") {
    group = "diagnostics"
    description = "Prints all sourceSets and their java.srcDirs and resources.srcDirs"

    doLast {
        project.extensions.findByName("sourceSets")?.let { sourceSets ->
            val set = sourceSets as org.gradle.api.tasks.SourceSetContainer
            set.forEach { ss ->
                println("SourceSet: ${ss.name}")
                println("  java dirs:")
                ss.allJava.srcDirs.forEach { println("    - ${it.absolutePath}") }
                println("  resources dirs:")
                ss.resources.srcDirs.forEach { println("    - ${it.absolutePath}") }
            }
        } ?: println("No sourceSets found in this project.")
    }
}