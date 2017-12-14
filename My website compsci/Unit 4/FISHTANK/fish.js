//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createFish() {
  var fish = {
    //properties
    loc: createVector(width/2, height/2),
    vel: createVector(2,3),
    name: "nemo_" + round(random(100)),
    //velocity --> vector
    //loc --> vector
    skin: color(random(255), 0, 0),
    outline: color(0),
    age:0,

    //methods (properties that happen to be functions)
    show: function() {
      fill(this.skin);
      stroke(this.outline);
      ellipse(this.loc.x, this.loc.y, 10, 10);
      fill(255);
      text(this.name, this.loc.x - 5, this.loc.y - 5);
    },
    move: function() {
      //movement code goes here
      if(this.loc.x >= width || this.loc.x <=0){
this.vel.x *= -1;
}
if(this.loc.y >= height || this.loc.y <=0){
this.vel.y *= -1;
}
      this.loc.add(this.vel)
    },
    update: function() {
      this.move();
      this.show();
      this.age++;
    }
  }
  return fish;
}
