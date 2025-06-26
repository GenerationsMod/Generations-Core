vec4 process(vec4 inColor) {
    float grayscale = 0.2126 * inColor.r + 0.7152 * inColor.g + 0.0722 * inColor.b;

    float luminanceDx = dFdx(grayscale);
    float luminanceDy = dFdy(grayscale);
    float edgeFactor = length(vec2(luminanceDx, luminanceDy));

    float outline = 1.0 - smoothstep(0.02, 0.05, edgeFactor);
    vec3 edgeColor = vec3(0.0);

    vec3 finalColor = mix(vec3(grayscale), edgeColor, outline);

    return vec4(finalColor, inColor.a);
}