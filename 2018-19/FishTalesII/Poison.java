import java.awt.*;//needed for Color

public class Poison extends Pellet{

  Poison(){
    super(new Color(0,255,0), new Color(255,255,255));
    this.yVelocity = -Math.random()*0.005;
    this.xPos = 5 - Math.random()*10;
    this.yPos = 5;
  }

  protected void show(){
    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.filledCircle(this.xPos, this.yPos, 0.02);

  }

  public boolean isDead(){
    boolean vitalSigns=false;
    return vitalSigns;
  }//isDead

}//Food
