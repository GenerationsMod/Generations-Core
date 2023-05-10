#version 330 core
#extension GL_ARB_explicit_uniform_location : enable
#pragma optionNV(strict on)
#define LIGHT_COUNT 4

in VS_OUT {
    vec3 pos;
    vec2 uv;
    vec3 normal;
    vec3 camPos;
    vec3 lightPositions[LIGHT_COUNT];
} fsIn;

out vec4 outColor;

layout (location = 0) uniform sampler2D albedo;
layout (location = 1) uniform sampler2D normal;
layout (location = 2) uniform sampler2D metallic;
layout (location = 3) uniform sampler2D roughness;
layout (location = 4) uniform sampler2D ao;
layout (location = 5) uniform sampler2D emission;

uniform vec3 lightColors[LIGHT_COUNT];

const float PI = 3.14159265359;

float DistributionGGX(vec3 N, vec3 H, float roughness) {
    float a = roughness * roughness;
    float a2 = a * a;
    float NdotH = max(dot(N, H), 0.0);
    float NdotH2 = NdotH * NdotH;

    float num = a2;
    float denom = (NdotH2 * (a2 - 1.0) + 1.0);
    denom = PI * denom * denom;

    return num / denom;
}

float GeometrySchlickGGX(float NdotV, float roughness) {
    float r = (roughness + 1.0);
    float k = (r * r) / 8.0;

    float num = NdotV;
    float denom = NdotV * (1.0 - k) + k;

    return num / denom;
}

float GeometrySmith(vec3 N, vec3 V, vec3 L, float roughness) {
    float NdotV = max(dot(N, V), 0.0);
    float NdotL = max(dot(N, L), 0.0);
    float ggx2 = GeometrySchlickGGX(NdotV, roughness);
    float ggx1 = GeometrySchlickGGX(NdotL, roughness);

    return ggx1 * ggx2;
}

vec3 fresnelSchlick(float cosTheta, vec3 F0) {
    return F0 + (1.0 - F0) * pow(clamp(1.0 - cosTheta, 0.0, 1.0), 5.0);
}

void main() {
    vec3 albedoColor = pow(texture(albedo, fsIn.uv).rgb, vec3(2.2));
    vec3 normalVector = texture(normal, fsIn.uv).rgb;
    float metallicValue = texture(metallic, fsIn.uv).r * 1.2;
    float roughnessValue = texture(roughness, fsIn.uv).g * 1.2;
    float aoValue = texture(ao, fsIn.uv).b;
    float emissionValue = texture(emission, fsIn.uv).r;

    if (emissionValue > 0) {
        outColor = texture(albedo, fsIn.uv);
        return;
    }

    vec3 N = normalize(fsIn.normal);
    vec3 V = normalize(fsIn.camPos - fsIn.pos);

    vec3 F0 = vec3(0.04);
    F0 = mix(F0, albedoColor, metallicValue);

    // reflectance equation
    vec3 Lo = vec3(0.0);
    for (int i = 0; i < LIGHT_COUNT; ++i) {
        // calculate per-light radiance
        vec3 L = normalize(fsIn.lightPositions[i] - fsIn.pos);
        vec3 H = normalize(V + L);
        float distance = length(fsIn.lightPositions[i] - fsIn.pos);
        float attenuation = 1.0 / (distance * distance);
        vec3 radiance = lightColors[i] * attenuation;

        // cook-torrance brdf
        float NDF = DistributionGGX(N, H, roughnessValue);
        float G = GeometrySmith(N, V, L, roughnessValue);
        vec3 F = fresnelSchlick(max(dot(H, V), 0.0), F0);

        vec3 kS = F;
        vec3 kD = vec3(1.0) - kS;
        kD *= 1.0 - metallicValue;

        vec3 numerator = NDF * G * F;
        float denominator = 4.0 * max(dot(N, V), 0.0) * max(dot(N, L), 0.0) + 0.0001;
        vec3 specular = numerator / denominator;

        // add to outgoing radiance Lo
        float NdotL = max(dot(N, L), 0.0);
        Lo += (kD * albedoColor / PI + specular) * radiance * NdotL;
    }

    vec3 ambient = vec3(0.14) * albedoColor * aoValue;

    vec3 color = ambient + Lo;

    // HDR tonemapping and gamma correction
    color = color / (color + vec3(1.0));
    color = pow(color, vec3(1.0 / 2.2));

    outColor = vec4(color, 1.0);
}