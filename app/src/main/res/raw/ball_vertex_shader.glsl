attribute vec2 Position;
uniform mat4 Matrix;
uniform int Boom;
void main(){
gl_Position=Matrix*vec4(Position,0.0,1.0);
if(Boom==0){
gl_PointSize=20.0;
}else if(Boom==1){
gl_PointSize=25.0;
}else if(Boom==2){
gl_PointSize=30.0;
}else if(Boom==3){
 gl_PointSize=35.0;
 }
}