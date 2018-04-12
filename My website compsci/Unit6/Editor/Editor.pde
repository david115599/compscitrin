
PGraphics draw;
float R1=0;
float G1=0;
float B1=0;
float A1=255;
float R2=0;
float G2=0;
float B2=0;
float A2=255;
int tool=1;
int thickness=1;
int mousexp=0;
int mouseyp=0;
int x;
int y;
HScrollbar hs1, hs2, hs3, hs4, hs5, hs6;
void setup() {
  size(900, 600);
  frameRate(200);

  draw = createGraphics(width, height);
  draw.beginDraw();
  draw.stroke(0);
  draw.noFill();
  draw.endDraw();

  background(200-50, 200-50, 200-50);
  fill(55, 55, 55);
  strokeWeight(2);
  stroke(0, 0, 0);
  rect(0, 0, 150, 200);
  rect(150, 0, 150, 200);
  hs1 = new HScrollbar(0, 40, 150, 16, 16);
  hs2 = new HScrollbar(0, 70, 150, 16, 16);
  hs3 = new HScrollbar(0, 100, 150, 16, 16);
  hs4 = new HScrollbar(150, 40, 150, 16, 16);
  hs5 = new HScrollbar(150, 70, 150, 16, 16);
  hs6 = new HScrollbar(150, 100, 150, 16, 16);
  hs1.newspos = 0;
  hs2.newspos = 0;
  hs3.newspos = 0;
  hs4.newspos = 105.88+150+30;
  hs5.newspos = 105.88+150+30;
  hs6.newspos = 105.88+150+30;
}

void draw() {
  background(200-50, 200-50, 200-50);
  stroke(R1,G1,B1,A1);
  image(draw, 0, 0);
  stroke(0);

  strokeWeight(1);
  stroke(55, 55, 55);
  fill(55, 55, 55);
  rect(0, 0, 300, 600);
  strokeWeight(2);
  stroke(0, 0, 0);
  rect(0, 0, 150, 200);
  rect(150, 0, 150, 200);
  noStroke();
  fill(255, 0, 0);
  rect(5, 120, 20, 20);
  fill(0, 255, 0);
  rect(25, 120, 20, 20);
  fill(0, 0, 255);
  rect(45, 120, 20, 20);
  fill(219, 65, 5);
  rect(65, 120, 20, 20);
  fill(65, 5, 219);
  rect(85, 120, 20, 20);
  fill(200, 200, 200);
  rect(105, 120, 20, 20);
  fill(0, 0, 0);
  rect(125, 120, 20, 20);

  fill(255, 0, 0);
  rect(155, 120, 20, 20);
  fill(0, 255, 0);
  rect(175, 120, 20, 20);
  fill(0, 0, 255);
  rect(195, 120, 20, 20);
  fill(219, 65, 5);
  rect(215, 120, 20, 20);
  fill(65, 5, 219);
  rect(235, 120, 20, 20);
  fill(200, 200, 200);
  rect(255, 120, 20, 20);
  fill(0, 0, 0);
  rect(275, 120, 20, 20);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >5 & mouseX <65 & mouseY >205 & mouseY <235) {
    tool =1;
    fill(255, 0, 0);
  }
  rect(5, 205, 60, 30);
  fill(150, 150, 150);
  text("PEN", 5, 230);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >70 & mouseX <130 & mouseY >205 & mouseY <235) {
    tool =2;
      fill(255, 0, 0);
  }
  rect(70, 205, 60, 30);
  fill(150, 150, 150);
  text("ERS", 70, 230);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >135 & mouseX <195 & mouseY >205 & mouseY <235) {
    thickness ++;
    fill(255, 0, 0);
  }
  rect(135, 205, 60, 30);
  fill(150, 150, 150);
  text("S+", 135, 230);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >200 & mouseX <260 & mouseY >205 & mouseY <235 & thickness>1) {
    thickness --;
    fill(255, 0, 0);
  }
  rect(200, 205, 60, 30);
  fill(150, 150, 150);
  text("S-", 200, 230);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >135 & mouseX <195 & mouseY >240 & mouseY <270) {
    A1 ++;
    fill(255, 0, 0);
  }
  rect(135, 240, 60, 30);
  fill(150, 150, 150);
  text("A1+", 135, 265);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >200 & mouseX <260 & mouseY >240 & mouseY <270 & thickness>1) {
    A1 --;
    fill(255, 0, 0);
  }
  rect(200, 240, 60, 30);
  fill(150, 150, 150);
  text("A1-", 200, 265);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >135 & mouseX <195 & mouseY >275 & mouseY <305) {
    A2 ++;
    fill(255, 0, 0);
  }
  rect(135, 275, 60, 30);
  fill(150, 150, 150);
  text("A2+", 135, 275+25);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >200 & mouseX <260 & mouseY >275 & mouseY <305 & thickness>1) {
    A2 --;
    fill(255, 0, 0);
  }
  rect(200, 275, 60, 30);
  fill(150, 150, 150);
  text("A2-", 200, 275+25);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >5 & mouseX <65 & mouseY >240 & mouseY <270) {
    tool =3;
    fill(255, 0, 0);
  }
  rect(5, 240, 60, 30);
  fill(150, 150, 150);
  text("LIN", 5, 265);

  fill(30, 30, 30);
  if (mousePressed == true & mouseX >5 & mouseX <75 & mouseY >275 & mouseY <305) {
      PImage partialSave = get(300,0,900,600);
      partialSave.save("Save.png");
      fill(255, 0, 0);
  }
  rect(5, 275, 70, 30);
  fill(150, 150, 150);
  text("Save", 5, 275+25);

    fill(30, 30, 30);
    text(mouseX-300, 5, 600);
    text(":", 80, 600);
    text(mouseY, 95, 600);

  strokeWeight(2);
  R1=(hs1.getPos())*(255/150);
  G1=(hs2.getPos())*(255/150);
  B1=(hs3.getPos())*(255/150);
  R2=(hs4.getPos()-170)*(255/150);
  G2=(hs5.getPos()-170)*(255/150);
  B2=(hs6.getPos()-170)*(255/150);
  textSize(30);
  fill(R1, G1, B1);

  text("Color1", 10, 30);
  fill(R2, G2, B2);
  text("Color2", 160, 30);
  hs1.update();
  hs1.display();
  hs2.update();
  hs2.display();
  hs3.update();
  hs3.display();
  hs4.update();
  hs4.display();
  hs5.update();
  hs5.display();
  hs6.update();
  hs6.display();
  stroke(0, 0, 0);
  fill(0, 0, 0);
  rect(150, 0, 1, 200);
