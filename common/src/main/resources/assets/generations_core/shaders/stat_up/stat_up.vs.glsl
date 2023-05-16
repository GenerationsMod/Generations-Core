#version 330 core

layout(location = 0) in vec3 positions;
layout(location = 1) in vec2 texcoords;
layout(location = 2) in vec3 inNormal;

out vec2 texCoord0;
out vec3 normal;

uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;

void main() {
    mat4 worldSpace = projectionMatrix * viewMatrix;
    vec4 worldPosition = modelMatrix * vec4(positions, 1.0);

    normal = (modelMatrix * vec4(inNormal, 0.0)).xyz;
    texCoord0 = texcoords;
    gl_Position = worldSpace * worldPosition;
}