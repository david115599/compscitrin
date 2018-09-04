var W=(800);
var H=(500);


function setup() {

  createCanvas(W,H);
  background(100);
  background(0);
  for(var i = 80; i < W/2; i += 7) {
     stroke(0,255,0);
     line(W/5, i-50, 700,i-50 );
     stroke(255,0,0);
     line(70, i-49, 600,i-49 );
     stroke(0,0,255);
     line(115, i+46, 655,i+46 );
   }
}
