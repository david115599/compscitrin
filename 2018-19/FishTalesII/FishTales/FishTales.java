
public class FishTales{

  FishTank myTank;
  Goldfish g;
  Piranha p;
  Food f;


  public static void main(String args[]){
    System.out.println("FishTales II- A more sofishticated architecture");
    // set the scale of the coordinate system
    StdDraw.setXscale(-1.0, 1.0);
    StdDraw.setYscale(-1.0, 1.0);
    StdDraw.enableDoubleBuffering();

    // initial values
    double rx = 0.480, ry = 0.860;     // position
    double vx = 0.015, vy = 0.023;     // velocity
    double radius = 0.05;              // radius

    // main animation loop
    while (true)  {

        // bounce off wall according to law of elastic collision
        if (Math.abs(rx + vx) > 1.0 - radius) vx = -vx;
        if (Math.abs(ry + vy) > 1.0 - radius) vy = -vy;

        // update position
        rx = rx + vx;
        ry = ry + vy;

        StdDraw.clear(StdDraw.CYAN);// clear the background

        // draw ball on the screen
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledCircle(rx, ry, radius);

        StdDraw.show();// copy offscreen buffer to onscreen

        StdDraw.pause(20);// pause for 20 ms
      }//main animation loop
  }//main

}//FishTales class
