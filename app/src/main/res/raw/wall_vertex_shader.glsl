attribute vec2 Position;
uniform mat4 Matrix;


void main(){
gl_Position=Matrix*vec4(Position,0.0,1.0);
}