#version 330 core

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D lightmap;
uniform sampler2D emission;
uniform ivec2 light;
uniform bool useLight;

#define MINECRAFT_LIGHT_POWER   (0.6)
#define MINECRAFT_AMBIENT_LIGHT (0.4)

vec4 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
}

void main() {
    outColor = texture(diffuse, texCoord0);
    if(outColor.a < 0.004) discard;
    if(useLight) {
        outColor *= vertexColor;
        // Sample Minecraft's light level from the lightmap texture
        vec4 minecraftLight = minecraft_sample_lightmap(lightmap, light);

        outColor *= mix(minecraftLight, vec4(1,1,1,1), texture(emission, texCoord0).r);
    }
}
