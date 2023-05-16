#version 330 core

in vec2 texCoord0;
in vec3 normal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 outColor;

uniform sampler2D diffuse;

uniform vec3 LIGHT_color;
uniform float LIGHT_shineDamper;
uniform float LIGHT_reflectivity;

const float AMBIENT_LIGHT = 0.6f;

void main() {
    vec4 color = texture(diffuse, texCoord0);

    if(color.a < 0.1) {
        discard;
    }

    // Math
    vec3 unitNormal = normalize(normal);
    vec3 unitLightVector = normalize(toLightVector);
    vec3 lightDir = -unitLightVector;
    vec3 unitToCameraVector = normalize(toCameraVector);

    // Diffuse Lighting
    float rawDiffuse = dot(unitNormal, unitLightVector);
    float diffuse = max(rawDiffuse, AMBIENT_LIGHT);
    vec3 coloredDiffuse = diffuse * LIGHT_color;

    // Specular Lighting
    vec3 reflectedLightDir = reflect(lightDir, unitNormal);
    float rawSpecularFactor = dot(reflectedLightDir, unitToCameraVector);
    float specularFactor = max(rawSpecularFactor, 0.0f);
    float dampedFactor = pow(specularFactor, LIGHT_shineDamper);
    vec3 finalSpecular = dampedFactor * LIGHT_reflectivity * LIGHT_color;

    outColor = vec4(coloredDiffuse, 1.0f) * color + vec4(finalSpecular, 1.0f);
}
