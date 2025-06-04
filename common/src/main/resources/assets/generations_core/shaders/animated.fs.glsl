#version 330 core

in vec2 texCoord0;
in vec4 vertexColor;
in float vertexDistance;

out vec4 outColor;

uniform vec4 ColorModulator;

//fog
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform sampler2D diffuse;
uniform sampler2D mask;
uniform sampler2D layer;
uniform sampler2D lightmap;
uniform sampler2D emission;
uniform sampler2D paradoxMask;

uniform int colorMethod;
uniform int effect;

uniform ivec2 light;

uniform vec3 tint;

uniform int frame;

//material
uniform vec3 baseColor1;
uniform vec3 baseColor2;
uniform vec3 baseColor3;
uniform vec3 baseColor4;
uniform vec3 baseColor5;
uniform vec3 emiColor1;
uniform vec3 emiColor2;
uniform vec3 emiColor3;
uniform vec3 emiColor4;
uniform vec3 emiColor5;
uniform float emiIntensity1;
uniform float emiIntensity2;
uniform float emiIntensity3;
uniform float emiIntensity4;
uniform float emiIntensity5;
uniform bool useLight;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;


#define MINECRAFT_LIGHT_POWER   (0.6)
#define MINECRAFT_AMBIENT_LIGHT (0.4)

vec4 linear_fog(vec4 inColor, float vertexDistance, float fogStart, float fogEnd, vec4 fogColor) {
    if (vertexDistance <= fogStart) {
        return inColor;
    }

    float fogValue = vertexDistance < fogEnd ? smoothstep(fogStart, fogEnd, vertexDistance) : 1.0;
    return vec4(mix(inColor.rgb, fogColor.rgb, fogValue * fogColor.a), inColor.a);
}

float linear_fog_fade(float vertexDistance, float fogStart, float fogEnd) {
    if (vertexDistance <= fogStart) {
        return 1.0;
    } else if (vertexDistance >= fogEnd) {
        return 0.0;
    }

    return smoothstep(fogEnd, fogStart, vertexDistance);
}

// ===== Lighting Method =====

