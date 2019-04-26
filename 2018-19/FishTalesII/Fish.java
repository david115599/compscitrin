import java.awt.*;//needed for Color

abstract class Fish extends LivingObject{
  private String name;
  private boolean vitalSigns=false;
  private boolean beeneaten=false;
boolean collision;

  Fish() {
    super();
    this.closestG = null;
  }//default fish constructor


  public boolean hasCollision(Tankable t){
    if(this instanceof Goldfish && t instanceof Piranha){
      //this.vitalSigns = true;
      this.beeneaten = true;
    }

    else if(this instanceof Goldfish && t instanceof Goldfish){
      this.xVelocity=-this.xVelocity;
      this.yVelocity=-this.yVelocity;
      //t.bounce();
    }
    if(this instanceof Fish && t instanceof Food){
        this.size +=.01;
    }
    if(this instanceof Fish && t instanceof Poison){
        this.size -=.01;
    }
    if(this instanceof Piranha && t instanceof Piranha){
      this.xVelocity=-this.xVelocity;
      this.yVelocity=-this.yVelocity;
      //t.bounce();
    }
    if(this instanceof Piranha && t instanceof Goldfish){
      this.size +=.01;
      this.xVelocity = this.xVelocity-.01;
      this.yVelocity = this.yVelocity-.01;
    }
   collision=false;
    return collision;
  }//hasCollision

  protected void move() {

    if(this.vitalSigns == true){
      this.xVelocity = 0;
      this.yVelocity = 0.01;
      if(this.yPos > 0.9){
        this.yVelocity = 0;
      }

    }

    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size && !(this instanceof ToroidalFin)) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size && !(this instanceof ToroidalFin)) this.yVelocity = -this.yVelocity;
    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size && (this instanceof ToroidalFin)) this.xPos = -this.xPos;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size && (this instanceof ToroidalFin)) this.yPos = -this.yPos;

    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;

    if(this instanceof Piranha && collision != true){
      if(closestG != null){
      //  it currently ignores collisions and needs to maintain their old velocity
      this.xVelocity = (closestG.getX()-this.xPos)/this.d(closestG)*.01;
      this.yVelocity = (closestG.getY()-this.yPos)/this.d(closestG)*.01;
      }
    }
  }//move

  public int compareTo(Fish f){
    return 0;
  }//compareTo

  public boolean isDead(){
    //boolean vitalSigns=false;

    return vitalSigns;
  }//isDead

  public boolean isEaten(){
    //boolean beeneaten=false;

    return beeneaten;
  }//isEaten

  abstract public boolean tryToEat(Tankable t);

}