//color 1
  if (mousePressed == true & mouseX >5 & mouseX <25 & mouseY >120 & mouseY <140) {
    hs1.newspos = 135;
    hs2.newspos = 0;
    hs3.newspos = 0;
  }
  if (mousePressed == true & mouseX >25 & mouseX <45 & mouseY >120 & mouseY <140) {
    hs1.newspos = 0;
    hs2.newspos = 135;
    hs3.newspos = 0;
  }
  if (mousePressed == true & mouseX >45 & mouseX <65 & mouseY >120 & mouseY <140) {
    hs1.newspos = 0;
    hs2.newspos = 0;
    hs3.newspos = 135;
  }
  //take the rgb value and divide it by 1.888888888888888888... to get the propper slider location
  if (mousePressed == true & mouseX >65 & mouseX <85 & mouseY >120 & mouseY <140) {
    hs1.newspos = 116;
    hs2.newspos = 34.5;
    hs3.newspos = 2.6;
  }
  if (mousePressed == true & mouseX >85 & mouseX <105 & mouseY >120 & mouseY <140) {
    hs1.newspos = 34.5;
    hs2.newspos = 2.6;
    hs3.newspos = 116;
  }
  if (mousePressed == true & mouseX >105 & mouseX <125 & mouseY >120 & mouseY <140) {
    hs1.newspos = 105.88+30;
    hs2.newspos = 105.88+30;
    hs3.newspos = 105.88+30;
  }
  if (mousePressed == true & mouseX >125 & mouseX <145 & mouseY >120 & mouseY <140) {
    hs1.newspos = 0;
    hs2.newspos = 0;
    hs3.newspos = 0;
  }
