precision mediump float;

varying vec2 varyLeftEyeBallLocation;
varying vec2 varyRightEyeBallLocation;

varying vec2 varyLeftEyeLocation;
varying vec2 varyRightEyeLocation;

varying vec2 varyPosition;

uniform float Center;
uniform float WoodWidth;

varying float varyEyeR;
varying float varyEyeBallR;
varying float varyH;
varying float varymouth;
void main(){

if((varyPosition.x<=(varyLeftEyeLocation.x+varyEyeR/2.0)&&varyPosition.x>=(varyLeftEyeLocation.x-varyEyeR/2.0))){
 gl_FragColor=vec4(1.0,1.0,1.0,1.0);
       float juli=sqrt(((varyLeftEyeLocation.x-varyPosition.x)*(varyLeftEyeLocation.x-varyPosition.x))+((varyLeftEyeLocation.y-varyPosition.y)*(varyLeftEyeLocation.y-varyPosition.y)));
       if(juli<varyEyeR/2.0){
           float eyeblljuli=sqrt((varyLeftEyeBallLocation.x-varyPosition.x)*(varyLeftEyeBallLocation.x-varyPosition.x)+(varyLeftEyeBallLocation.y-varyPosition.y)*(varyLeftEyeBallLocation.y-varyPosition.y));
           if(eyeblljuli<varyEyeBallR/2.0){
            gl_FragColor=vec4(0.0,0.0,0.0,1.0);
           }else{
            gl_FragColor=vec4(1.0,1.0,1.0,1.0);
           }

       }else{
        gl_FragColor=vec4(0.811,0.215,0.266,1.0);
       }
}else if( (varyPosition.x>=(varyRightEyeLocation.x-varyEyeR/2.0)
                     &&(varyPosition.x<=(varyRightEyeLocation.x+varyEyeR/2.0)))){
      float juli=sqrt((varyRightEyeLocation.x-varyPosition.x)*(varyRightEyeLocation.x-varyPosition.x)+(varyRightEyeLocation.y-varyPosition.y)*(varyRightEyeLocation.y-varyPosition.y));
          if(juli<varyEyeR/2.0){


                    float eyeblljuli=sqrt((varyRightEyeBallLocation.x-varyPosition.x)*(varyRightEyeBallLocation.x-varyPosition.x)+(varyRightEyeBallLocation.y-varyPosition.y)*(varyRightEyeBallLocation.y-varyPosition.y));
                              if(eyeblljuli<varyEyeBallR/2.0){
                               gl_FragColor=vec4(0.0,0.0,0.0,1.0);
                              }else{
                               gl_FragColor=vec4(1.0,1.0,1.0,1.0);
                              }


                 }else{
                  gl_FragColor=vec4(0.811,0.215,0.266,1.0);
                 }
 }
 else if(varyPosition.x>-varyEyeBallR/2.0&&varyPosition.x<varyEyeBallR/2.0){
    float locationceter=((-varyH/2.0)+varyEyeR/2.0);
    float location=(locationceter+varymouth);


       if(locationceter+varymouth>=locationceter){
             if(varyPosition.y>=locationceter&&varyPosition.y<location){
             gl_FragColor=vec4(0.294,0.047,0.247,1.0);
             }else{
             gl_FragColor=vec4(0.811,0.215,0.266,1.0);
             }

       }else{
             if(varyPosition.y<=locationceter&&varyPosition.y>location){
                          gl_FragColor=vec4(0.294,0.047,0.247,1.0);
                          }else{
                          gl_FragColor=vec4(0.811,0.215,0.266,1.0);
                          }
       }
 }
 else{
 gl_FragColor=vec4(0.811,0.215,0.266,1.0);
 }


}