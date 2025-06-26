#version 330 core
#define MAX_BONES 220
#define MINECRAFT_LIGHT_POWER   (0.6)
#define MINECRAFT_AMBIENT_LIGHT (0.4)

layout(location = 0) in vec3 positions;
layout(location = 1) in vec2 texcoords;
layout(location = 2) in vec3 inNormal;
layout(location = 3) in vec4 joints;
layout(location = 4) in vec4 weights;

out float vertexDistance;
out vec4 vertexColor;
out vec2 texCoord0;
out vec3 fragNormal;
out vec3 fragViewDir;
out vec3 worldPos;
out vec4 lightMapColor;

uniform sampler2D lightmap;

uniform bool legacy;

uniform int FogShape;

uniform ivec2 light;

uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;
uniform vec2 uvOffset;
uniform vec2 uvScale;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;

uniform mat4 boneTransforms[MAX_BONES];

mat4 getBoneTransform() {
    mat4 boneTransform =
    boneTransforms[uint(joints.x)] * weights.x + // Bone 1 Transform (Bone Transform * Weight)
    boneTransforms[uint(joints.y)] * weights.y + // Bone 2 Transform (Bone Transform * Weight)
    boneTransforms[uint(joints.z)] * weights.z + // Bone 3 Transform (Bone Transform * Weight)
    boneTransforms[uint(joints.w)] * weights.w ; // Bone 4 Transform (Bone Transform * Weight)
    return boneTransform;
}

float fog_distance(vec3 pos, int shape) {
    if (shape == 0) {
        return length(pos);
    } else {
        float distXZ = length(pos.xz);
        float distY = abs(pos.y);
        return max(distXZ, distY);
    }
}

vec4 getVertexColor(vec3 normal) {
    if(legacy) return vec4(1);

    vec3 lightDir0 = normalize(Light0_Direction);
    vec3 lightDir1 = normalize(Light1_Direction);
    float light0 = max(0.0, dot(Light0_Direction, normal));
    float light1 = max(0.0, dot(Light1_Direction, normal));
    float lightAccum = min(1.0, (light0 + light1) * MINECRAFT_LIGHT_POWER + MINECRAFT_AMBIENT_LIGHT);
    return vec4(lightAccum, lightAccum, lightAccum, 1);
}

void main() {
    mat4 worldSpace = projectionMatrix * viewMatrix;
    mat4 modelTransform = modelMatrix * getBoneTransform();
    vec4 worldPosition = modelTransform * vec4(positions, 1.0);

    gl_Position = worldSpace * worldPosition;
    vertexColor = getVertexColor(inNormal * transpose(inverse(mat3(modelTransform))));
    vertexDistance = fog_distance(gl_Position.xyz, FogShape);
    lightMapColor = texelFetch(lightmap, light / 16, 0);
    texCoord0 = (texcoords * uvScale) + uvOffset;

    fragViewDir = normalize(-(viewMatrix * worldPosition).xyz);
    worldPos = worldPosition.xyz;
}
