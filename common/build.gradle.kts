architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))

val minecraftVersion = project.properties["minecraft_version"] as String

dependencies {
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    // Remove the next line if you don't want to depend on the API
    modApi("dev.architectury:architectury:${project.properties["architectury_version"]}")
    modApi("earth.terrarium.botarium:botarium-common-$minecraftVersion:${project.properties["botarium_version"]}")

    implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}
//    implementation("org.tukaani:xz:${project.properties["rareCandyXZ"]}")
//    implementation("org.apache.commons:commons-compress:${project.properties["rareCandyCommonCompress"]}")
//    implementation("org.lwjgl:lwjgl-assimp:3.3.2")
//    implementation("org.lwjgl:lwjgl-assimp:3.3.2:natives-windows")
    implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){isTransitive = false}
//    implementation("org.msgpack:msgpack-core:${project.properties["rareCandyMsgPackCore"]}")
//    implementation("com.google.flatbuffers:flatbuffers-java:${project.properties["rareCandyFlatBuffers"]}")
    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    //Cobblemon
    implementation(kotlin("stdlib-jdk8"))
    modCompileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")
    modImplementation("net.impactdev.impactor.api:economy:5.1.1+1.20.1")
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
                username = getGensCredentials().first
                password = getGensCredentials().second
            }
        }
    }
}

private fun getGensCredentials(): Pair<String?, String?> {
    val username = (project.findProperty("gensUsername") ?: System.getenv("GENS_USERNAME") ?: "") as String?
    val password = (project.findProperty("gensPassword") ?: System.getenv("GENS_PASSWORD") ?: "") as String?
    return Pair(username, password)
}