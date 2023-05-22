plugins {
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.2-SNAPSHOT" apply false
    idea
    java
}

val minecraftVersion = project.properties["minecraft_version"] as String

architectury.minecraft = minecraftVersion

subprojects {
    apply(plugin = "dev.architectury.loom")

    dependencies {
        "minecraft"("com.mojang:minecraft:$minecraftVersion")
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")
    apply(plugin = "idea")

    version = rootProject.properties["mod_version"] as String
    group = rootProject.properties["maven_group"] as String

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    configure<JavaPluginExtension> {
        withSourcesJar()
    }
}
