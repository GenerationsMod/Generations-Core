plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val minecraftVersion = project.properties["minecraft_version"] as String

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentFabric").extendsFrom(configurations["common"])
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
    runs.create("datagen") {
        server()
        name("Data Generation")
        vmArg("-Dfabric-api.datagen")
        vmArg("-Dfabric-api.datagen.output-dir=${project(":fabric").file("src/main/generated/resources").absolutePath}")
        vmArg("-Dfabric-api.datagen.modid=generations_core")

        runDir("build/datagen")
    }
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    modApi("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_api_version"]}+$minecraftVersion")
    modApi("dev.architectury:architectury-fabric:${project.properties["architectury_version"]}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionFabric")) { isTransitive = false }

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")

    implementation("shadowCommon"("gg.generations", "RareCandy", "${project.properties["rareCandy"]}"){isTransitive = false})!!
    implementation("shadowCommon"("org.tukaani", "xz", "${project.properties["rareCandyXZ"]}"))!!
    implementation("shadowCommon"("de.javagl", "jgltf-model", "${project.properties["rareCandyJgltfModel"]}"))!!
    implementation("shadowCommon"("com.github.thecodewarrior", "BinarySMD", "${project.properties["rareCandyBinarySMD"]}"){isTransitive = false})!!
    implementation("shadowCommon"("org.msgpack", "msgpack-core", "${project.properties["rareCandyMsgPackCore"]}"))!!
    implementation("shadowCommon"("com.google.flatbuffers", "flatbuffers-java", "${project.properties["rareCandyFlatBuffers"]}"))!!
    implementation("shadowCommon"("com.thebombzen:jxlatte:1.1.0")!!)

    modApi("earth.terrarium:botarium-fabric-$minecraftVersion:${project.properties["botarium_version"]}")
    modRuntimeOnly("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modRuntimeOnly("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")

    //Cobblemon
    modApi("com.cobblemon:fabric:${project.properties["cobblemon_version"]}")
}

tasks {
    base.archivesName.set(base.archivesName.get() + "-Fabric")
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
        exclude("generations/gg/generations/core/generationscore/fabric/datagen/**")
        exclude("data/forge/**")
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }

    jar.get().archiveClassifier.set("dev")

    sourcesJar {
        val commonSources = project(":common").tasks.sourcesJar
        dependsOn(commonSources)
        from(commonSources.get().archiveFile.map { zipTree(it) })
    }
}

components {
    java.run {
        if (this is AdhocComponentWithVariants)
            withVariantsFromConfiguration(project.configurations.shadowRuntimeElements.get()) { skip() }
    }
}

publishing {
    publications.create<MavenPublication>("mavenCommon") {
        artifactId = "${project.properties["archives_base_name"]}" + "-Fabric"
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