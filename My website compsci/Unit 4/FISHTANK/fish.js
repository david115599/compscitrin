//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify

function createFish(mx,my,p1) {
  var fish = {
    //properties
    loc: createVector(mx, my),
    vel: createVector(random(5),random(5)),
    name: "nemo_" + round(random(1000)),
    //velocity --> vector
    //loc --> vector
    skin: color(random(255), 0, 0),
    outline: color(0),
    age:0,
    weight:40,
    tail:1,
    deltatail:.5,

    //methods (properties that happen to be functions)
    show: function() {
      if (this.tail > 0 & this.tail < 25) {
        this.tail = this.tail+this.deltatail;
      }
      else{
        this.deltatail = -this.deltatail
        this.tail = this.tail+this.deltatail;
      }
      fill(this.skin);
      stroke(this.outline);
      ellipse(this.loc.x, this.loc.y, this.weight, this.weight);
      triangle(this.loc.x-20-this.tail, this.loc.y+20, this.loc.x-20-this.tail, this.loc.y-20,this.loc.x,this.loc.y);
      fill(255);
      text(this.name, this.loc.x - 5, this.loc.y - 5);
    },
    move: function() {
      //movement code goes here
    if (this.age <= 1000 & this.weight <= 80) {
      if(this.loc.y >= height || this.loc.y <=0){
          this.vel.y *= -1;
      }
      if(this.loc.x >= width-5 || this.loc.x <=5){
          this.vel.x *= -1;
    }
  } // alive
    else if (this.loc.y <= 40) {
      this.vel.y = 0;
      this.vel.x = 0;
      this.deltatail=0;
    }
    else {
      this.vel.y = -1;
      this.vel.x = 0;
      this.deltatail=0;
    }

    this.loc.add(this.vel)
  },
  Collision: function(other) {
        return (other.weight / 2 + this.weight /2 >= this.loc.dist(other.loc));
},
    update: function() {
      this.show();
      this.age++;
      this.move();
    }
  }
  return fish;
}
