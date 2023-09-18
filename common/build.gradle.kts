architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))

val minecraftVersion = project.properties["minecraft_version"] as String

dependencies {
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    // Remove the next line if you don't want to depend on the API
    modApi("dev.architectury:architectury:${project.properties["architectury_version"]}")
    modImplementation("earth.terrarium:botarium-common-$minecraftVersion:${project.properties["botarium_version"]}")

    implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}
    implementation("org.tukaani:xz:${project.properties["rareCandyXZ"]}")
    implementation("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}")
    implementation("de.javagl:jgltf-model:${project.properties["rareCandyJgltfModel"]}")
    implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){isTransitive = false}
    implementation("org.msgpack:msgpack-core:${project.properties["rareCandyMsgPackCore"]}")
    implementation("com.google.flatbuffers:flatbuffers-java:${project.properties["rareCandyFlatBuffers"]}")
    implementation("com.thebombzen:jxlatte:1.1.0")
    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")

    //Cobblemon
    implementation(kotlin("stdlib-jdk8"))
    modCompileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")
    implementation("net.impactdev.impactor.api:economy:5.1.0")
}

publishing {
    publications.create<MavenPublication>("mavenCommon") {
        artifactId = "${project.properties["archives_base_name"]}" + "-Common"
        from(components["java"])
    }

    repositories {
        mavenLocal()
        maven {
            val releasesRepoUrl = "https://maven.generations.gg/releases"
            val snapshotsRepoUrl = "https://maven.generations.gg/snapshots"
            url = uri(if (project.version.toString().endsWith("SNAPSHOT") || project.version.toString().startsWith("0")) snapshotsRepoUrl else releasesRepoUrl)
            name = "Generations-Repo"
            credentials {
                username = project.properties["repoLogin"]?.toString()
                password = project.properties["repoPassword"]?.toString()
            }
        }
    }
}