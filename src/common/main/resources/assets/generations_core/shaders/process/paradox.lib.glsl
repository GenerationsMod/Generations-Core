uniform sampler2D paradoxMask;
uniform int frame;

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

vec4 process(vec4 color) {
    return mix(color, vec4(1.0), getParadoxIntensity());
}
