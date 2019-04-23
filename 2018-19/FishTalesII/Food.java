import java.awt.*;//needed for Color

public class Food extends Pellet{

  Food(){
    super(new Color(0,255,0), new Color(255,255,255));
    this.yVelocity = -Math.random()*0.005;
    this.xPos = 1 - Math.random()*2;
  }

  protected void show(){
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledCircle(this.xPos, this.yPos, 0.02);

  }

  public boolean isDead(){
    boolean vitalSigns=false;
    return vitalSigns;
  }//isDead

}//Food
