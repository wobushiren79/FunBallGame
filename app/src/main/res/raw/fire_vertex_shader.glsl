uniform mat4 Matrix;
uniform float Time;

attribute vec3 Position;
attribute vec3 Color;
attribute vec3 Vector;
attribute float StartTime;


varying vec3 VaryColor;
varying float ElapsedTime;


void main(){
 VaryColor=Color;
 ElapsedTime=Time-StartTime;
  float gravityFactor=(ElapsedTime*ElapsedTime/8.0)*300.0;
  vec3 currentPosition=Position+(Vector*ElapsedTime)*300.0;
  currentPosition.y-=gravityFactor;
  gl_Position=Matrix*vec4(currentPosition,1.0);
 gl_PointSize=10.0;
}