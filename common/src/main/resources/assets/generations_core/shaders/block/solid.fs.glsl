#version 330 core

// Inputs from the vertex shader
in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec2 texCoord0;
in vec4 normal;

// Output color to the framebuffer
out vec4 outColor;

// Uniforms (textures and constants)
uniform sampler2D diffuse;
uniform sampler2D emission;

uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform ivec2 light;
uniform bool useLight;

vec4 computeLight(sampler2D emission, vec4 lightMapColor, vec2 texCoord0) {
    return mix(lightMapColor, vec4(1, 1, 1, 1), texture(emission, texCoord0).r);
}

// Function to apply linear fog
vec4 linear_fog(vec4 inColor, float vertexDistance, float fogStart, float fogEnd, vec4 fogColor) {
    if (vertexDistance <= fogStart) {
        return inColor;
    }

    float fogValue = vertexDistance < fogEnd ? smoothstep(fogStart, fogEnd, vertexDistance) : 1.0;
    return vec4(mix(inColor.rgb, fogColor.rgb, fogValue * fogColor.a), inColor.a);
}

void main() {
    // Fetch the base color from the diffuse texture
    vec4 color = texture(diffuse, texCoord0) * vertexColor;

    // Discard the fragment if the alpha is too low
    if (color.a < 0.004) discard;

    // Apply lighting if enabled
    if (useLight) {
        color *= vertexColor * lightMapColor * computeLight(emission, lightMapColor, texCoord0);
    }

    // Apply fog and set the final output color
    outColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}