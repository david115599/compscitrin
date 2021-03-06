W=(1422.5)
H=(800)
var deltax=3;
var starx = [];
var stary = [];
var starw = [];
var pct = 0.45;   // Percentage traveled (0.0 to 2.0)
var pct2 = 0.45;   // Percentage traveled (0.0 to 2.0)
var beginX = W;// Initial x-coordinate
var beginY = H;// Initial y-coordinate
var endX = 0;   // Final x-coordinate
var endY = 0;   // Final y-coordinate
var distX;          // X-axis distance to move
var distY;          // Y-axis distance to move
var exponent = 2;   // Determines the curve
var xs = 0.0;        // Current x-coordinate
var ys = 0.0;        // Current y-coordinate
var step = 0.005;    // Size of each step along the path
var cr = 255;
var cg = 255;
var cb = 0;
var bgc=1;
var y = 0;
var x = 0;
var v = 4;
var q = 6;
var img;
var skc = 0;
var bx;
var by;
var boxSize = 75;
var overBox = false;
var locked = false;
var xOffset = 0.0;
var yOffset = 0.0;
var quantity = 300;
var xPosition = [];
var yPosition = [];
var flakeSize = [];
var direction = [];
var minFlakeSize = 1;
var maxFlakeSize = 5;
var c = 1;

function setup() {

  createCanvas(W,H);
  frameRate(160);
  img = loadImage("House.png");
  for(var i = 0; i<50; i++){
    starx.push(random(0,width));
    stary.push(random(0,height));
    starw.push(random(1,6));
  }
  distX = endX - beginX;
  distY = endY - beginY;
  for(var  g= 0; g < quantity; g++) {
    flakeSize[g] = round(random(minFlakeSize, maxFlakeSize));
    xPosition[g] = random(0, width);
    yPosition[g] = random(0, height);
    direction[g] = round(random(0, 1));
  }
}

