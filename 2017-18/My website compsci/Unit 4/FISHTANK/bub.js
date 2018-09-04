//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createPeletc() {
  var fish = {
    //properties
    loc: createVector(random(width), random(height)),
    vel: createVector(0, -1 * random(10)),
    //velocity --> vector
    //loc --> vector
    skin: color(0, 220, 220),
    outline: color(0),
    age: 0,
    weight: random(100),

    //methods (properties that happen to be functions)
    show: function() {
      fill(this.skin);
      stroke(this.outline);
      ellipse(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
    },
    move: function() {


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
