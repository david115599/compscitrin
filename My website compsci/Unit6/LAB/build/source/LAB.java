import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LAB extends PApplet {

/**
 * elevatorDemo by Andrew Rose
 * control a virtual elevator
 * uses buttons and a switch statement
 * controlP5 must be installed for this to work
 * Challenge: add a third floor
 */

//import controlP5 and create an object to add buttons



ControlP5 cp5;

int state = 1; //start on floor 1

public void setup() {
  rectMode(CENTER);
  
  background(100);

  cp5 = new ControlP5(this);

  // create a 'floor2' button
  // pressing button calls floor2 controller below
  cp5.addButton("Draw")
    .setValue(2);

  // create an 'floor1' button
  // pressing button calls floor1 controller below
  cp5.addButton("Erase")
    .setValue(1);
}

public void draw() {
  if (state == 1) {
    println("Drawing");
    fill(250);
  }
  else{
    fill(0);
  }
  ellipse(mouseX, mouseY, 1, 1);

}

public void floor1(int theValue) {
  if (state == 1) {
    println("Drawing");
    fill(250);
  }
}


public void floor2(int theValue) {
  switch (state) {
  case 2:
    println("erasing");
    fill(0);
    break;
  }
}

/*
// Constants
int Y_AXIS = 1;
int X_AXIS = 2;
color c1, c2;

void setup() {
  size(620, 640);
  background(255);

  // Define colors
  c1 = color(255);
  c2 = color(0);

  noLoop();
}

void draw() {
  setGradient(50, 190, 540, 80, c2, c1, X_AXIS);
}

void setGradient(int x, int y, float w, float h, color c1, color c2, int axis ) {

  noFill();

  if (axis == Y_AXIS) {  // Top to bottom gradient
    for (int i = y; i <= y+h; i+=33.75) {
      float inter = map(i, y, y+h, 0, 1);
      color c = lerpColor(c1, c2, inter);
      fill(c);
      rect(x, i, x+w, i);
    }
  }
  else if (axis == X_AXIS) {  // Left to right gradient
    for (int i = x; i <= x+w; i+=33.75) {
      float inter = map(i, x, x+w, 0, 1);
      color c = lerpColor(c1, c2, inter);
      fill(c);
      noStroke();
      rect(i, y, i, y+h);
    }
  }
}
*/

int size = 20;
int Y_AXIS = 1;
int X_AXIS = 2;
int c1, c2;
public void setup(){
 size (500, 300);
 smooth();
 c1 = color(255);
 c2 = color(0);
}

public void draw(){
  setGradient(0, 200, 125, 40, c2, c1, X_AXIS);
  setGradient2(300, 200, 125, 40, c2, c1, X_AXIS);

stroke(0);

  colorMode(HSB, 200);
  for (int i = 300; i < 500; i+=10) {
    for (int j = 0; j < 200; j+=10) {
      fill(i-300, j, 200);
      rect(i, j,20,20);
}
}
colorMode(HSB, 200);
for (int i = 300; i < 500; i+=10) {
  for (int j = 200; j > 100; j-=10) {
    fill(i-300, 200, 250-j);
    rect(i, j,20,20);
  }
}

noStroke();
colorMode(HSB, 200);
for (int i = 0; i < 200; i++) {
  for (int j = 0; j < 200; j++) {
    stroke(i, j*2, 200);
    point(-i+200, j);
  }
}
colorMode(HSB, 200);
for (int i = 0; i < 200; i+=1) {
  for (int j = 200; j > 100; j-=1) {
    stroke(i, 200, 250-j);
    point(-i+200, j);
  }
}
}
public void setGradient(int x, int y, float w, float h, int c1, int c2, int axis ) {

  noFill();

  if (axis == Y_AXIS) {  // Top to bottom gradient
    for (int i = y; i <= y+h; i+=1) {
      float inter = map(i, y, y+h, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      rect(x, i, x+w, i);
    }
  }
  else if (axis == X_AXIS) {  // Left to right gradient
    for (int i = x; i <= x+w; i+=1) {
      float inter = map(i, x, x+w, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      noStroke();
      rect(i, y, i, y+h);
    }
  }
}
public void setGradient2(int x, int y, float w, float h, int c1, int c2, int axis ) {

  noFill();

  if (axis == Y_AXIS) {  // Top to bottom gradient
    for (int i = y; i <= y+h; i+=10) {
      float inter = map(i, y, y+h, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      rect(x, i, x+w, i);
    }
  }
  else if (axis == X_AXIS) {  // Left to right gradient
    for (int i = x; i <= x+w; i+=10) {
      float inter = map(i, x, x+w, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      noStroke();
      rect(i, y, i, y+h);
    }
  }
}
  public void settings() {  size(400, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LAB" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
