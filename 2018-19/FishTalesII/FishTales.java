
public class FishTales{

<<<<<<< HEAD
=======
  FishTank myTank;
  static Goldfish g = new Goldfish("bob",0,0,0.05f,0.05f);
  static Piranha p = new Piranha("pbob",.5*Math.random(),0,0.03f,0.05f);
  Food f;
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7


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
<<<<<<< HEAD

        if(StdDraw.isKeyPressed(71)){ //zoom in 'i' 'I'

          myTank.add(new Goldfish("Goldfish",0,0,0.05f,0.05f));
        }

        StdDraw.clear();
        myTank.update();
        g.update();


        StdDraw.show();// copy offscreen buffer to onscreen

        StdDraw.pause(20);// pause for 20 ms
      }//main animation loop
=======
      StdDraw.clear();
      g.update();
      p.update();

      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7
=======
      StdDraw.clear();
      g.update();
      p.update();

      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop
>>>>>>> 741fc9d05d6e2f8613720af0b6c278a43f0399f7
  }//main

}//FishTales class
