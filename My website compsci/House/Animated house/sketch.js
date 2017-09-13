
var y = 0;

function setup() {
  // The background image must be the same size as the parameters
  // into the createCanvas() method. In this program, the size of
  // the image is 720x400 pixels.

  createCanvas(300, 300);
}

function draw() {


  //stroke(226, 204, 0);
  //line(0, y, width, y);
  background(255)
  fill(0);
  ellipse(100, y+100, 80, 80);
  ellipse(150, y+150, 80, 80);
  ellipse(100, y+50, 80, 80);
  ellipse(200, y+100, 80, 80);
  ellipse(250, y+150, 80, 80);
  fill(255,0,0);
  ellipse(120, y+35, 5, 5);
  ellipse(80, y+35, 5, 5);
  ellipse(100, y+50, 20, 40);


  y++;
  if (y > height) {
    y = 0;

  }
}


