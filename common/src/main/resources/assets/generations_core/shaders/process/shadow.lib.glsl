#define shadowBase vec3(0.04, 0.04, 0.1)
#define shadowHighlight vec3(0.07, 0.07, 0.08)

vec4 process(vec4 inColor) {
    float luminance = dot(inColor.rgb, vec3(0.299, 0.587, 0.114));
    vec3 grayscaleColor = vec3(luminance);

    vec3 shadowColor = mix(shadowBase, shadowHighlight, smoothstep(0.3, 0.7, luminance));

    vec3 finalColor = mix(grayscaleColor, shadowColor, 0.9);

    finalColor = finalColor * 1.1 - 0.05;

    finalColor = clamp(finalColor, 0.0, 1.0);

    return vec4(finalColor, inColor.a);
}