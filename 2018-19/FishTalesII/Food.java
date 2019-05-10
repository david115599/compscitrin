import java.awt.*;//needed for Color

public class Food extends Pellet{

  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private double width = (screenSize.getWidth()*.9)/200; //sets width
  private double height = (screenSize.getHeight()*.9)/200; //sets height

  Food(){

    super(new Color(0,255,0), new Color(255,255,255));
    this.yVelocity = -Math.random()*0.02;
    this.xPos = 2* Math.random()*width - width;
    this.yPos = height;
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
