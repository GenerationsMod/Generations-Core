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

    modCompileOnly("earth.terrarium.botarium:botarium-common-$minecraftVersion:${project.properties["botarium_version"]}")

    implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}

    implementation("com.github.thecodewarrior:BinarySMD:${project.properties["rareCandyBinarySMD"]}"){isTransitive = false}
    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    //Cobblemon
    implementation(kotlin("stdlib-jdk8"))
    modCompileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")
    modImplementation("net.impactdev.impactor.api:economy:5.1.1+1.20.1")
}
