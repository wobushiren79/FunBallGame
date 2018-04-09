attribute vec2 Position;
uniform mat4 Matrix;
uniform int Boom;
void main(){
float temp=10.0;
if(Boom==0){
gl_Position=Matrix*vec4(Position,0.0,1.0);
}else if(Boom==1){
  gl_Position=Matrix*vec4(Position+temp,0.0,1.0);
}else if(Boom==2){
 gl_Position=Matrix*vec4(Position.x-temp,Position.y+temp,0.0,1.0);
}else if(Boom==3){
 gl_Position=Matrix*vec4(Position-temp,0.0,1.0);
}else if(Boom==4){
    gl_Position=Matrix*vec4(Position.x+temp,Position.y-temp,0.0,1.0);
}else if(Boom==5){
     gl_Position=Matrix*vec4(Position,0.0,1.0);
 }

}