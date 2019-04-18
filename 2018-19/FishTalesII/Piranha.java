import java.awt.*;//needed for Color

class Piranha extends Fish {

  Piranha(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = Math.random()*.01;
    this.yVelocity = Math.random()*.01;
  }


  protected void show(){
    StdDraw.setPenColor(StdDraw.BLUE);
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
