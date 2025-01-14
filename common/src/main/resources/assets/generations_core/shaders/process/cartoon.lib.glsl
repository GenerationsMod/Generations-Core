const float edgeThreshold = 0.08;
const float blockSize = 0.0015;
const int bilateralIterations = 2;

const vec2 offsets[8] = vec2[](
vec2(-1.0, -1.0), vec2(0.0, -1.0), vec2(1.0, -1.0),
vec2(-1.0,  0.0),                  vec2(1.0,  0.0),
vec2(-1.0,  1.0), vec2(0.0,  1.0), vec2(1.0,  1.0)
);

float detectEdge(vec2 uv) {
    float centerIntensity = dot(texture(diffuse, uv).rgb, vec3(0.2126, 0.7152, 0.0722));
    float diffSum = 0.0;

    for (int i = 0; i < 8; i++) {
        vec2 offsetUV = uv + offsets[i] * blockSize;
        float neighborIntensity = dot(texture(diffuse, offsetUV).rgb, vec3(0.2126, 0.7152, 0.0722));
        diffSum += abs(centerIntensity - neighborIntensity);
    }

    return smoothstep(edgeThreshold - 0.02, edgeThreshold + 0.02, diffSum / 8.0);
}

vec3 bilateralFilter(vec2 uv) {
    vec3 colorSum = vec3(0.0);
    float weightSum = 0.0;

    for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
            vec2 offsetUV = uv + vec2(float(i), float(j)) * 0.003;
            vec3 sampleColor = texture(diffuse, offsetUV).rgb;
            float spatialWeight = exp(-float(i * i + j * j) / (2.0 * 1.0));
            float colorWeight = exp(-dot(sampleColor - texture(diffuse, uv).rgb, sampleColor - texture(diffuse, uv).rgb) / (2.0 * 0.05));
            float weight = spatialWeight * colorWeight;
            colorSum += sampleColor * weight;
            weightSum += weight;
        }
    }

    return colorSum / weightSum;
}

vec4 process(vec4 inColor) {
    float edge = detectEdge(texCoord0);

    vec3 color = texture(diffuse, texCoord0).rgb;
    for (int i = 0; i < bilateralIterations; i++) {
        color = bilateralFilter(texCoord0);
    }

    return vec4(mix(color, inColor.rgb, edge), 1.0);
}