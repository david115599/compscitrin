import java.awt.*;//needed for Color

public class Food extends Pellet{

  Food(){
    super(new Color(0,255,0), new Color(255,255,255));
    this.yVelocity = Math.random()*0.015;
  }

  protected void show(){
    StdDraw.setPenColor(StdDraw.YELLOW);
    StdDraw.filledCircle(this.xPos, this.yPos, 0.02);
  }

  public boolean isDead(){
    boolean vitalSigns=false;
    return vitalSigns;
  }//isDead

}//Food
