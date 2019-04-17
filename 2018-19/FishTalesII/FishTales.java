
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3c2821686ce141d8b7ca91e89667c4b1d6f96323

        if(StdDraw.isKeyPressed(71)){ //zoom in 'i' 'I'

          myTank.add(new Goldfish("Goldfish",0,0,0.05f,0.05f));
        }

        StdDraw.clear();
        myTank.update();
      



      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
<<<<<<< HEAD
=======
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7
=======
      StdDraw.clear();
      g.update();
      p.update();

      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7
>>>>>>> 3c2821686ce141d8b7ca91e89667c4b1d6f96323
  }//main

}//FishTales class
