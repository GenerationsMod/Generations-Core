#version 430
layout(local_size_x = 16, local_size_y = 16) in;

const vec2 outputSize = vec2(1024.0);

layout(rgba8, binding = 0) uniform image2D solidTex;
layout(rgba8, binding = 1) uniform image2D litTex;

uniform sampler2D diffuse;
uniform sampler2D emission;
uniform sampler2D layer;
uniform sampler2D mask;
uniform sampler2D paradoxTexture;

layout(std140, binding = 0) uniform Material {
    vec3 baseColor1;
    vec3 baseColor2;
    vec3 baseColor3;
    vec3 baseColor4;
    vec3 baseColor5;

    vec3 emiColor1;
    vec3 emiColor2;
    vec3 emiColor3;
    vec3 emiColor4;
    vec3 emiColor5;

    float emiIntensity1;
    float emiIntensity2;
    float emiIntensity3;
    float emiIntensity4;
    float emiIntensity5;

    int colorMethod;
    int effect;
    bool paradox;
};

vec4 adjust(vec4 color) {
    return clamp(color * 2.0, 0.0, 1.0);
}

float adjustScalar(float color) {
    return clamp(color * 2.0, 0.0, 1.0);
}

vec3 applyEmission(vec3 base, vec3 emissionColor, float intensity) {
    return base + (emissionColor - base) * intensity;
}

vec4 layered(vec2 uv) {
    vec4 color = texture(diffuse, uv);
    vec4 layerMasks = adjust(texture(layer, uv));
    float maskColor = adjustScalar(texture(mask, uv).r);

    vec3 base = mix(color.rgb, color.rgb * baseColor1, layerMasks.r);
    base = mix(base, color.rgb * baseColor2, layerMasks.g);
    base = mix(base, color.rgb * baseColor3, layerMasks.b);
    base = mix(base, color.rgb * baseColor4, layerMasks.a);
    base = mix(base, color.rgb * baseColor5, maskColor);

    base = mix(base, applyEmission(base, emiColor1, emiIntensity1), layerMasks.r);
    base = mix(base, applyEmission(base, emiColor2, emiIntensity2), layerMasks.g);
    base = mix(base, applyEmission(base, emiColor3, emiIntensity3), layerMasks.b);
    base = mix(base, applyEmission(base, emiColor4, emiIntensity4), layerMasks.a);
    base = mix(base, applyEmission(vec3(0.0), emiColor5, emiIntensity5), maskColor);

    return vec4(base, color.a);
}

vec4 masked(vec2 uv) {
    vec4 color = texture(diffuse, uv);
    float maskColor = texture(mask, uv).r;
    color.rgb = mix(color.rgb, color.rgb * baseColor1, maskColor);
    return color;
}

vec4 baseColor(vec2 uv) {
    if (colorMethod == 0) return texture(diffuse, uv);
    else if (colorMethod == 1) return layered(uv);
    else if (colorMethod == 2) return masked(uv);
    else return texture(diffuse, uv);
}

// Galaxy
const float darkenFactor = 0.3;

vec4 galaxy(vec4 color) {
    float brightness = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));

    const vec3 lightGradientColor1 = vec3(0.2, 0.0, 0.3);
    const vec3 lightGradientColor2 = vec3(0.6, 0.1, 0.7);
    const float gradientThreshold = 0.5;

    vec3 gradientColor = mix(
    lightGradientColor1,
    lightGradientColor2,
    smoothstep(gradientThreshold, 1.0, brightness)
    );

    color.rgb *= darkenFactor;
    color.rgb = mix(
    color.rgb,
    gradientColor,
    smoothstep(gradientThreshold, 1.0, brightness)
    );

    return color;
}

// Pastel
vec4 pastel(vec4 inColor, vec2 uv) {
    vec2 wrappedUV = fract(uv * 5.0);

    float gradient = sin(wrappedUV.x * 3.14159) * sin(wrappedUV.y * 3.14159);
    gradient = (gradient + 1.0) * 0.5;

    vec3 pastelBlue = vec3(0.8, 0.9, 1.0);
    vec3 pastelPink = vec3(1.0, 0.8, 0.9);

    vec3 pastelColor = mix(pastelBlue, pastelPink, gradient);

    return vec4(mix(inColor.rgb, pastelColor, 0.5), inColor.a);
}

