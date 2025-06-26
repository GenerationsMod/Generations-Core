uniform sampler2D mask;

uniform vec3 color;

vec4 getColor(vec2 texCoord) {
    vec4 outColor = texture(diffuse, texCoord);
    float mask = texture(mask, texCoord).x;
    outColor.rgb = mix(outColor.rgb, outColor.rgb * color, mask);
    return outColor;
}