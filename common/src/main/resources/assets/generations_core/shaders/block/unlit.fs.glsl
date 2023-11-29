#version 330 core

in vec2 texCoord0;

out vec4 outColor;

uniform sampler2D diffuse;


void main() {
    vec4 color = texture(diffuse, texCoord0);

    if(color.a < 0.1) {
        discard;
    }

    outColor = color;
}
