//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify
function createPeletc(rectx) {
  var fish = {
    //properties
    loc: createVector(rectx, height-100),
    //velocity --> vector
    //loc --> vector
    skin: color(0, 255, 0),
    outline: color(0),
    weight: 100,

    //methods (properties that happen to be functions)
    show: function() {
      this.loc.x=rectx-50;
      fill(this.skin);
      stroke(this.outline);
      rect(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
    },
    update: function(rectx) {
      this.show();
    }
  }
  return fish;
}
