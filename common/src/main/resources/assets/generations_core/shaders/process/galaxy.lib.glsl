const float darkenFactor = 0.3;

vec4 process(vec4 inColor) {
    float brightness = dot(inColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    const vec3 lightGradientColor1 = vec3(0.2, 0.0, 0.3);
    const vec3 lightGradientColor2 = vec3(0.6, 0.1, 0.7);
    const float gradientThreshold = 0.5;
    vec3 gradientColor = mix(lightGradientColor1, lightGradientColor2, smoothstep(gradientThreshold, 1.0, brightness));
    inColor.rgb *= darkenFactor;
    inColor.rgb = mix(inColor.rgb, gradientColor, smoothstep(gradientThreshold, 1.0, brightness));
    return inColor;
}
