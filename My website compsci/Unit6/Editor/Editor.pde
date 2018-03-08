/**
 * elevatorDemo by Andrew Rose
 * control a virtual elevator
 * uses buttons and a switch statement
 * controlP5 must be installed for this to work
 * Challenge: add a third floor
 */

//import controlP5 and create an object to add buttons
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
