import java.awt.*;//needed for Color

class ToroidalFin extends Fish {

  ToroidalFin(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = Math.random()*.05;
    this.yVelocity = Math.random()*.05;
  }



  protected void show(){
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.circle(this.xPos, this.yPos, this.size);
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
