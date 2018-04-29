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
PImage img;

void setup() {
  size(757,568); //dimensions under Get Info
  tiger = loadImage("Tiger.jpeg");
  image(tiger,0,0);
  cp5 = new ControlP5(this);

  pixelDensity(1);//deactivate HD Retina
  //size(757, 568); //dimensions under Get Info
  img = loadImage("Tiger.jpeg");
  image(img, 0, 0); //draw Tiger to canvas

  cp5.addSlider("light")
  .setPosition(100, 20)
  .setSize(200, 19)
  .setRange(-255,255)
  .setValue(0)
  ;
  cp5.addSlider("warmth")
  .setPosition(100, 50)
  .setSize(200, 19)
  .setRange(-255,255)
  .setValue(0)
  ;
  cp5.addSlider("opacity")
  .setPosition(100, 80)
  .setSize(200, 19)
  .setRange(-255,255)
  .setValue(0)
  ;
  cp5.addSlider("greyscale")
  .setPosition(100, 110)
  .setSize(200, 19)
  .setRange(-255,255)
  .setValue(0)
  ;
  cp5.addSlider("threshold")
  .setPosition(100, 140)
  .setSize(200, 19)
  .setRange(-255,255)
  .setValue(0)
  ;

}

void draw() {
  //buffering
}

// function colorB will receive changes from
// controller with name colorB
void light(int val) {
  println("a slider event from brightness: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
    r += val;
    g += val;
    b += val;
    // Constrain RGB to between 0-255
    r = constrain(r,0,255);
    g = constrain(g,0,255);
    b = constrain(b,0,255);
    // Make a new color and set pixel in the window
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}
void warmth(int val) {
  println("a slider event from brightness: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
    r += val;
    b -= val;
    // Constrain RGB to between 0-255
    r = constrain(r,0,255);
    g = constrain(g,0,255);
    b = constrain(b,0,255);
    // Make a new color and set pixel in the window
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}
void opacity(int val) {
  println("a slider event from brightness: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
    float a = alpha  (tiger.pixels[i]);
    a += val;
    // Constrain RGB to between 0-255
    r = constrain(r,0,255);
    g = constrain(g,0,255);
    b = constrain(b,0,255);
    a = constrain(a,0,255);
    // Make a new color and set pixel in the window
    color c = color(r,g,b,a);
    pixels[i] = c;
  }
  updatePixels();
}
void greyscale(int val) {
  println("a slider event from brightness: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
    r += val;
    b += val;
    g += val;
    // Constrain RGB to between 0-255
    r = constrain(r,0,255);
    g = constrain(g,0,255);
    b = constrain(b,0,255);
    // Make a new color and set pixel in the window
    color c = color(r);
    pixels[i] = c;
  }
  updatePixels();
}
void threshold(int val) {
  println("a slider event from brightness: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);
    r += val;
    b += val;
    g += val;
    // Constrain RGB to between 0-255
    r = constrain(r,0,255);
    g = constrain(g,0,255);
    b = constrain(b,0,255);
    if(r>127.5){
      pixels[i] = color(255);
    }
    if(r<127.5){
      pixels[i] = color(0);
    }
    // Make a new color and set pixel in the window


  }
  updatePixels();
}
void keyPressed() {
  if (keyCode == DOWN)
  down();
  else if (keyCode == UP)
  up();
  else if (keyCode == ENTER)
  image(img, 0, 0); //draw Tiger to canvas
  else if (keyCode == LEFT)
  left();
  else if (keyCode == RIGHT)
  right();
  else if (keyCode == 81)
  q();
  else if (keyCode == 69)
  e();
}
void down() {
  hFlip(true);
}

void up() {
  hFlip(false);
}
void right() {
  lFlip(true);
}

void left() {
  lFlip(false);
}
void q() {
  dFlip(true);
}

void e() {
  dFlip(false);
}
void hFlip(boolean down) {
  loadPixels();
  //only need to access top half of image
  for (int y = 0; y < height/2; y++) { // for each row
    for (int x = 0; x < width; x++) {  // for each pixel in row
      if (down)
      pixels[x+(height-y-1)*width] = pixels[x+(y*width)];
      else
      pixels[x+(y*width)] = pixels[x+(height-y-1)*width];
      } //swap color information for each pixel
    }
    updatePixels();
  }
  void lFlip(boolean down) {
    loadPixels();
    //only need to access top half of image
    for (int y = 0; y < height; y++) { // for each row
      for (int x = 0; x < width/2; x++) {  // for each pixel in row
        int xn = width-x-1;
        if (down)
        pixels[x+(y*width)] = pixels[xn+(y*width)];
        else
        pixels[xn+(y*width)] = pixels[x+(y*width)] ;
        } //swap color information for each pixel
      }
      updatePixels();
    }
    void dFlip(boolean down) {
      loadPixels();
      //only need to access top half of image
      for (int x = 0; x < width; x++) {

        float s = float(height)/float(width);
        int uy =int(x*s);
        println(x,uy,height/width,s,height,width);
        for (int y = 0; y <uy; y++) { // for each row
          // for each pixel in row

          float xm = ((y+s*x)/2*s);
          int xn = int(y/s);
          int yn = int(s*x);
          println(xn);
          println(yn);
          if (down)
          pixels[xn+(yn*width)] = pixels[(x+(y*width))];
          else{
            pixels[(x+(y*width))] = pixels[xn+(yn*width)];
          }
          } //swap color information for each pixel
        }
        updatePixels();
      }
