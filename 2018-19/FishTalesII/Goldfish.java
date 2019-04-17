import java.awt.*;//needed for Color

class Goldfish extends Fish {

  Goldfish(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = -0.023;
    this.yVelocity = 0.015;
  }


<<<<<<< HEAD
    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size) this.yVelocity = -this.yVelocity;

    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;


  }//move
=======
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7

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
