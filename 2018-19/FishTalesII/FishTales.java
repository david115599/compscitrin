
public class FishTales{



  public static void main(String args[])throws InterruptedException {
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

      if(StdDraw.isKeyPressed(71)){ //G

        if(myTank.add(new Goldfish("Goldfish",0,0,0.05f,0.05f))){
        }
        else{
          myTank.add(new Goldfish("Goldfish",Math.random()*.05,Math.random()*.05,0.05f,0.05f));
        }
      }

      else if(StdDraw.isKeyPressed(80)){ //P

        if(myTank.add(new Piranha("Piranha",0,0,0.05f,0.05f))){
        }
        else{
          myTank.add((new Piranha("Piranha",Math.random()*.05,Math.random()*.05,0.05f,0.05f)));
        }

      }

      else if(StdDraw.isKeyPressed(84)){ //P

        if(myTank.add(new ToroidalFin("ToroidalFin",0,0,0.05f,0.05f))){
        }
        else{
          myTank.add((new ToroidalFin("ToroidalFin",Math.random()*.05,Math.random()*.05,0.05f,0.05f)));
        }

      }

      else if(StdDraw.isKeyPressed(67)){
        myTank.cleanTheTank();
      }

      else if(StdDraw.isKeyPressed(70)){ //F

        myTank.add(new Food());
        myTank.add(new Food());
        myTank.add(new Food());
        myTank.add(new Food());
        myTank.add(new Food());
        myTank.add(new Food());
      }

      //StdDraw.clear(StdDraw.BLUE);
      StdDraw.clear();
      myTank.update();



      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop

  }//main



}//FishTales class
