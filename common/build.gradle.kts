architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

sourceSets.main.get().resources.srcDir(file("src/main/generated/resources"))

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))

val minecraftVersion = project.properties["minecraft_version"] as String

repositories {
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    modApi("dev.architectury:architectury:${project.properties["architectury_version"]}")

    modCompileOnly("earth.terrarium.botarium:botarium-common-$minecraftVersion:${project.properties["botarium_version"]}")

    implementation("gg.generations:RareCandy:${project.properties["rareCandy"]}"){isTransitive = false}

    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
    modCompileOnly(fileTree(mapOf("dir" to "libs", "include" to "*.jar")))

    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api:${project.properties["rei"]}")
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin:${project.properties["rei"]}")

    modCompileOnly("maven.modrinth:journeymap:${project.properties["minecraft_version"]}-${project.properties["journeymap_version"]}-forge")
    modCompileOnly("maven.modrinth:xaeros-minimap:${project.properties["xaeros_minimap_version"]}_Forge_1.20")


    //Cobblemon
    implementation(kotlin("stdlib-jdk8"))
    modCompileOnly("com.cobblemon:mod:${project.properties["cobblemon_version"]}")
    modImplementation("net.impactdev.impactor.api:economy:5.1.1+1.20.1")
}
