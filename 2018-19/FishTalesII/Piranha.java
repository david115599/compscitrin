import java.awt.*;//needed for Color

class Piranha extends Fish {

  Piranha(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = Math.random()*.05;
    this.yVelocity = Math.random()*.05;

    this.maxSpeed = Math.sqrt(this.xVelocity*this.xVelocity+this.yVelocity*this.yVelocity);

  }




  protected void show(){
    StdDraw.setPenColor(StdDraw.MAGENTA);
    StdDraw.filledCircle(this.xPos, this.yPos, this.size);
  }//show
  public  boolean tryToEat(Tankable t){
    boolean hasEaten = false;

    return hasEaten;
  }//tryToEat
  public  boolean tryToBreed(Tankable t){
    boolean hasBred = false;

    return hasBred;
  }//tryToBreed

}//Piranha
