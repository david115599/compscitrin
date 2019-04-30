import java.awt.*;//needed for Color

abstract class Bubble extends LivingObject{
  private boolean beeneaten=false;
  Bubble(double x,double y) {
    super();
    this.yVelocity = Math.random()*0.02;
    this.xPos = x;
    this.yPos = y;
  }//default constructor

  Bubble(Color fillColor, Color outlineColor) {
  }//default constructor

  protected void show(){
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.circle(this.xPos, this.yPos, .05);
  }//show

  protected void move() {
    this.yPos = this.yPos + this.yVelocity;
    if(this.yPos < 4.99){
      this.yVelocity = 0;
    }
  }//move

  public boolean hasCollision(Tankable t){
    if(this instanceof Bubble && t instanceof Fish ){
        beeneaten=true;
    }
    boolean collision=false;
    return collision;
  }//hasCollision
  public boolean isEaten(){
    //boolean beeneaten=false;

    return beeneaten;
  }//isEaten

  public void setAmmonia(double a){

  }
}//Pellet
