import java.awt.*;//needed for Color

class Whale extends Fish {

  Whale(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = Math.random()*.03;
    this.yVelocity = Math.random()*.03;
  }



  protected void show(){
    StdDraw.setPenColor(StdDraw.CYAN);
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
