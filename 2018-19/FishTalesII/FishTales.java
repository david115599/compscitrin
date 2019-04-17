
public class FishTales{



  public static void main(String args[]){
    FishTank myTank = new FishTank();
    Goldfish g = new Goldfish("bob",0,0,0.05f,0.05f);
    Piranha p;
    Food f;

    System.out.println("FishTales II- A more sofishticated architecture");
    // set the scale of the coordinate system
    StdDraw.setXscale(-1.0, 1.0);
    StdDraw.setYscale(-1.0, 1.0);
    StdDraw.enableDoubleBuffering();

    // main animation loop
    while (true)  {

        if(StdDraw.isKeyPressed(71)){ //zoom in 'i' 'I'

          myTank.add(new Goldfish("Goldfish",0,0,0.05f,0.05f));
        }

        StdDraw.clear();
        myTank.update();
      



      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
  }//main

}//FishTales class
