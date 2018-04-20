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

void setup() {
  size(757,568); //dimensions under Get Info
  tiger = loadImage("Tiger.jpeg");
  image(tiger,0,0);
  cp5 = new ControlP5(this);

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
              .setPosition(100, 80)
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
