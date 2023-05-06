
loom.silentMojangMappingsLicense()

repositories {
    maven("https://nexus.resourcefulbees.com/repository/maven-public/")
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
}

architectury {
    common("forge", "fabric")
    platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/generationscore.accesswidener"))