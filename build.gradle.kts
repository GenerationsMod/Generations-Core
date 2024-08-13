import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("architectury-plugin") version "3.4-SNAPSHOT"
    kotlin("jvm") version ("2.0.10")
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
    java
    idea
}

val minecraftVersion = project.properties["minecraft_version"] as String

architectury.minecraft = minecraftVersion

subprojects {
    apply(plugin = "dev.architectury.loom")
    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://repo1.maven.org/maven2")
        maven("https://jitpack.io")
        maven("https://maven.generations.gg/snapshots")
        maven("https://maven.generations.gg/releases")
        maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
        maven("https://cursemaven.com").content { includeGroup("curse.maven") }
        maven("https://maven.impactdev.net/repository/development/")
        maven("https://maven.parchmentmc.org")
        maven("https://maven2.bai.lol").content {
            includeGroup("lol.bai")
            includeGroup("mcp.mobius.waila")
        }
        maven("https://maven.tterrag.com/")
        maven("https://nexus.resourcefulbees.com/repository/maven-public/")
        maven("https://maven.neoforged.net/releases")
    }

    @Suppress("UnstableApiUsage")
    dependencies {
        "minecraft"("com.mojang:minecraft:$minecraftVersion")
        "mappings"(loom.layered{
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-$minecraftVersion:${project.properties["parchment"]}@zip")
        })

        compileOnly("org.jetbrains:annotations:24.1.0")
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    version = project.properties["mod_version"] as String
    group = project.properties["maven_group"] as String
    base.archivesName.set(project.properties["archives_base_name"] as String)

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    java.withSourcesJar()
}

kotlin.jvmToolchain(17)
