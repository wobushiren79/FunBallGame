attribute vec2 Position;
uniform mat4 Matrix;

varying vec2 varyPosition;


uniform vec2 LeftEyeLocation;
uniform vec2 RightEyeLocation;

uniform float H;

uniform vec2 BallLocation;


uniform float EyeR;
uniform float EyeBallR;


varying vec2 varyLeftEyeBallLocation;
varying vec2 varyRightEyeBallLocation;

varying vec2 varyLeftEyeLocation;
varying vec2 varyRightEyeLocation;

varying float varyEyeR;
varying float varyEyeBallR;

varying float varymouth;
varying float varyH;
void main(){

vec4 Location=Matrix*vec4(Position,0.0,1.0);
gl_Position=Location;

 vec2 leftVetor=vec2(BallLocation.x-LeftEyeLocation.x,BallLocation.y-LeftEyeLocation.y);
 vec2 rightVetor=vec2(BallLocation.x-RightEyeLocation.x,BallLocation.y-RightEyeLocation.y);

 float leftlen=sqrt((leftVetor.x*leftVetor.x)+(leftVetor.y*leftVetor.y));
 float rightlen=sqrt(rightVetor.x*rightVetor.x+rightVetor.y*rightVetor.y);

 float eyeballlen=EyeR/2.0-EyeBallR/2.0;

 vec2 LeftEyeBallVetor=vec2((eyeballlen/leftlen)*leftVetor.x,(eyeballlen/leftlen)*leftVetor.y);
 vec2  RightEyeBallVetor=vec2((eyeballlen/rightlen)*rightVetor.x,(eyeballlen/rightlen)*rightVetor.y);

  vec2 LeftEyeBallLocation=vec2(LeftEyeBallVetor.x+LeftEyeLocation.x,LeftEyeBallVetor.y+LeftEyeLocation.y);
  vec2 RightEyeBallLocation=vec2(RightEyeBallVetor.x+RightEyeLocation.x,RightEyeBallVetor.y+RightEyeLocation.y);

 float mouth=(EyeR/H)*BallLocation.y;
 varymouth=mouth;

varyH=H;
//varyPosition=vec2(Location.x,Location.y);
varyPosition=Position;

varyLeftEyeBallLocation=LeftEyeBallLocation;
varyRightEyeBallLocation=RightEyeBallLocation;

varyLeftEyeLocation=LeftEyeLocation;
varyRightEyeLocation=RightEyeLocation;

varyEyeR=EyeR;
varyEyeBallR=EyeBallR;
}