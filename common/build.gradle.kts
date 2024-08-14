architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))

val minecraftVersion = project.properties["minecraft_version"] as String

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
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