vec4 minecraft_sample_lightmap(sampler2D lightMap, ivec2 uv) {
    return texture(lightMap, clamp(uv / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
}

// ===== Layered Color Method =====
vec4 adjust(vec4 color) {
    return clamp(color * 2, 0, 1);
}

float adjustScalar(float color) {
    return clamp(color * 2, 0.0, 1.0);
}

float getMaskIntensity() {
    return texture(mask, texCoord0).r;
}

vec3 applyEmission(vec3 base, vec3 emissionColor, float intensity) {
    return base + (emissionColor - base) * intensity;
}

vec4 layered(vec2 texCoord) {
    vec4 color = texture(diffuse, texCoord);
    vec4 layerMasks = adjust(texture(layer, texCoord));
    float maskColor = adjustScalar(getMaskIntensity());

    vec3 base = mix(color.rgb, color.rgb * baseColor1, layerMasks.r);
    base = mix(base, color.rgb * baseColor2, layerMasks.g);
    base = mix(base, color.rgb * baseColor3, layerMasks.b);
    base = mix(base, color.rgb * baseColor4, layerMasks.a);
    base = mix(base, color.rgb * baseColor5, maskColor);

    base = mix(base, applyEmission(base, emiColor1, emiIntensity1), layerMasks.r);
    base = mix(base, applyEmission(base, emiColor2, emiIntensity2), layerMasks.g);
    base = mix(base, applyEmission(base, emiColor3, emiIntensity3), layerMasks.b);
    base = mix(base, applyEmission(base, emiColor4, emiIntensity4), layerMasks.a);
    base = mix(base, applyEmission(vec3(0), emiColor5, emiIntensity5), maskColor);

    return vec4(base, color.a);
}

// ===== Masked Color Method =====
vec4 masked(vec2 texCoord) {
    vec4 outColor = texture(diffuse, texCoord);

    float mask = texture(mask, texCoord).x;
    outColor.rgb = mix(outColor.rgb, outColor.rgb * baseColor1, mask);
    return outColor;
}

vec4 getColor(vec2 texCoord) {
    if(colorMethod == 1) {
        return layered(texCoord);
    } else if(colorMethod == 2) {
        return masked(texCoord);
    }

    return texture(diffuse, texCoord);
}

// ===== Cartoon Effect =====

const float edgeThreshold = 0.08;
const float blockSize = 0.0015;

const vec2 offsets[8] = vec2[](
vec2(-1.0, -1.0), vec2(0.0, -1.0), vec2(1.0, -1.0),
vec2(-1.0,  0.0),                  vec2(1.0,  0.0),
vec2(-1.0,  1.0), vec2(0.0,  1.0), vec2(1.0,  1.0)
);

float detectEdge(vec2 uv) {
    float centerIntensity = dot(getColor(uv).rgb, vec3(0.2126, 0.7152, 0.0722));
    float diffSum = 0.0;

    for (int i = 0; i < 8; i++) {
        vec2 offsetUV = uv + offsets[i] * blockSize;
        float neighborIntensity = dot(getColor(offsetUV).rgb, vec3(0.2126, 0.7152, 0.0722));
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

vec4 cartoon(vec4 inColor) {
    float edge = detectEdge(texCoord0);

    vec3 color = bilateralFilter(texCoord0);

    return vec4(mix(color, inColor.rgb, edge), 1.0);
}

// ===== Paradox Effect =====
float getParadoxIntensity() {
    vec2 effectTexCoord = vec2(texCoord0);

    if(frame >= 0) {
        effectTexCoord *= 4.0;
        effectTexCoord = fract(effectTexCoord);

        effectTexCoord *= (0.25);
        effectTexCoord.x += (frame % 4)/4.0;
        effectTexCoord.y +=  (frame/4)/4.0;
    }

    return clamp(texture(paradoxMask, effectTexCoord).r * 2, 0.0, 1.0);
}

vec4 paradox(vec4 color) {
    return mix(color, vec4(1.0), getParadoxIntensity());
}

// ===== Galaxy Effect =====
const float darkenFactor = 0.3;

vec4 galaxy(vec4 inColor) {
    float brightness = dot(inColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    const vec3 lightGradientColor1 = vec3(0.2, 0.0, 0.3);
    const vec3 lightGradientColor2 = vec3(0.6, 0.1, 0.7);
    const float gradientThreshold = 0.5;
    vec3 gradientColor = mix(lightGradientColor1, lightGradientColor2, smoothstep(gradientThreshold, 1.0, brightness));
    inColor.rgb *= darkenFactor;
    inColor.rgb = mix(inColor.rgb, gradientColor, smoothstep(gradientThreshold, 1.0, brightness));
    return mix(inColor, vec4(1.0), getParadoxIntensity());
}

// ===== Pastel Effect =====
vec4 pastel(vec4 inColor) {
    vec2 wrappedUV = fract(texCoord0 * 5.0);

    float gradient = sin(wrappedUV.x * 3.14159) * sin(wrappedUV.y * 3.14159);

    gradient = (gradient + 1.0) * 0.5;

    vec3 pastelBlue = vec3(0.8, 0.9, 1.0);
    vec3 pastelPink = vec3(1.0, 0.8, 0.9);

    vec3 pastelColor = mix(pastelBlue, pastelPink, gradient);

    return vec4(mix(inColor.rgb, pastelColor, 0.5), inColor.a);
}

// ===== Shadow Effect =====
vec4 shadow(vec4 inColor) {
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

// ===== Sketch Effect =====

vec4 sketch(vec4 inColor) {

    const vec3 luminanceWeights = vec3(0.2126, 0.7152, 0.0722);
    const float subtleThreshold = 0.02; // Lower threshold for fine differences
    const float subtleScale = 0.0015;   // Very small blockSize to stay local

    vec3 centerColor = inColor.rgb;
    float centerIntensity = dot(centerColor, luminanceWeights);

    float diffSum = 0.0;

    for (int i = 0; i < 8; i++) {
        vec3 neighborColor = getColor(texCoord0 + offsets[i] * subtleScale).rgb;
        diffSum += abs(dot(neighborColor - centerColor, luminanceWeights));
    }

    float value = smoothstep(subtleThreshold - 0.01, subtleThreshold + 0.01, diffSum * 0.125);

    return vec4(value, value, value, inColor.a);
}

// ===== Vintage Effect =====
vec4 vintage(vec4 inColor) {
    float grayscale = 0.2126 * inColor.r + 0.7152 * inColor.g + 0.0722 * inColor.b;

    return vec4(vec3(grayscale), inColor.a);
}

vec4 process(vec4 color) {
    //cartoon
    if(effect == 1) return cartoon(color);
    //galaxy
    if(effect == 2) return galaxy(color);
    //paradox
    if(effect == 3) return paradox(color);
    //pastel
    if(effect == 4) return pastel(color);
    //shadow
    if(effect == 5) return shadow(color);
    //sketch
    if(effect == 6) return sketch(color);
    //vintage
    if(effect == 7) return vintage(color);

    return color;
}

// ===== Tersaalization Effect =====

#define TERA_LIGHT_DIRECT   vec3(0.3f, 0.9f, 0.0f)
#define TERATYPE_TINT       vec3(0.161, 0.502, 0.937)

#define FACET_RES           8.0
#define SHIMMER_BANDS       5.0
#define GAMMA_CORRECTION    1.4
#define SHIMMER_STRENGTH    0.8
#define IRIDESCENCE_STRENGTH 0.2

uniform bool useTera;

in vec3 fragViewDir;
in vec3 worldPos;

vec3 calculateTersaalizationEffect(vec3 baseColor) {
    vec3 N = normalize(cross(dFdx(worldPos), dFdy(worldPos)));
    vec3 V = normalize(fragViewDir);

    // Fresnel rim lighting
    float fresnel = pow(1.0 - max(dot(N, V), 0.0), 5.0);

    // Directional lighting response
    float lightResponse = max(dot(N, TERA_LIGHT_DIRECT), 0.0);

    // Facet simulation via quantized normals
    vec3 faceted = normalize(floor(N * FACET_RES + 0.5) / FACET_RES);
    float facetResponse = pow(max(dot(faceted, V), 0.0), 6.0);

    // Composite shimmer signal
    float shimmer = fresnel * 0.5 + lightResponse * 0.3 + facetResponse * 0.8;
    shimmer = pow(clamp(shimmer, 0.0, 1.0), GAMMA_CORRECTION);

    // Posterization
    shimmer = floor(shimmer * SHIMMER_BANDS) / SHIMMER_BANDS;

    // Micro-noise based on 3D direction
    float angleNoise = abs(sin(dot(N.xyz, vec3(12.9898, 78.233, 45.164)) * 43758.5453));
    shimmer += angleNoise * 0.02;

    // Optional iridescent variation
    vec3 iridescent = vec3(1.0, 0.9, 0.8) + vec3(0.05, -0.02, 0.03) * sin(shimmer * 20.0);

    // Tint variation based on light response
    vec3 directionalTint = mix(TERATYPE_TINT, vec3(1.0), lightResponse);

    // Final output
    return baseColor
    + directionalTint * shimmer * SHIMMER_STRENGTH
    + iridescent * shimmer * IRIDESCENCE_STRENGTH;
}
//////////////////////////////////////

void main() {
    outColor = process(getColor(texCoord0)) * ColorModulator;

    if (outColor.a < 0.004) {
        discard;
    }

    outColor.rgb *= tint;

    if(useTera) {
        outColor.rgb = calculateTersaalizationEffect(outColor.rgb);
    } else if (useLight) {
        outColor *= vertexColor;
        // Sample Minecraft's light level from the lightmap texture
        vec4 minecraftLight = minecraft_sample_lightmap(lightmap, light);

        outColor *= mix(minecraftLight, vec4(1, 1, 1, 1), texture(emission, texCoord0).r);
    }

    outColor = linear_fog(outColor, vertexDistance, FogStart, FogEnd, FogColor);
}