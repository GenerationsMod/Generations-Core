#version 330 core

in vec2 texCoord0;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D lightmap;
uniform sampler2D emission;
uniform ivec2 light;
uniform bool useLight;

vec4 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
}

void main() {
    outColor = texture(diffuse, texCoord0);
    if(useLight) outColor *= mix(minecraft_sample_lightmap(lightmap, light), vec4(1,1,1,1), texture(emission, texCoord0).r);
}
