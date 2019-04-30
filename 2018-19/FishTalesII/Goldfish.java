import java.awt.*;//needed for Color

class Goldfish extends Fish {

  Goldfish(String name, double x,double y) {
    super();
    this.xPos = x;
    this.yPos = y;

    this.maxSize = StdRandom.gaussian(10,10/5)/10;
    this.size = 0.05f;

    this.maxSpeed = StdRandom.gaussian(30,30/5)/10;
    this.xVelocity = Math.random()*.05;
    this.yVelocity = Math.random()*.05;

    this.maxAge = (int)StdRandom.gaussian(1000,1000/5);
    this.age = 0;
  }



  protected void show(){
    StdDraw.setPenColor(StdDraw.ORANGE);
    StdDraw.filledCircle(this.xPos, this.yPos, this.size);
  }//show

  public boolean tryToEat(Tankable t){
    boolean hasEaten = false;

    return hasEaten;
  }//tryToEat
  public boolean tryToBreed(Tankable t){
    boolean hasBred = false;

    return hasBred;
  }//tryToBreed

}//Goldfish
