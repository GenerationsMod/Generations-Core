
loom.silentMojangMappingsLicense()

repositories {
    mavenCentral()
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")
    maven("https://jitpack.io") // BinarySMD
    maven {
        name = "generationsMavenSnapshots"
        url = uri("https://maven.generations.gg/snapshots")
    }
}

sourceSets.main  {
    resources.srcDir("src/main/generated/resources")
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

    implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}
    implementation("org.tukaani:xz:${project.properties["rareCandyXZ"]}")
    implementation("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}")
    implementation("de.javagl:jgltf-model:${project.properties["rareCandyJgltfModel"]}")
    implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){isTransitive = false}
    implementation("org.msgpack:msgpack-core:${project.properties["rareCandyMsgPackCore"]}")
    implementation("com.google.flatbuffers:flatbuffers-java:23.3.3")
}

architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))