#version 330 core

in vec2 texCoord0;
in float vertexDistance;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D lightmap;
uniform sampler2D mask;
uniform sampler2D emission;

uniform vec3 color;

uniform ivec2 light;
uniform bool useLight;

uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

vec4 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
}

vec4 linear_fog(vec4 inColor, float vertexDistance, float fogStart, float fogEnd, vec4 fogColor) {
    if (vertexDistance <= fogStart) {
        return inColor;
    }

    float fogValue = vertexDistance < fogEnd ? smoothstep(fogStart, fogEnd, vertexDistance) : 1.0;
    return vec4(mix(inColor.rgb, fogColor.rgb, fogValue * fogColor.a), inColor.a);
}

void main() {
    outColor = texture(diffuse, texCoord0);

    if(outColor.a < 0.004) discard;

    float mask = texture(mask, texCoord0).x;

    outColor.xyz = mix(outColor.xyz, outColor.xyz * color, mask);

    if(useLight) outColor *= mix(minecraft_sample_lightmap(lightmap, light), vec4(1,1,1,1), texture(emission, texCoord0).r);

    outColor = linear_fog(outColor, vertexDistance, FogStart, FogEnd, FogColor);
}
