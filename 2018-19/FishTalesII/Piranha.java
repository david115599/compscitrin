import java.awt.*;//needed for Color

class Piranha extends Fish {

  Piranha(String name, double x,double y) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.maxSize = StdRandom.gaussian(20,20/5)/20;
    this.size = 0.05f;

    this.maxSpeed = StdRandom.gaussian(20,20/5)/1000;
    this.xVelocity = Math.random()*.04;
    this.yVelocity = Math.random()*.04;

    this.maxAge = (int)StdRandom.gaussian(2000,2000/5);
    this.age = 0;
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
