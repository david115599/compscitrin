
public class FishTales{

  FishTank myTank;
  static Goldfish g = new Goldfish("bob",0,0,0.05f,0.05f);
  static Piranha p = new Piranha("pbob",.5*Math.random(),0,0.03f,0.05f);
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
      StdDraw.clear();
      g.update();
      p.update();

      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
  }//main

}//FishTales class
