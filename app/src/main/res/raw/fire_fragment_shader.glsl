precision mediump float;

varying vec3 VaryColor;
varying float ElapsedTime;


void main(){
//   if(ElapsedTime<=VaryHightTime){
//    gl_FragColor=vec4(VaryColor,1.0);
//   }else{
//    gl_FragColor=vec4(VaryColor/(ElapsedTime-VaryHightTime),1.0);
//   }
// gl_FragColor=vec4(VaryColor,1.0);

 float xDistance=0.5-gl_PointCoord.x;
 float yDistance=0.5-gl_PointCoord.y;
 float distanceFromCenter=sqrt(xDistance*xDistance+yDistance*yDistance);
 if(distanceFromCenter>0.5){
   discard;
 }else{
 gl_FragColor=vec4(VaryColor,1.0);
 }

}