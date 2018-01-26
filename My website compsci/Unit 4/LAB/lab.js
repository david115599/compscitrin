var block =[];
var x = 0;
var y = 0;
function setup() {
  //song.loop();
  createCanvas(600, 600);
  fishButton = createButton('Start');
  fishButton.position(252, 19);
  fishButton.mousePressed(addblock);
}
function draw() {
  background(0, 180, 0);
for (var i = 0; i < block.length; i++){
    block[i].update();}
fill(200,0,0);
rect((width/2)-100,height-20,200,20);
}

function addblock() {
  for (var i = 0; i < (width)/100; i++){
    block.push(createPelet(x,y));
    x+=100;
}
}
