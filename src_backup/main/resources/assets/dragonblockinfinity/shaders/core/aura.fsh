#version 110

varying vec3 vNormalWorld;
varying vec3 v_viewDir;
varying float vHeight;

uniform vec3  color1;
uniform vec3  color2;
uniform float alp1;
uniform float alp2;
uniform float power;
uniform float divis;
uniform float heightFade;

void main() {
    vec3 N = normalize(vNormalWorld);
    vec3 V = normalize(v_viewDir);
    float facingRaw = dot(V, N);
    if (facingRaw < 0.0) N = -N;
    float facingAbs = abs(dot(V, N));
    float edgeFactor = pow(1.0 - facingAbs, power) / divis;
    float blend = clamp(edgeFactor, 0.0, 1.0);
    float topFade = 1.0 - smoothstep(0.5, 1.0, vHeight * heightFade);
    vec3 color = mix(color1, color2, blend);
    float alpha = mix(alp1, alp2, blend);
    if (facingRaw < 0.0) alpha = 0.01;
    alpha *= topFade;
    gl_FragColor = vec4(color, alpha);
}
