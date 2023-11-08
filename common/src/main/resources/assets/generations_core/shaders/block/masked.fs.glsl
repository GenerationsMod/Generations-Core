#version 330 core

in vec2 texCoord0;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D lightmap;
uniform sampler2D mask;
uniform vec3 color;
uniform ivec2 light;

vec3 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0))).xyz;
}

void main() {
    vec4 colorIn = texture(diffuse, texCoord0);
    if(colorIn.a < 0.1) {
        discard;
    }

    vec3 lightColor = minecraft_sample_lightmap(lightmap, light);
    float mask = texture(mask, texCoord0).x;
    vec3 tinColor = mix(vec3(1.0), color, mask);

    outColor = vec4(lightColor * tinColor * colorIn.xyz, 1.0);

}
