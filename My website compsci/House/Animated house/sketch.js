var bg;
var y = 0;

function setup() {
  // The background image must be the same size as the parameters
  // into the createCanvas() method. In this program, the size of
  // the image is 720x400 pixels.
  bg = loadImage("House.png");
  createCanvas(720, 400);
}

function draw() {
  background(bg);

  stroke(226, 204, 0);
  line(0, y, width, y);

  y++;
  if (y > height) {
    y = 0;
      ellipse(100, 100, 80, 80);
      ellipse(150, 150, 80, 80);
      ellipse(100, 50, 80, 80);
      ellipse(200, 100, 80, 80);
      ellipse(250, 150, 80, 80);
   
  }
}


