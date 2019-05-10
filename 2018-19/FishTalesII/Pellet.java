import java.awt.*;//needed for Color

abstract class Pellet extends LivingObject{
  private boolean beeneaten=false;
  Pellet() {
    super();

  }//default constructor

  Pellet(Color fillColor, Color outlineColor) {
  }//default constructor

  protected void move() {
    this.yPos = this.yPos + this.yVelocity;
    if(this.yPos < -4.99){
      this.yVelocity = 0;
    }
  }//move

  public boolean hasCollision(Tankable t){
    if(this instanceof Food && t instanceof Fish ){
        beeneaten=true;
    }
    if(this instanceof Poison && t instanceof Fish ){
        beeneaten=true;
    }
    boolean collision=false;
    return collision;
  }//hasCollision
  public boolean isEaten(){
    //boolean beeneaten=false;

    return beeneaten;
  }//isEaten

  public void becomeeaten(){
    beeneaten = true;
  }


  public void tap(){

  }

  public void setName(String s){

  }

  public void setType(String t){

  }

  public String getName(){
    return "pellet";
  }

  public String getType(){
    return "pellet";
  }
}//Pellet