// Shadow
vec4 shadow(vec4 inColor, vec2 uv) {
    float grayscale = dot(inColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    vec3 base = vec3(grayscale);

    vec2 wrappedUV = fract(uv * 5.0);
    float gradient = sin(wrappedUV.x * 3.14159) * sin(wrappedUV.y * 3.14159);
    gradient = (gradient + 1.0) * 0.5;

    vec3 deepPurpleBlue = vec3(0.1, 0.1, 0.2);
    vec3 darkerShade    = vec3(0.05, 0.05, 0.1);

    vec3 shadowColor = mix(deepPurpleBlue, darkerShade, gradient);

    vec3 finalColor = mix(base, shadowColor, 0.7);
    finalColor = clamp(finalColor * 0.9, 0.0, 1.0);

    return vec4(finalColor, inColor.a);
}

// Sketch (portable Sobel on diffuse luminance)
float luminanceAt(vec2 uv) {
    return dot(texture(diffuse, uv).rgb, vec3(0.2126, 0.7152, 0.0722));
}

vec3 sketchRGB(vec2 uv, vec2 texel) {
    float tl = luminanceAt(uv + texel * vec2(-1.0, -1.0));
    float  t = luminanceAt(uv + texel * vec2( 0.0, -1.0));
    float tr = luminanceAt(uv + texel * vec2( 1.0, -1.0));
    float  l = luminanceAt(uv + texel * vec2(-1.0,  0.0));
    float  c = luminanceAt(uv + texel * vec2( 0.0,  0.0));
    float  r = luminanceAt(uv + texel * vec2( 1.0,  0.0));
    float bl = luminanceAt(uv + texel * vec2(-1.0,  1.0));
    float  b = luminanceAt(uv + texel * vec2( 0.0,  1.0));
    float br = luminanceAt(uv + texel * vec2( 1.0,  1.0));

    float gx = (-1.0 * tl) + ( 1.0 * tr)
    + (-2.0 *  l) + ( 2.0 *  r)
    + (-1.0 * bl) + ( 1.0 * br);

    float gy = (-1.0 * tl) + (-2.0 *  t) + (-1.0 * tr)
    + ( 1.0 * bl) + ( 2.0 *  b) + ( 1.0 * br);

    float edge = length(vec2(gx, gy));
    float outline = 1.0 - smoothstep(0.10, 0.25, edge);

    vec3 baseGray = vec3(c);
    vec3 edgeColor = vec3(0.0);

    return mix(baseGray, edgeColor, outline);
}

// Vintage
vec4 vintage(vec4 inColor) {
    float grayscale = dot(inColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    return vec4(vec3(grayscale), inColor.a);
}

vec4 process(vec4 color, vec2 uv, vec2 texel) {
    if (effect == 0) return color;
    else if (effect == 1) return galaxy(color);
    else if (effect == 2) return pastel(color, uv);
    else if (effect == 3) return shadow(color, uv);
    else if (effect == 4) return vec4(sketchRGB(uv, texel), color.a);
    else if (effect == 5) return vintage(color);
    else return color;
}

void main() {
    ivec2 pixel = ivec2(gl_GlobalInvocationID.xy);
    vec2 uv = (vec2(pixel) + 0.5) / outputSize;
    vec2 texel = 1.0 / outputSize;

    vec4 color = baseColor(uv);
    color = process(color, uv, texel);

    if (paradox) {
        color.rgb = mix(color.rgb, vec3(1.0), texture(paradoxTexture, uv).r);
    }

    float emiAlpha = texture(emission, uv).r * color.a;

    imageStore(solidTex, pixel, color);
    imageStore(litTex,   pixel, vec4(color.rgb, emiAlpha));
}
