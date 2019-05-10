import java.awt.*;//needed for Color

public class Bubble extends Pellet{
  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private boolean beeneaten=false;
  private double sheight = screenSize.getHeight()*.9; //sets height

  Bubble(double x,double y) {
    super();
    this.yVelocity = Math.random()*0.02+0.003;
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

  public boolean isDead(){
    if(this.yPos > sheight/200){
  //    beeneaten=true;
      return true;
    }
    return false;
  }

  public void tap(){

  }

  public void becomeeaten(){

  }
}//Pellet
