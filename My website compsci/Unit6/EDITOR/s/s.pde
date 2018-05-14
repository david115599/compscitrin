
import controlP5.*;

ControlP5 cp5;

PImage tiger;
boolean bw;
boolean bw2;

void setup() {
  size(757,568);
  tiger = loadImage("Tiger.jpeg");
  image(tiger,0,0);
  cp5 = new ControlP5(this);

  cp5.addSlider("threshold")
     .setPosition(100, 20)
     .setSize(200, 19)
     .setRange(0,255)
     .setValue(255/2)
     ;

   cp5.addSlider("posterize")
     .setPosition(100, 40)
     .setSize(200, 19)
     .setRange(0,255)
     .setValue(255/2)
     ;

}

void draw() {

}


void threshold(int val) {
  println("a slider event from threshold: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);

    if (keyCode == UP) {
      bw = true;
    }

    if (keyCode == DOWN) {
      bw = false;
    }

 if (bw == true) {
   if ((r+g+b)/3 < val) {
     r = 0;
     g = 0;
     b = 0;
   }

   else {
     r = 255;
     g = 255;
     b = 255;
   }
  }

else {
   if (r > val) {
     g = 0;
     b = 0;
   }

  else if (g > val) {
     r = 0;
     b = 0;
   }

   else {
     r = 0;
     g = 0;
   }
}
    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}

void posterize(int val) {

  println("a slider event from posterize: "+val);
  loadPixels();
  tiger.loadPixels();
  for (int i = 0; i < tiger.pixels.length; i++) {
    float r = red   (tiger.pixels[i]);
    float g = green (tiger.pixels[i]);
    float b = blue  (tiger.pixels[i]);

   if (keyCode == LEFT) {
      bw = true;
    }

    if (keyCode == RIGHT) {
      bw = false;
    }

  if (bw == false) {
    r = int(red(tiger.pixels[i])/val) * val;
    g = int(green(tiger.pixels[i])/val) * val;
    b = int(blue(tiger.pixels[i])/val) * val;
  }

  else {
    r = int(brightness(tiger.pixels[i])/val) * val;
    g = int(brightness(tiger.pixels[i])/val) * val;
    b = int(brightness(tiger.pixels[i])/val) * val;
  }

    color c = color(r,g,b);
    pixels[i] = c;
  }
  updatePixels();
}
