#version 330 core

out float vertexDistance;
out vec4 vertexColor;
out vec4 lightMapColor;
out vec2 texCoord0;
out vec4 normal;

out vec4 outColor;

uniform sampler2D diffuse;
uniform sampler2D emission;
uniform sampler2D mask;

uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform ivec2 light;
uniform bool useLight;

uniform vec3 color;

vec4 computeLight(sampler2D emission, vec4 lightMapColor, vec2 texCoord0) {
    return mix(lightMapColor, vec4(1, 1, 1, 1), texture(emission, texCoord0).r);
}

vec4 linear_fog(vec4 inColor, float vertexDistance, float fogStart, float fogEnd, vec4 fogColor) {
    if (vertexDistance <= fogStart) {
        return inColor;
    }

    float fogValue = vertexDistance < fogEnd ? smoothstep(fogStart, fogEnd, vertexDistance) : 1.0;
    return vec4(mix(inColor.rgb, fogColor.rgb, fogValue * fogColor.a), inColor.a);
}

void main() {
    vec4 colors = texture(diffuse, texCoord0);

    if(colors.a < 0.004) discard;

    colors.xyz = mix(colors.xyz, colors.xyz * color, texture(mask, texCoord0).x);

    if (useLight) {
        colors *= vertexColor * lightMapColor * computeLight(emission, lightMapColor, texCoord0);
    }

    outColor = linear_fog(colors, vertexDistance, FogStart, FogEnd, FogColor);
}
