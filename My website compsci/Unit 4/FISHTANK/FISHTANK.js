var rectObjs = []; // start with empty list
var v = 1;
function setup() {
  createCanvas(400, 400);

}
function draw() {
  background(50);
  noStroke();
  rectMode(CENTER);
  fill(255);
  for (var i = 0; i < rectObjs.length; i++) {
    fill(rectObjs[i].fillColor);
    rect(rectObjs[i].xpos,
        rectObjs[i].ypos, 50, 25);
if (rectObjs[i].ypos < 400){
v = v;
}
if(rectObjs[i].ypos > 0){
v = -v
}
    rectObjs[i].ypos += v;
  }
}
function mousePressed() {
  rectObjs.push({xpos: mouseX, ypos: mouseY,
    fillColor: random(255)});
}
