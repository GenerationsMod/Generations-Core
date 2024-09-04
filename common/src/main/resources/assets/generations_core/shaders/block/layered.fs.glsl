#version 330 core

// Output variables
out vec4 outColor;

// Input variables
in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec2 texCoord0;
in vec4 normal;

// Texture samplers
uniform sampler2D diffuse;
uniform sampler2D emission;
uniform sampler2D layer;
uniform sampler2D mask;

// Fog parameters
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

// Light parameters
uniform ivec2 light;
uniform bool useLight;

// Base colors
uniform vec3 baseColor1;
uniform vec3 baseColor2;
uniform vec3 baseColor3;
uniform vec3 baseColor4;
uniform vec3 baseColor5;

// Emission colors and intensities
uniform vec3 emiColor1;
uniform vec3 emiColor2;
uniform vec3 emiColor3;
uniform vec3 emiColor4;
uniform vec3 emiColor5;
uniform float emiIntensity1;
uniform float emiIntensity2;
uniform float emiIntensity3;
uniform float emiIntensity4;
uniform float emiIntensity5;

// Frame information
uniform int frame;

// Function to adjust the color intensity
vec4 adjust(vec4 color) {
    return clamp(color * 2.0, 0.0, 1.0);
}

// Function to adjust scalar intensity
float adjustScalar(float color) {
    return clamp(color * 2.0, 0.0, 1.0);
}

// Function to get the mask intensity
float getMaskIntensity() {
    vec2 effectTexCoord = texCoord0;

    if (frame >= 0) {
        effectTexCoord *= 0.25;
        effectTexCoord.x += (frame % 4) / 4.0;
        effectTexCoord.y += (frame / 4) / 4.0;
    }

    return texture(mask, effectTexCoord).r;
}

// Function to apply emission to the base color
vec3 applyEmission(vec3 base, vec3 emissionColor, float intensity) {
    return base + (emissionColor - base) * intensity;
}

// Function to get the final color
vec4 getColor() {
    vec4 color = texture(diffuse, texCoord0);
    vec4 layerMasks = adjust(texture(layer, texCoord0));
    float maskColor = adjustScalar(getMaskIntensity());

    vec3 base = mix(color.rgb, color.rgb * baseColor1, layerMasks.r);
    base = mix(base, color.rgb * baseColor2, layerMasks.g);
    base = mix(base, color.rgb * baseColor3, layerMasks.b);
    base = mix(base, color.rgb * baseColor4, layerMasks.a);
    base = mix(base, color.rgb * baseColor5, maskColor);

    base = mix(base, applyEmission(base, emiColor1, emiIntensity1), layerMasks.r);
    base = mix(base, applyEmission(base, emiColor2, emiIntensity2), layerMasks.g);
    base = mix(base, applyEmission(base, emiColor3, emiIntensity3), layerMasks.b);
    base = mix(base, applyEmission(base, emiColor4, emiIntensity4), layerMasks.a);
    base = mix(base, applyEmission(vec3(0.0), emiColor5, emiIntensity5), maskColor);

    return vec4(base, color.a);
}

// Function to compute light
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
    vec4 color = getColor();

    // Discard the fragment if alpha is too low
    if (color.a < 0.004) discard;

    // Apply lighting if enabled
    if (useLight) {
        color *= vertexColor * lightMapColor * computeLight(emission, lightMapColor, texCoord0);
    }

    // Apply fog and set the final output color
    outColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}