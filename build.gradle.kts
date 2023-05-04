plugins {
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.1-SNAPSHOT" apply false
    idea
}

architectury {
    minecraft = rootProject.properties["minecraft_version"] as String
}

subprojects {
    apply(plugin = "dev.architectury.loom")
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
