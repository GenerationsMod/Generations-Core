#version 430
layout(local_size_x = 256, local_size_y = 1) in;

struct SourceVertex {
    vec3 position;
    vec2 texcoord;
    vec3 normal;
    uvec4 joints;
    vec4 weights;
};

struct TargetVertex {
    float x;
    float y;
    float z;
    uint color;
    float u0;
    float v0;
    uint uv1;
    uint uv2;
    uint normal;
};

struct DrawCmd {
    uint baseIndex;
    uint indexCount;
};

struct Instance {
    mat4 modelMatrix;
    mat4 boneTransforms[220];
    vec4 tint;
    vec3 teraTint;
    uint teraActive;
    uint light;
    uint overlay;
};

struct Transform {
    vec2 scale;
    vec2 offset;
    int variant;
};

uniform int variantSize;
uniform int instanceId;

layout(std430, binding = 0) readonly  buffer SrcBuffer       { SourceVertex src[]; };
layout(std430, binding = 1) readonly  buffer IndexBuffer     { uint indices[]; };
layout(std430, binding = 2) readonly  buffer DrawCommands    { DrawCmd cmd[]; };
layout(std430, binding = 3) readonly  buffer InstanceBuffer  { Instance instances[]; };
layout(std430, binding = 4) readonly  buffer TransformBuffer { Transform transforms[]; };
layout(std430, binding = 5) writeonly buffer DstBuffer       { TargetVertex dst[]; };

mat4 getBoneTransform(Instance instance, uvec4 joints, vec4 weights) {
    mat4[] bone = instance.boneTransforms;

    return
    bone[joints.x] * weights.x +
    bone[joints.y] * weights.y +
    bone[joints.z] * weights.z +
    bone[joints.w] * weights.w;
}

void main() {
    uint local = gl_GlobalInvocationID.x;
    uint meshId = gl_GlobalInvocationID.y;

    DrawCmd c = cmd[meshId];
    if (local >= c.indexCount) return;

    uint idx = c.baseIndex + local;

    SourceVertex src = src[indices[idx]];
    TargetVertex outV;

    Instance instance = instances[instanceId];

    vec4 pos = instance.modelMatrix * (getBoneTransform(instance, src.joints, src.weights) * vec4(src.position, 1.0));

    Transform uvTransform = transforms[instanceId * variantSize + meshId];
    vec2 uv0 = src.texcoord * uvTransform.scale + uvTransform.offset;

    outV.x = pos.x;
    outV.y = pos.y;
    outV.z = pos.z;
    outV.color = 0xFFFFFFFFu;
    outV.u0 = uv0.x;
    outV.v0 = uv0.y;
    outV.uv1 = instance.light;
    outV.uv2 = instance.overlay;

    ivec3 normalPacked = ivec3(clamp(src.normal * 127.0, -128.0, 127.0));
    outV.normal = uint(normalPacked.x & 0xFF) | (uint(normalPacked.y & 0xFF) << 8) | (uint(normalPacked.z & 0xFF) << 16);

    dst[idx] = outV;
}