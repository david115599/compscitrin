var r = (10);
var a = (0);
var b = (0);
var i = 0;
var slider;
var bx;
var by;
var a1 = 0; //angle
var x1 = 0; //triangle coordinate x
var y1 = 0; //triangle coordinate y
var mx = 0;
var my = 0;
var xint = 0;
var movement = .01;
var triangleMove = 1;
var pause;
var numSides = 3;
var ta = 0;
var snap;
var angle;

function setup() {
  createCanvas(1000, 500);

  //creates slider
  slider = createSlider(0, 500, 200);
  slider.position(550, 15);
  slider.style('width', '80px');
}

function draw() {
  background(150, 150, 150);
  translate(500, 25);
  fill(0, 255, 0);
  angle = a1 * (180 / PI);
  if (a1 * (180 / PI) > 90) {
    angle = 90 - (angle - 90);
  }
  //writes text on right side of screen
  textSize(32);
  text(nf(("(x-")) + ((mx - height / 2) / (25 / 2)) + (")^2+(y-") + ((my - height / 2) / (25 / 2)) + (")^2=") + nf((((r / 2) / (25 / 2)) * ((r / 2) / (25 / 2))), 3, 4), 20, 30);
  text(("x" + ("=") + ((r / 2)) + ("cos(") + nf(a1 / 2, 1, 2) + (")+") + ((mx / (25 / 2) - 20))), 20, 60);
  text(("y" + ("=") + ((r / 2)) + ("sin(") + nf(a1 / 2, 1, 2) + (")+") + (20 - (my / (25 / 2)))), 20, 90);
  text(("Area = ") + nf(PI * (((r / 2) / (25 / 2)) * ((r / 2) / (25 / 2))), 4, 1), 20, 120);
  text(("Circumference = ") + nf(PI * (2 * ((r / 2) / (25 / 2))), 4, 1), 20, 150);
  text(("Angle = ") + (angle), 20, 180);
  text("Press Spacebar to pause.", 20, 210);
  text("Press S to snap to grid.", 20, 240);
  translate(-500, -25);
  drawGrid(25);
  fill(255, 0, 0);
  polygon(mx, my, r / 2, numSides, i += movement);
  fill(0, 255, 0);
  ellipse(mx, my, r, r);
  yint = my + sqrt((r / 2) * (r / 2) - (250 - mx) * (250 - mx));
  yint1 = my - sqrt((r / 2) * (r / 2) - (250 - mx) * (250 - mx));
  xint = mx + sqrt((r / 2) * (r / 2) - (250 - my) * (250 - my));
  xint1 = mx - sqrt((r / 2) * (r / 2) - (250 - my) * (250 - my));
  fill(255, 0, 200);
  ellipse(250, yint, 10, 10);
  ellipse(250, yint1, 10, 10);
  ellipse(xint, 250, 10, 10);
  ellipse(xint1, 250, 10, 10);
  textSize(10)
  text("(0," + nf((yint - 250) / (500 / 40), 2, 2) + ")", 260, yint);
  text("(0," + nf((yint1 - 250) / (500 / 40), 2, 2) + ")", 260, yint1);
  text("(0," + nf((xint - 250) / (500 / 40), 2, 2) + ")", xint, 240);
  text("(0," + nf((xint1 - 250) / (500 / 40), 2, 2) + ")", xint1, 240);
  text("(" + nf((x1 - 250) / (25 / 2), 2, 2) + nf((y1 - 250) / (25 / 2), 2, 2) + ")", x1 + mx, y1 + my);
  fill(255, 0, 0);
  fill(255, 0, 0);
  polygon(mx, my, r / 2, numSides, i += movement);
  drawRight();
  fill(0, 255, 0);
  textSize(10);
  text("(" + ((mx - 250) / (25 / 2)) + "," + ((my - 250) / (25 / 2)) + ")", (mx), (my));

  //allows slider to change radius
  r = slider.value();
  //helps pause animation
  if (a1 <= 3.12 && pause == false) {

    a1 += .01;

  } else {
    a1 = 0;
  }
}

//coordinate plane
function drawGrid(size) {
  for (var i = 0; i < 501; i += 25 / 2) {
    line(0, i, 500, i);
    line(i, 0, i, 500);
  }

  for (var x = 0; x < 500; x += 125 / 2) {
    strokeWeight(3);
    line(x, 240, x, 260);
    line(240, x, 260, x);
  }

  strokeWeight(3);
  line(250, 0, 250, 500);
  line(0, 250, 500, 250);
  strokeWeight(1);

}

//draw polygon and triangles
function drawRight() {

  translate(mx, my);
  x1 = triangleMove * (r * cos(a1) / 2);
  y1 = triangleMove * (r * sin(a1) / 2);

  fill(255, 0, 0);
  polygon(0, 0, r / 2, numSides);
  fill(0, 0, 255);
  triangle(0, 0, x1, y1, x1, 0);
  triangle(0, 0, -x1, -y1, -x1, 0);
  stroke(255, 255, 0);
  //creates tangent line
  rotate(ta);
  line(-r / 2, -r / 2, -r / 2, r / 2);
  rotate(-ta);
  ta = ta + movement;
  stroke(0, 0, 0);
  translate(-mx, -my);
}

//draws circle on mouse coordinates
function mouseReleased() {
  if (mouseReleased) {
    if (mouseX < 500 - r / 2 & mouseY < 500 - r / 2 & mouseX > r / 2 & mouseY > r / 2) {
      mx = mouseX;
      my = mouseY;
      if (snap == true) {
        mx -= mx % (500 / 40);
        my -= my % (500 / 40);
      }
    }

  }
}

//draws polygon
function polygon(x, y, radius, npoints, spin) {
  var angle = TWO_PI / npoints;
  beginShape();
  for (var a = 0 + spin; a < TWO_PI + spin; a += angle) {
    var sx = x + cos(a) * radius;
    var sy = y + sin(a) * radius;
    vertex(sx, sy);
  }
  endShape(CLOSE);
}

//pauses animation
function keyTyped() {
  if (key === 's')
    snap = !snap;
  if (key === ' ')
    pause = !pause;
  if (pause == true) {
    movement = 0;
    x1 = 0;
    y1 = 0;
    triangleMove = 0;
    a1 += 0;
  } else {
    movement = 0.01;
    triangleMove = 1;
    x1 = triangleMove * (r * cos(a1) / 2);
    y1 = triangleMove * (r * sin(a1) / 2);
    a1 = 0.01;
  }
  //allows # of sides to change with # keys 2-9 if (key === '2')
  if (key === '2')
    numSides = 2;
  else if (key === '3')
    numSides = 3;
  else if (key === '4')
    numSides = 4;
  else if (key === '5')
    numSides = 5;
  else if (key === '6')
    numSides = 6;
  else if (key === '7')
    numSides = 7;
  else if (key === '8')
    numSides = 8;
  else if (key === '9')
    numSides = 9;
}
