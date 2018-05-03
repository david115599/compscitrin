#ifdef GL_ES
precision mediump float;
#endif
uniform vec2 u_resolution;
uniform int u_LEVEL;
uniform sampler2D u_tex;
ivec2 pix;
void main () {
pix.xy = ivec2(gl_FragCoord.xy);
pix.y=int(u_resolution.y)-pix.y;
pix+=(u_LEVEL/2-(pix%u_LEVEL));
gl_FragColor = texelFetch(u_tex,pix,0);
}
