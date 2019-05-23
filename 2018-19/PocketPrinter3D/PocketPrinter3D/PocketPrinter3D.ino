#include <SPI.h>
#include <SD.h>
#include <Stepper.h>
#include <TFT.h>
Sd2Card card;
SdVolume volume;
SdFile root;
#define lcd_cs 10
#define dc     9
#define rst    8
const int chipSelect = 4;
const int stepsPerRevolution = 200;  // change this to fit the number of steps per revolution
Stepper myStepper(stepsPerRevolution, 2, 3, 5, 7);
TFT TFTscreen = TFT(lcd_cs, dc, rst);
PImage logo;
void setup() {
  Serial.begin(9600);
  myStepper.setSpeed(200);


  Serial.print("\nInitializing SD card...");

  // we'll use the initialization code from the utility libraries
  // since we're just testing if the card is working!
  if (!card.init(SPI_HALF_SPEED, chipSelect)) {
    Serial.println("initialization failed. Things to check:");
    Serial.println("* is a card inserted?");
    Serial.println("* is your wiring correct?");
    Serial.println("* did you change the chipSelect pin to match your shield or module?");
    while (1);
  } else {
    Serial.println("Wiring is correct and a card is present.");
  }


  File root1;
  root1 = SD.open("/");
  printDirectory(root1, 0);

  TFTscreen.begin();
  TFTscreen.background(255, 255, 255);

  logo = TFTscreen.loadImage("0.bmp");
}

void loop() {
  //myStepper.step(5/*#of steps*/);

  if (logo.isValid() == false) {
    return;
  }
  int x = (TFTscreen.width() / 2 - logo.width() / 2);
  int y = (TFTscreen.height() / 2 - logo.height() / 2);
  Serial.println(F("drawing image"));
  TFTscreen.image(logo, x, y);


  // get a random location where to draw the image.
  // To avoid the image to be draw outside the screen,
  // take into account the image size.

  for (int i = 0; i < 9; i++) {
    digitalWrite(13, LOW);
    String str = (String)i + ".bmp";
    int len = (int)str.length() + 1;
    char buf[len];
    str.toCharArray(buf, len );
    Serial.println((buf));
    logo = TFTscreen.loadImage(buf);
    int x = (TFTscreen.width() / 2 - logo.width() / 2);
    int y = (TFTscreen.height() / 2 - logo.height() / 2);

    // draw the image to the screen
    TFTscreen.background(0, 0, 0);
    delay(10);
    TFTscreen.background(255, 255, 255);
    Serial.println(F("drawing image"));
    TFTscreen.image(logo, x, y);
    digitalWrite(13, HIGH);
    delay(20000);
    digitalWrite(13, LOW);
  }

}




void printDirectory(File dir, int numTabs) {
  while (true) {

    File entry =  dir.openNextFile();
    if (! entry) {
      // no more files
      break;
    }
    for (uint8_t i = 0; i < numTabs; i++) {
      Serial.print('\t');
    }
    Serial.print(entry.name());
    if (entry.isDirectory()) {
      Serial.println("/");
      printDirectory(entry, numTabs + 1);
    } else {
      // files have sizes, directories do not
      Serial.print("\t\t");
      Serial.println(entry.size(), DEC);
    }
    entry.close();
  }
}
