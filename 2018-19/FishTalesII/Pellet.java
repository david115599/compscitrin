import java.awt.*;//needed for Color

abstract class Pellet extends LivingObject{

  Pellet() {
    super();
    
  }//default constructor

  Pellet(Color fillColor, Color outlineColor) {
  }//default constructor

  protected void move() {
    this.yPos = this.yPos + this.yVelocity;
    if(this.yPos < -0.99){
      this.yVelocity = 0;
    }
  }//move

  public boolean hasCollision(Tankable t){
    boolean collision=false;
    return collision;
  }//hasCollision

}//Pellet
