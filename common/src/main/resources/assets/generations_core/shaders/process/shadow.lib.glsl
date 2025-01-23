vec4 process(vec4 inColor) {
    float grayscale = 0.2126 * inColor.r + 0.7152 * inColor.g + 0.0722 * inColor.b;

    vec3 baseColor = vec3(grayscale);

    vec2 wrappedUV = fract(texCoord0 * 5.0);
    float gradient = sin(wrappedUV.x * 3.14159) * sin(wrappedUV.y * 3.14159);

    gradient = (gradient + 1.0) * 0.5;

    vec3 deepPurpleBlue = vec3(0.1, 0.1, 0.2);
    vec3 darkerShade = vec3(0.05, 0.05, 0.1);

    vec3 shadowColor = mix(deepPurpleBlue, darkerShade, gradient);

    vec3 finalColor = mix(baseColor, shadowColor, 0.7);

    finalColor = finalColor * 0.9;

    finalColor = clamp(finalColor, 0.0, 1.0);

    return vec4(finalColor, inColor.a);
}