function draw() {

if (cb==0) {
  background(0,0,200-pct*150+100);
} else {
  background(0,0,0+pct*150-40);
}
//sun/moon
fill(0, 2);
rect(0, 0, width, height);
pct += step;
pct2 += step;

if (pct <= 1.55) {
xs = (beginX + (pct * distX));
  ys = beginY*pow(xs, exponent)/pow(beginX, exponent);
  fill(cr,cg,cb);
  ellipse(xs+width/2, ys+45, 80, 80);
}

else{
console.log("go back");
console.log(pct);
console.log(cb);
console.log(bgc);
pct = .45;
if (cb==0) {
  cb=255;
  bgc=255;
  c++
}
else if (cb==255) {
  cb=0;
  bgc=0;
}
}
//The All Mighty T
 fill(255,0,0);
rect(x+100, y+100, 80, 20);
rect(x+130, y+100, 20, 80);


if (y > -100 & y < height-800) {
  y = y+v;
}
else{
  v = -v
  y = y+v;
}
if (x > -100 & x < width-200) {
  x = x+q;
}
else{
  q = -q
  x = x+q;
}
//The All Mighty L
 fill(255,0,0);
rect(x+130, 480, 80, 20);
rect(x+130, 400, 20, 80);

//stars
for(var i = 0; i<50; i++){
  fill(255,0,0);
  ellipse(starx[i],stary[i],starw[i],starw[i]);
}
//smoke
fill(150,100,100);
ellipse(-(x/100)+800,-(x/200)+200,40,80)
ellipse((x/100)+800,-(x/500)+150,60,100)
ellipse(-(x/100)+800,-(x/200)+100,80,120)
ellipse((x/100)+800,-(x/500)+50,100,140)
//House
fill(100,255,120);
rect(0,500, W, H/2);
fill(0,0,180);
rect(W/2-W/4/2,H/2.5,W/4,H/2);
triangle(W/3,H/2.5,W/2, H/5, W/1.5, H/2.5);
fill(150,100,100);
rect(1422.5/2-75/2,570,75,150);
//picture
image(img, 690-15,400,50,50);

//Path
fill(255,0,0)
rect(690-15,720,10,10);
rect(690-15,740,10,10);
rect(690-15,760,10,10);
rect(690-15,780,10,10);
rect(700-15,730,10,10);
rect(700-15,750,10,10);
rect(700-15,770,10,10);
rect(700-15,790,10,10);
rect(710-15,720,10,10);
rect(710-15,740,10,10);
rect(710-15,760,10,10);
rect(710-15,780,10,10);
rect(720-15,730,10,10);
rect(720-15,750,10,10);
rect(720-15,770,10,10);
rect(720-15,790,10,10);
rect(730-15,720,10,10);
rect(730-15,740,10,10);
rect(730-15,760,10,10);
rect(730-15,780,10,10);
rect(740-15,730,10,10);
rect(740-15,750,10,10);
rect(740-15,770,10,10);
rect(740-15,790,10,10);
rect(750-15,720,10,10);
rect(750-15,740,10,10);
rect(750-15,760,10,10);
rect(750-15,780,10,10);
fill(255,150,0)
rect(690-15,730,10,10);
rect(690-15,750,10,10);
rect(690-15,770,10,10);
rect(690-15,790,10,10);
rect(700-15,720,10,10);
rect(700-15,740,10,10);
rect(700-15,760,10,10);
rect(700-15,780,10,10);
rect(710-15,730,10,10);
rect(710-15,750,10,10);
rect(710-15,770,10,10);
rect(710-15,790,10,10);
rect(720-15,720,10,10);
rect(720-15,740,10,10);
rect(720-15,760,10,10);
rect(720-15,780,10,10);
rect(730-15,730,10,10);
rect(730-15,750,10,10);
rect(730-15,770,10,10);
rect(730-15,790,10,10);
rect(740-15,720,10,10);
rect(740-15,740,10,10);
rect(740-15,760,10,10);
rect(740-15,780,10,10);
rect(750-15,730,10,10);
rect(750-15,750,10,10);
rect(750-15,770,10,10);
rect(750-15,790,10,10);
//Tree
fill(139,69,19);
rect(200,450,50,200);
fill(0,255,0);
ellipse(228,390,200,200);
//fence
for (var f = 0; f <= 670; f += 20) {
  fill(0,255,255);
  rect(f, H-100,10,60);
  rect(f,H-100,20,10)
}
for (var f2 = 750; f2 <= W; f2 += 20) {
  fill(0,255,255);
  rect(f2, H-100,10,60);
  rect(f2-10,H-100,20,10)
 }


 // Lights
 // Test if the cursor is over the box
   if (mouseX > 400 && mouseX < 1000 &&
       mouseY > 120 && mouseY < 700) {
     overBox = true;
     if(!locked) {
       stroke(255);
       fill(153);
     }
   } else {
     stroke(0);
     fill(153);
     overBox = false;
   }

   // Draw the box
   rect(580-15,500,75,75);
   rect(800-15,500,75,75);
   rect(580-15,400,75,75);
   rect(800-15,400,75,75);
   rect(580-15,600,75,75);
   rect(800-15,600,75,75);
   rect(690-15,400,75,75);

//snow
   console.log(c);
   if(c%3==0){
    drawSnow();
   }
}
 function mousePressed() {
   if(overBox) {
     locked = true;
     fill(255, 255, 255);
   } else {
     locked = false;
   }
   xOffset = mouseX-bx;
   yOffset = mouseY-by;

 }

 function mouseDragged() {
   if(locked) {
     bx = mouseX-xOffset;
     by = mouseY-yOffset;
   }
 }

 function mouseReleased() {
   locked = false;
 }
 function drawSnow() {
 	for(var  g= 0; g < xPosition.length; g++) {
fill(255)
     ellipse(xPosition[g], yPosition[g], flakeSize[g], flakeSize[g]);

     if(direction[g] == 0) {
       xPosition[g] += map(flakeSize[g], minFlakeSize, maxFlakeSize, .1, .5);
     } else {
       xPosition[g] -= map(flakeSize[g], minFlakeSize, maxFlakeSize, .1, .5);
     }

     yPosition[g] += flakeSize[g] + direction[g];

     if(xPosition[g] > width + flakeSize[g] || xPosition[g] < -flakeSize[g] || yPosition[g] > height + flakeSize[g]) {
       xPosition[g] = random(0, width);
       yPosition[g] = -flakeSize[g];
     }
   }
 }
