var block = [];
var ball = [];
var bar = [];
var x = 0;
var y = 0;
var rectx = 300;
var song;
//music only works in firefox
function preload() {
  song = loadSound('Brick Breaker Revolution  1 Java Mobile Intro.mp3');
}



function setup() {
  song.loop();
  createCanvas(600, 600);
  fishButton = createButton('Start');
  fishButton.position(252, 19);
  fishButton.mousePressed(addblock);
}

function draw() {

  bar.push(createPeletc(rectx));
  background(0, 180, 0);
  for (var i = 0; i < ball.length; i++) {
    for (var m = 0; m < block.length; m++) {
      if (ball[i].Collisionfish(block[m])) {
        ball[i].vel.y *= -1;
        ball[i].vel.x *= -1;
        block.splice(m--, 1);
      }
    }
  }
  for (var i = 0; i < block.length; i++) {
    block[i].update();
  }
  for (var i = 0; i < ball.length; i++) {
    ball[i].update();
  }
  for (var i = 0; i < ball.length; i++) {
    for (var m = 0; m < bar.length; m++) {
      if (ball[i].Collision(bar[m])) {
        ball[i].vel.y *= -1;
      }
    }
  }
  document.onkeydown = checkKey;
    for (var i = 0; i < bar.length; i++) {
    for (var z = 0; z < bar.length; z++) {
      bar[i].update();
      bar.splice(z--, 1);
  }
  }
}

function keyTyped() {
  if (key === ' ') {
    addball();
    x = 0;
  }
}

function checkKey(e) {
  e = e || window.event;
  if (e.keyCode == '37' & rectx >= 60) {
    rectx -= 10;
  }
  if (e.keyCode == '39' & rectx <= width - 60) {
    rectx -= -10;
  }
}

function addblock() {
  for (var i = 0; i < (width) / 100; i++) {
    block.push(createPelet(x, y));
    x += 100;
  }
}


function addball() {
  ball.push(createPeletD(rectx));
}
