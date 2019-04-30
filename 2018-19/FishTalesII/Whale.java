import java.awt.*;//needed for Color

class Whale extends Fish {

  Whale(String name, double x,double y) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.maxSize = StdRandom.gaussian(1000,1000/5)/20;
    this.size = 0.1f;

    this.maxSpeed = StdRandom.gaussian(10,10/5)/20;
    this.xVelocity = Math.random()*.01;
    this.yVelocity = 0;
    this.maxAge = (int)StdRandom.gaussian(10000,10000/5);
    this.age = 0;
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
