import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType
import kotlin.io.path.absolute

plugins {
    id("com.gradleup.shadow")
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

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":common", "transformProductionFabric"))

    modLocalRuntime("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")

    implementation("shadowBundle"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}") {isTransitive = false})!!

    implementation("shadowBundle"("com.github.Chocohead:Fabric-ASM:v2.3")!!)

//    modImplementation(group = "earth.terrarium.common_storage_lib", name = "common-storage-lib-fabric-1.21.1", version = "0.0.7")

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
//    modRuntimeOnly("com.jozufozu.flywheel:flywheel-fabric-$minecraftVersion:${project.properties["flywheel_fabric_version"]}")
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
    setReleaseType(ReleaseType.RELEASE)
    projectVersion.set(project.version.toString() + "-${project.name}")
    displayName.set(base.archivesName.get() + "-${project.version}")
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
    curseDepends.optional.set(mutableListOf("wthit", "generations-structures"))
    modrinthDepends.required.set(depends)
    modrinthDepends.optional.set(mutableListOf("wthit", "generations-structures"))
}

private fun getPublishingCredentials(): Pair<String?, String?> {
    val curseForgeToken = (project.findProperty("curseforge_token") ?: System.getenv("CURSEFORGE_TOKEN") ?: "") as String?
    val modrinthToken = (project.findProperty("modrinth_token") ?: System.getenv("MODRINTH_TOKEN") ?: "") as String?
    return Pair(curseForgeToken, modrinthToken)
}

tasks.register("runMoveDataGenResourcesToCommon") {
    group = "loom"
    doLast {
        var root = projectDir.toPath().absolute().parent

        val fabricModels = root.resolve("fabric/src/main/generated/").toFile()
        val commonModels = root.resolve("common/src/main/generated/resources/").toFile()

        commonModels.mkdirs()

        fabricModels.copyRecursively(commonModels, overwrite = true)
        fabricModels.deleteRecursively() // Remove the old models folder
        println("Fabric DataGen resources to common.")
    }
}
