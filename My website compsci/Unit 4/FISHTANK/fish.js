//A Fish constructor function

//createFish: returns an object literal
//"this" refers back to the object itself, ie "fish"
//replace "this" with "fish" to verify

function createFish(mx,my,t,a,ma,w,mxw,mw,r,g,b,spaz) {
  var fish = {
    //properties
    loc: createVector(random(590), random(590)),
    vel: createVector(random(5),random(5)),
    name: t,
    //velocity --> vector
    //loc --> vector
    skin: color(r, g, b),
    age:a,
    weight:w,
    tail:1,
    tail1:1,
    deltatail:.5,
    maxage:ma,
    maxweight:mxw,
    minweight:mw,
    tdi:1,
    spez:spaz,

    //methods (properties that happen to be functions)
    show: function() {
      if (this.tail > 0 & this.tail < this.weight) {
        this.tail = this.tail+this.deltatail;
        this.tail1 = this.tail*this.tdi;
      }
      else{
        this.deltatail = -this.deltatail
        this.tail = this.tail+this.deltatail;
        this.tail1 = this.tail*this.tdi;
      }
      fill(this.skin);
      stroke(this.spez,0,0);
      triangle((this.loc.x-(this.weight/2)*this.tdi-this.tail1), (this.loc.y+this.weight/2), (this.loc.x-(this.weight/2)*this.tdi-this.tail1), (this.loc.y-this.weight/2),this.loc.x,this.loc.y);
      ellipse(this.loc.x, this.loc.y, this.weight, this.weight);
      fill(255);
      text(this.name, this.loc.x - 5, this.loc.y - 5);
    },
    move: function() {
      //movement code goes here
    if (this.age <= this.maxage & this.weight <= this.maxweight & this.weight >= this.minweight) {
      if (this.spez==255) {
        if(this.loc.y >= height || this.loc.y <=0){
          this.loc.y = this.weight/2;
        }
        if(this.loc.x >= width-5 || this.loc.x <=5){
          this.loc.x = this.weight/2;
          }
      }
      else {
        if(this.loc.y >= height || this.loc.y <=0){
          this.vel.y *= -1;
        }
        if(this.loc.x >= width-5 || this.loc.x <=5){
          this.vel.x *= -1;
          this.tdi *= -1;
          }
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
Collisionfish: function(other) {
      return ((other.weight / 2 + this.weight /2 )> this.loc.dist(other.loc));
},
    update: function() {
      this.show();
      this.age++;
      this.move();
    }
  }
  return fish;
}
