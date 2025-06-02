import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType

plugins {
    id("com.gradleup.shadow")
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

val minecraftVersion = project.properties["minecraft_version"] as String

configurations {
    create("common")
        "common" {
            isCanBeResolved = true
            isCanBeConsumed = false
        }

        compileClasspath.get().extendsFrom(configurations["common"])
        runtimeClasspath.get().extendsFrom(configurations["common"])
        getByName("developmentNeoForge").extendsFrom(configurations["common"])

        create("shadowBundle")
        "shadowBundle" {
            isCanBeResolved = true
            isCanBeConsumed = false
        }
    }

    loom {
        accessWidenerPath.set(project(":common").loom.accessWidenerPath)

//        neoForge {
//            convertAccessWideners.set(true)
//            extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)
//            mixinConfig("GenerationsCore-common.mixins.json")
//        }

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
        neoForge("net.neoforged:neoforge:${project.properties["neoforge_version"]}")

        modApi("dev.architectury:architectury-neoforge:${project.properties["architectury_version"]}")

        "common"(project(":common", "namedElements")) { isTransitive = false }
        "shadowBundle"(project(":common", "transformProductionNeoForge"))

        modImplementation(group = "earth.terrarium.common_storage_lib", name = "common-storage-lib-neoforge-1.21.1", version = "0.0.7")

        forgeRuntimeLibrary(
            "shadowBundle"(
                "gg.generations",
                "RareCandy",
                "${project.properties["rareCandy"]}"
            ) { isTransitive = false })!!

        modCompileOnly("mcp.mobius.waila:wthit-api:neo-${project.properties["WTHIT"]}")
        modRuntimeOnly("mcp.mobius.waila:wthit:neo-${project.properties["WTHIT"]}")
        modRuntimeOnly("lol.bai:badpackets:neo-${project.properties["badPackets"]}")

//        modLocalRuntime("curse.maven:spit-it-out-857141:4888754")

        modLocalRuntime("curse.maven:worldedit-225608:5830452")


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


tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("generations/gg/generations/core/generationscore/forge/datagen/**",
            "org/lwjgl/system//**",
            "org/lwjgl/BufferUtils.class",
            "org/lwjgl/CLongBuffer.class",
            "org/lwjgl/PointerBuffer.class",
            "org/lwjgl/Version\$BuildType.class",
            "org/lwjgl/Version.class",
            "org/lwjgl/VersionImpl.class",
            "org/lwjgl/package-info.class",
            "architectury.common.json",
            ".cache/**")
        configurations = listOf(project.configurations.getByName("shadowBundle"))
        relocate("org.lwjgl.assimp", "generations.gg.generations.shaded.assimp")
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
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
    setReleaseType(ReleaseType.RELEASE)
    projectVersion.set(project.version.toString() + "-${project.name}")
    displayName.set(base.archivesName.get() + "-${project.version}")
    changelog.set(projectDir.toPath().parent.resolve("CHANGELOG.md").toFile().readText())
    artifact.set(tasks.remapJar)
    setGameVersions(minecraftVersion)
    setLoaders(ModLoader.FORGE, ModLoader.NEOFORGE)
    setCurseEnvironment(CurseEnvironment.BOTH)
    setJavaVersions(JavaVersion.VERSION_17, JavaVersion.VERSION_18, JavaVersion.VERSION_19, JavaVersion.VERSION_20, JavaVersion.VERSION_21, JavaVersion.VERSION_22)
    val depends = mutableListOf(
        "architectury-api",
        "kotlin-for-forge",
        "cobblemon",
        "botarium",
    )
    curseDepends.required.set(depends)
    curseDepends.optional.set(mutableListOf("wthit-forge", "generations-structures"))
    modrinthDepends.required.set(depends)
    modrinthDepends.optional.set(mutableListOf("wthit", "generations-structures"))
}

private fun getPublishingCredentials(): Pair<String?, String?> {
    val curseForgeToken = (project.findProperty("curseforge_token") ?: System.getenv("CURSEFORGE_TOKEN") ?: "") as String?
    val modrinthToken = (project.findProperty("modrinth_token") ?: System.getenv("MODRINTH_TOKEN") ?: "") as String?
    return Pair(curseForgeToken, modrinthToken)
}