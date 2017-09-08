function setup() {
  createCanvas(640, 480);
}

function draw() {
  if (mouseIsPressed) {
    fill(60);
  } else {
    fill(23);
  }
  ellipse(mouseX, mouseY, 80, 80);
}