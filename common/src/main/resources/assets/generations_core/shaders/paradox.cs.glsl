#version 430
layout(local_size_x = 16, local_size_y = 16) in;

uniform sampler2D sampler;
uniform int frame;

layout(rgba8, binding = 0) uniform image2D outputTexture;

float getParadoxIntensity(vec2 effectTexCoord) {
    effectTexCoord *= 4.0;
    effectTexCoord = fract(effectTexCoord);

    effectTexCoord *= 0.25;
    effectTexCoord.x += (frame % 4) / 4.0;
    effectTexCoord.y += (frame / 4) / 4.0;

    return clamp(texture(sampler, effectTexCoord).r * 2.0, 0.0, 1.0);
}

void main() {
    ivec2 pixel = ivec2(gl_GlobalInvocationID.xy);
    vec2 uv = (pixel + 0.5) / textureSize(sampler, 0);

    imageStore(outputTexture, pixel, vec4(getParadoxIntensity(uv), 1.0, 1.0, 1.0));
}