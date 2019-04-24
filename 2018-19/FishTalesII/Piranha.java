import java.awt.*;//needed for Color

class Piranha extends Fish {

  Piranha(String name, double x,double y, double h, double w) {
    super();
    this.xPos = x;
    this.yPos = y;
    this.size = h;

    this.xVelocity = Math.random()*.005;
    this.yVelocity = Math.random()*.005;

    this.closestG = null;
  }

  protected void move() {

    if (Math.abs(this.xPos + this.xVelocity) > 1.0 - this.size) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > 1.0 - this.size) this.yVelocity = -this.yVelocity;

    if(closestG != null){
    //  it currently ignores collisions and needs to maintain their old velocity
    //this.xVelocity = (closestG.getX()-this.xPos)/this.d(closestG)*.01;
    //this.yVelocity = (closestG.getY()-this.yPos)/this.d(closestG)*.01;
    }

    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;

  }//move



  protected void show(){
    StdDraw.setPenColor(StdDraw.RED);
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
