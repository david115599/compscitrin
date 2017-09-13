
var y = 0;

var bg = loadImage("House.png");

function setup() {


  createCanvas(300, 300);
}

function draw() {


  stroke(226, 204, 0);
  line(0, -y, width, -y);
  background("bg")
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


