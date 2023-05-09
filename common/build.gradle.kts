
loom.silentMojangMappingsLicense()

repositories {
    mavenCentral()
    maven("https://jitpack.io") // BinarySMD
    maven("https://maven.pixelmongenerations.com/repository/maven-private/") {

        credentials {
            username = project.properties["repoLogin"]?.toString() ?: findProperty("REPO_LOGIN").toString()
            password = project.properties["repoPassword"]?.toString() ?: findProperty("REPO_PASSWORD").toString()
        }
    }
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")

    mavenCentral()
    maven("https://maven.thepokecraftmod.com/releases")
    maven("https://maven.parchmentmc.org")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

sourceSets {
    main {
        resources.srcDir("src/main/generated/resources")
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${rootProject.properties["minecraft_version"]}")
    mappings(loom.officialMojangMappings())
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")
    // Remove the next line if you don't want to depend on the API
    modApi("dev.architectury:architectury:${rootProject.properties["architectury_version"]}")
    modImplementation("earth.terrarium:botarium-common-${rootProject.properties["minecraft_version"]}:${rootProject.properties["botarium_version"]}")

    include(implementation("com.thepokecraftmod:modelLoader:1.2.3")!!)
    include(implementation("com.thepokecraftmod:renderer:1.2.3")!!)
    include(implementation("org.tukaani:xz:1.9")!!)
    include(implementation("com.thebombzen:jxlatte:1.1.0")!!)

    compileOnly("org.lwjgl:lwjgl-assimp:3.3.2")
}

architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))