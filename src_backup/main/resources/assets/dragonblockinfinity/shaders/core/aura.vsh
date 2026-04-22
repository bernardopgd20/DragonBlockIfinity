#version 110

attribute vec3 Position;
attribute vec4 Color;

varying vec3 vNormalWorld;
varying vec3 v_viewDir;
varying float vHeight;

uniform mat4 modelMatrix;
uniform mat4 ProjMat;
uniform float time;
uniform float auravar;
uniform float speed;
uniform float amplitude;

float triangleWave(float x) { return abs(fract(x) * 2.0 - 1.0); }

void main() {
    vec4 pos = vec4(Position, 1.0);
    float angle = atan(Position.z, Position.x);
    float freqY = 2.0;

    float angularWave = triangleWave(angle * 2.0);
    float layer = triangleWave((Position.y * freqY) - (time * speed));
    float totalWave = layer * angularWave;
    float falloff = smoothstep(-1.8, 0.0, Position.y) * (1.0 - smoothstep(0.0, 2.0, Position.y));
    float smooth_auravar = auravar * auravar * (3.0 - 2.0 * auravar);

    pos.xz *= smooth_auravar;
    vec3 dir = normalize(pos.xyz);
    vec3 upDir = vec3(0.0, 1.0, 0.0);
    vec3 finalDir = normalize(mix(dir, upDir, 0.55));
    pos.xyz += finalDir * (totalWave * amplitude * falloff) * pow(auravar, 6.0);

    vec4 viewPos = modelMatrix * vec4(pos.xyz, 1.0);
    vNormalWorld = normalize(pos.xyz);
    v_viewDir = -viewPos.xyz;
    vHeight = Position.y;
    gl_Position = ProjMat * viewPos;
}
