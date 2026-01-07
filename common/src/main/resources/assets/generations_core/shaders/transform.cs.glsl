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
    vec3 position;
    vec4 color;
    vec2 uv0;
    ivec2 uv1;
    ivec2 uv2;
    vec3 normal;
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
    ivec2 light;
    ivec2 overlay;
};

struct Transform {
    vec2 scale;
    vec2 offset;
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

    vec4 pos = getBoneTransform(instance, src.joints, src.weights) * vec4(src.position, 1.0) * instance.modelMatrix;

    Transform uvTransform = transforms[instanceId * variantSize + meshId];

    outV.position = pos.xyz;
    outV.color = instance.tint;
    outV.uv0 = src.texcoord * uvTransform.scale + uvTransform.offset;
    outV.uv1 = instance.light;
    outV.uv2 = instance.overlay;
    outV.normal   = src.normal;

    dst[idx] = outV;
}