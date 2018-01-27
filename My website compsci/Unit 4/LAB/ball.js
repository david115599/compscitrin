//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createPeletD(rectx) {
  var fish = {
    //properties

    loc: createVector(rectx, height-135),
    vel: createVector(-1*random(10), -1*random(10)),
    //velocity --> vector
    //loc --> vector
    skin: color(255, 0, 0),
    outline: color(0),
    age: 0,
    weight: 20,

    //methods (properties that happen to be functions)
    show: function() {
      fill(this.skin);
      stroke(this.outline);
      ellipse(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
    },
    move: function() {
      //movement code goes here
      if (this.loc.y <= 0) {
        this.vel.y *= -1;
      }
      if (this.loc.x >= width - 5 || this.loc.x <= 5) {
        this.vel.x *= -1;
      }

      this.loc.add(this.vel)
    },
    Collisionfish: function(other) {
      return ((other.weight + this.weight) > this.loc.dist(other.loc));
    },
    Collision: function(other) {
      return ((other.weight + this.weight)-80 > this.loc.dist(other.loc));
    },
    update: function() {
      this.move();
      this.show();
      this.age++;
    }
  }
  return fish;
}
