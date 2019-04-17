import java.awt.*;//needed for Color

abstract class Fish extends LivingObject{
  private String name;

  Fish() {
    super();
  }//default fish constructor
  protected void move() {

    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size) this.yVelocity = -this.yVelocity;

    this.xPos = this.xPos + this.xVelocity;
    System.out.println("this.xPos : " + this.xPos + " + this.xVelocity " + this.xVelocity + " " + (this.xPos+this.xVelocity));
    this.yPos = this.yPos + this.yVelocity;
  }//move

  public boolean hasCollision(Tankable t){
    boolean collision=false;
    return collision;
  }//hasCollision

  public int compareTo(Fish f){
    return 0;
  }//compareTo

  public boolean isDead(){
    boolean vitalSigns=false;

    return vitalSigns;
  }//isDead

  abstract public boolean tryToEat(Tankable t);

}
