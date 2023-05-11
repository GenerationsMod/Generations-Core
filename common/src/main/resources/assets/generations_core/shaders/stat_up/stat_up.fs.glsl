#version 330 core

in vec2 texCoord0;
in vec3 normal;

out vec4 outColor;

uniform sampler2D diffuse;
uniform float time;

void main() {
    vec4 color = texture(diffuse, vec2(texCoord0.x, texCoord0.y + time));
    if (color.a < 0.1) discard;

    float gradientSlider = 1.5 - color.r;
    vec3 orangeShade = vec3(229.0 / 255, 107.0 / 255, 0.0 / 255);
    outColor = mix(vec4(mix(color.rgb, orangeShade, max(0.0, gradientSlider)), color.a), vec4(1.0, 1.0, 1.0, 1.0), color.b * 0.5);
}
