//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createPeletD() {
  var fish = {
    //properties
    loc: createVector(random(width), 0),
    vel: createVector(0, random(10)),
    //velocity --> vector
    //loc --> vector
    skin: color(255, 0, 0),
    outline: color(0),
    age: 0,
    weight: 10,

    //methods (properties that happen to be functions)
    show: function() {
      fill(this.skin);
      stroke(this.outline);
      ellipse(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
    },
    move: function() {
      //movement code goes here
      if (this.loc.y >= height - 8 || this.loc.y <= -10) {
        this.vel.y = 0;
      }
      if (this.loc.x >= width - 5 || this.loc.x <= 0) {
        this.vel.x = 0;
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