//color 2
  if (mousePressed == true & mouseX >5+150 & mouseX <25+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 135+150;
    hs5.newspos = 0+150;
    hs6.newspos = 0+150;
  }
  if (mousePressed == true & mouseX >25+150 & mouseX <45+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 0+150;
    hs5.newspos = 135+150;
    hs6.newspos = 0+150;
  }
  if (mousePressed == true & mouseX >45+150 & mouseX <65+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 0+150;
    hs5.newspos = 0+150;
    hs6.newspos = 135+150;
  }
  //take the rgb value and divide it by 1.888888888888888888... to get the propper slider location
  if (mousePressed == true & mouseX >65+150 & mouseX <85+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 116+150;
    hs5.newspos = 34.5+150;
    hs6.newspos = 2.6+150;
  }
  if (mousePressed == true & mouseX >85+150 & mouseX <105+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 34.5+150;
    hs5.newspos = 2.6+150;
    hs6.newspos = 116+150;
  }
  if (mousePressed == true & mouseX >105+150 & mouseX <125+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 105.88+150+30;
    hs5.newspos = 105.88+150+30;
    hs6.newspos = 105.88+150+30;
  }
  if (mousePressed == true & mouseX >125+150 & mouseX <145+150 & mouseY >120 & mouseY <140) {
    hs4.newspos = 0+150;
    hs5.newspos = 0+150;
    hs6.newspos = 0+150;
  }
}

class HScrollbar {
  int swidth, sheight;    // width and height of bar
  float xpos, ypos;       // x and y position of bar
  float spos, newspos;    // x position of slider
  float sposMin, sposMax; // max and min values of slider
  int loose;              // how loose/heavy
  boolean over;           // is the mouse over the slider?
  boolean locked;
  float ratio;

  HScrollbar (float xp, float yp, int sw, int sh, int l) {
    swidth = sw;
    sheight = sh;
    int widthtoheight = sw - sh;
    ratio = (float)sw / (float)widthtoheight;
    xpos = xp;
    ypos = yp-sheight/2;
    spos = xpos + swidth/2 - sheight/2;
    newspos = spos;
    sposMin = xpos;
    sposMax = xpos + swidth - sheight;
    loose = l;
  }

  void update() {
    if (overEvent()) {
      over = true;
    } else {
      over = false;
    }
    if (mousePressed && over) {
      locked = true;
    }
    if (!mousePressed) {
      locked = false;
    }
    if (locked) {
      newspos = constrain(mouseX-sheight/2, sposMin, sposMax);
    }
    if (abs(newspos - spos) > 1) {
      spos = spos + (newspos-spos)/loose;
    }
  }

  float constrain(float val, float minv, float maxv) {
    return min(max(val, minv), maxv);
  }

  boolean overEvent() {
    if (mouseX > xpos && mouseX < xpos+swidth &&
      mouseY > ypos && mouseY < ypos+sheight) {
      return true;
    } else {
      return false;
    }
  }

  void display() {
    noStroke();
    fill(204);
    rect(xpos, ypos, swidth, sheight);
    if (over || locked) {
      fill(0, 0, 0);
    } else {
      fill(102, 102, 102);
    }
    rect(spos, ypos, sheight, sheight);
  }

  float getPos() {
    // Convert spos to be values between
    // 0 and the total width of the scrollbar
    return spos * ratio;
  }
}
void mousePressed(){
  if (tool == 3) {
    draw.beginDraw();
    draw.stroke(R1,G1,B1,A1);
    draw.endDraw();
    x = mouseX;
    y = mouseY;
  }
}
void mouseReleased() {
    if (tool == 3) {
  draw.beginDraw();
  draw.stroke(R1,G1,B1,A1);
  draw.strokeWeight(thickness);
  draw.stroke(R1,G1,B1,A1);
  draw.line(x, y, mouseX, mouseY);
  draw.endDraw();
    }
  }
void mouseDragged() {
    draw.beginDraw();
    if (tool == 1) {
       if (mouseButton == LEFT) {
           draw.stroke(R1,G1,B1,A1);
       }
       else if (mouseButton == RIGHT) {
   draw.stroke(R2,G2,B2,A2);
  }
  draw.strokeWeight(thickness);
  draw.line(pmouseX,pmouseY,mouseX,mouseY);
    }
    if (tool == 2) {
  draw.strokeWeight(thickness);
  draw.stroke(R2,G2,B2,A2);
  draw.line(pmouseX,pmouseY,mouseX,mouseY);
    }
    draw.endDraw();
    if (tool == 3) {
    stroke(R1,G1,B1,A1);
      line(x, y, mouseX, mouseY);
    }
}
