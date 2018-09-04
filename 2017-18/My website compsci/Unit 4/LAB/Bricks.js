//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createPelet(x,y) {
  var fish = {
    //properties
    loc: createVector(x, y),
    //velocity --> vector
    //loc --> vector
    skin: color(0, 255, 0),
    outline: color(0),
    age: 0,
    weight: 100,

    //methods (properties that happen to be functions)
    show: function() {
      fill(this.skin);
      stroke(this.outline);
      rect(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
    },

    update: function() {
      this.show();
      this.age++;
    }
  }
  return fish;
}
