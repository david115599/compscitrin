import java.awt.*;//needed for Color

class Goldfish extends Fish {

  Goldfish(String name, float x,float y, float h, float w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = -0.023;
    this.yVelocity = 0.015;
  }

  protected void move() {

    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size) this.yVelocity = -this.yVelocity;

    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;


  }//move

  protected void show(){
    StdDraw.setPenColor(StdDraw.ORANGE);
    StdDraw.filledCircle(this.xPos, this.yPos, this.size);
  }//show

  public boolean tryToEat(Tankable t){
    boolean hasEaten = false;

    return hasEaten;
  }//tryToEat
  public  boolean tryToBreed(Tankable t){
    boolean hasBred = false;

    return hasBred;
  }//tryToBreed

}//Goldfish
