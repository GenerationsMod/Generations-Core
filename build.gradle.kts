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

    /*
    var archivesBaseName = rootProject.archives_base_name
    var version = rootProject.mod_version
    var group = rootProject.maven_group

     */

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    configure<JavaPluginExtension> {
        withSourcesJar()
    }
}
