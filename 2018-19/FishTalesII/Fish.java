import java.awt.*;//needed for Color

abstract class Fish extends LivingObject{
  private String name;
  private boolean vitalSigns=false;


  Fish() {
    super();
  }//default fish constructor
  protected void move() {

    if(this.vitalSigns == true){
      this.xVelocity = 0;
      this.yVelocity = 0.01;
      if(this.yPos > 0.9){
        this.yVelocity = 0;
      }

    }

    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size) this.yVelocity = -this.yVelocity;

    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;
  }//move

  public boolean hasCollision(Tankable t){
    if(this instanceof Goldfish && t instanceof Piranha){
      this.vitalSigns = true;
    }

    if(this instanceof Goldfish && t instanceof Goldfish){

    }
    else if(this instanceof Fish && t instanceof Food){

    }
    else if(this instanceof Fish && t instanceof Poison){

    }
    if(this instanceof Piranha && t instanceof Piranha){

    }
    if(this instanceof Piranha && t instanceof Goldfish){

    }
    else{
      this.xVelocity=-this.xVelocity;
      this.yVelocity=-this.yVelocity;
      t.bounce();
    }
    boolean collision=false;
    return collision;
  }//hasCollision

  public int compareTo(Fish f){
    return 0;
  }//compareTo

  public boolean isDead(){
    //boolean vitalSigns=false;

    return vitalSigns;
  }//isDead

  abstract public boolean tryToEat(Tankable t);

}
