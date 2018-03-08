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

public class Editor extends PApplet {

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
  public void settings() {  size(400, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Editor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
