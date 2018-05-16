/**
 * ControlP5 Button
 * this example shows how to create buttons with controlP5.
 * 
 * find a list of public methods available for the Button Controller 
 * at the bottom of this sketch's source code
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/controlp5
 *
 */

import controlP5.*;

ControlP5 cp5;

PImage tiger;
boolean bw;
boolean bw2;
boolean bw3;
int way =1;
int gono =1;
float noiseScale = 0.02;
float increment = 0.02;

void setup() {
  size(757,568); 
  tiger = loadImage("Tiger.jpeg");
  image(tiger,0,0);
   loadPixels();
    randomCanvas();
     updatePixels();
  tiger.loadPixels();

  avg();
    
    
  cp5 = new ControlP5(this);

     
   cp5.addSlider("posterize")
     .setPosition(100, 40)
     .setSize(200, 19)
     .setRange(0,255)
     .setValue(255/2)
     ;

    cp5.addSlider("contrast")
     .setPosition(100, 60)
     .setSize(200, 19)
     .setRange(0,255/2)
     .setValue(0)
     ;
     
   cp5.addSlider("invert")
     .setPosition(100, 20)
     .setSize(200, 19)
     .setRange(0,255)
     .setValue(255)
     ;
     
   cp5.addButton("invertBTN")
     .setPosition(50, 20)
     .setSize(40, 19)
     ;
     
   cp5.addButton("posterizeBTN")
     .setPosition(50, 40)
     .setSize(40, 19)
     ;
     
   cp5.addButton("contrastBTN")
     .setPosition(50, 60)
     .setSize(40, 19)
     ;
     
     
   cp5.addButton("avg")
     .setPosition(50, 80)
     .setSize(40, 19)
     ;
     
     
   cp5.addSlider("nightmodegain")
     .setPosition(100, 80)
     .setSize(200, 19)
     .setRange(-255,0)
     .setValue(-255)
     ;
    /* 
    cp5.addSlider("nightmodenoise")
     .setPosition(100, 100)
     .setSize(200, 19)
     .setRange(0,255)
     .setValue(0)
     ;
    */
    
   
  
  
}

void draw() {

}


void avg() {
  loadPixels();
  for (int i = 0; i < pixels.length; i++) {
      pixels[i] = (tiger.pixels[i]*pixels[i])/2;
  }
  updatePixels();
}

void nightmodegain(int val) {
  
  println("nightmode");
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
   
   
    r = r - brightness(tiger.pixels[i])+val; //more red
    g += val;
   b = b - brightness(tiger.pixels[i]) +val;  //less red
    
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}



void randomCanvas() {
  for (int i = 0; i < pixels.length; i++) {
    pixels[i] = color(random(2));
  }

}

void posterize(int val) {
  
  println("a slider event from posterize: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);

  
  if (bw == false) {
    r = int(red(tiger.pixels[i])/val) * val;
    g = int(green(tiger.pixels[i])/val) * val;
    b = int(blue(tiger.pixels[i])/val) * val;
  }
  
  else {
    r = int(brightness(tiger.pixels[i])/val) * val;
    g = int(brightness(tiger.pixels[i])/val) * val;
    b = int(brightness(tiger.pixels[i])/val) * val;
  }
    
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}

void contrast(int val) {
  
  println("a slider event from contrast: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);

  
  if (bw2 == false) {
    if (r > 255/2) {
    r += val;
    }
    
    else {
       r -= val;
    }
     if (g > 255/2) {
    g += val;
    }
    
    else {
       g -= val;
    }
     if (b > 255/2) {
    b += val;
    }
    
    else {
       b -= val;
    }
  }
  
  else {
    if (r > 255/2) {
    r = int(brightness(tiger.pixels[i])+ val);
    }
    else {
     r = int(brightness(tiger.pixels[i])- val);
    }
       if (g > 255/2) {
    g = int(brightness(tiger.pixels[i])+ val);
    }
    else {
     g = int(brightness(tiger.pixels[i])- val);
    }
       if (b > 255/2) {
    b = int(brightness(tiger.pixels[i])+ val);
    }
    else {
     b = int(brightness(tiger.pixels[i])- val);
    }
  }
    
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}

void invert(int val) {
  
  println("INVERT");
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
   
  
  if (bw3 == false) {
    r = int(val - red( tiger.pixels[i]));
    g = int(val - green(tiger.pixels[i]));
    b = int(val - blue(tiger.pixels[i]));
  }
  
  else {
    r = int(val - brightness(tiger.pixels[i]));
    g = int(val - brightness(tiger.pixels[i]));
    b = int(val - brightness(tiger.pixels[i]));
  }
    
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}

/*void nightmodenoise(int val) {
  
  println("nightmode");
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
   
 pixels[i] = color(random(256),val);
  }
  updatePixels();
}
*/


void posterizeBTN(){
  bw =! bw;
}

void contrastBTN(){
  bw2 =! bw2;
}

void invertBTN(){
  bw3 =! bw3;
}
