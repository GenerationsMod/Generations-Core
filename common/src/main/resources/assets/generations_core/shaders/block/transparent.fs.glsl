#version 330 core

in vec2 texCoord0;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D lightmap;
uniform ivec2 light;
uniform float alpha;

vec4 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
}

void main() {
    vec4 color = texture(diffuse, texCoord0);
    vec4 lightColor = minecraft_sample_lightmap(lightmap, light);

    outColor = color * lightColor;
    outColor.a = outColor.a;
}
