#version 110


vec4 color;
varying vec3 vNormalWorld;
varying vec3 v_viewDir;
varying float vHeight;
uniform float time;
void main() {
    float intensity = pow(max(0.0, dot(normalize(vNormalWorld), normalize(v_viewDir))), 2.0);
    float alpha = smoothstep(0.0, 1.0, intensity) * smoothstep(0.0, 1.0, vHeight) * (1.0 - smoothstep(1.0, 2.0, vHeight));
    color = vec4(1.0, 0.5, 0.0, alpha);
    gl_FragColor = color;
    
}