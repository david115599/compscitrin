import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Editor extends PApplet {

/**
 * elevatorDemo by Andrew Rose
 * control a virtual elevator
 * uses buttons and a switch statement
 * controlP5 must be installed for this to work
 * Challenge: add a third floor
 */

//import controlP5 and create an object to add buttons
/*
import controlP5.*;

ControlP5 cp5;

int state = 1; //start on floor 1

void setup() {
  rectMode(CENTER);
  size(400, 600);
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

void draw() {
  if (state == 1) {
    println("Drawing");
    fill(250);
  }
  else{
    fill(0);
  }
  ellipse(mouseX, mouseY, 1, 1);

}

void floor1(int theValue) {
  if (state == 1) {
    println("Drawing");
    fill(250);
  }
}


void floor2(int theValue) {
  switch (state) {
  case 2:
    println("erasing");
    fill(0);
    break;
  }
}
*/

// Constants
int Y_AXIS = 1;
int X_AXIS = 2;
int c1, c2;

public void setup() {
  
  background(255);

  // Define colors
  c1 = color(255);
  c2 = color(0);

  noLoop();
}

public void draw() {
  setGradient(50, 190, 540, 80, c2, c1, X_AXIS);
}

public void setGradient(int x, int y, float w, float h, int c1, int c2, int axis ) {

  noFill();

  if (axis == Y_AXIS) {  // Top to bottom gradient
    for (int i = y; i <= y+h; i+=33.75f) {
      float inter = map(i, y, y+h, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      rect(x, i, x+w, i);
    }
  }
  else if (axis == X_AXIS) {  // Left to right gradient
    for (int i = x; i <= x+w; i+=33.75f) {
      float inter = map(i, x, x+w, 0, 1);
      int c = lerpColor(c1, c2, inter);
      fill(c);
      noStroke();
      rect(i, y, i, y+h);
    }
  }
}
  public void settings() {  size(620, 640); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Editor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
