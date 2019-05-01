import java.awt.Color;
import java.lang.Math;
import java.awt.Dimension;
import java.awt.Toolkit;
public class FishTales{



  public static void main(String args[])throws InterruptedException {
    FishTank myTank = new FishTank();
    Goldfish g = new Goldfish("bob",0,0);
    Piranha p;
    Food f;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth()*.9; //sets width
    double height = screenSize.getHeight()*.9; //sets height
    double ammonia = 0;
    boolean pressed = false;
    StdDraw.setCanvasSize((int)width,(int)height);

    System.out.println("FishTales II- A more sofishticated architecture");
    // set the scale of the coordinate system
    StdDraw.setXscale(-width/200, width/200);
    StdDraw.setYscale(-height/200, height/200);
    StdDraw.enableDoubleBuffering();

    // main animation loop
    while (true)  {
      if(StdDraw.isMousePressed()){
        if(pressed == false){
          for(int i = 0;i<(int)(Math.random()*10+4);i++){
            myTank.add(new Bubble(StdDraw.mouseX()+Math.random()*width/3200-width/6400,StdDraw.mouseY()+Math.random()*height/3200-height/6400));
          }
          myTank.tapTheTank();
        }
        pressed = true;
      }
      else{
        pressed = false;
      }

      //Creates Goldfish
      if(StdDraw.isKeyPressed(71)){ //G
        if(myTank.add(new Goldfish("Goldfish",(2*Math.random()*width/200)-width/200,(2*Math.random()*height/200)-height/200))){
        }

        else{
          myTank.add((new Goldfish("Goldfish",Math.random()*.05+(2*Math.random()*height/200)-height/200,Math.random()*.05+(2*Math.random()*height/200)-height/200)));
        }
      }


      //Creates Piranha
      else if(StdDraw.isKeyPressed(80)){ //P
        if(myTank.add(new Piranha("Piranha",(2*Math.random()*width/200)-width/200,(2*Math.random()*height/200)-height/200))){
        }
        else{
          myTank.add((new Piranha("Piranha",Math.random()*.05+(2*Math.random()*height/200)-height/200,Math.random()*.05+(2*Math.random()*height/200)-height/200)));
        }

      }

      //Creates Whale
      else if(StdDraw.isKeyPressed(87)){ //P
        if(myTank.add(new Whale("Whale",(2*Math.random()*width/200)-width/200,(2*Math.random()*height/200)-height/200))){
        }
        else{
          myTank.add((new Whale("Whale",Math.random()*.05+(2*Math.random()*height/200)-height/200,Math.random()*.05+(2*Math.random()*height/200)-height/200)));
        }
      }

      //Creates ToroidalFin
      else if(StdDraw.isKeyPressed(84)){ //P
        if(myTank.add(new ToroidalFin("ToroidalFin",(2*Math.random()*width/200)-width/200,(2*Math.random()*height/200)-height/200))){
        }
        else{
          myTank.add((new ToroidalFin("ToroidalFin",Math.random()*.05+(2*Math.random()*height/200)-height/200,Math.random()*.05+(2*Math.random()*height/200)-height/200)));
        }
      }

      //Cleans the tank
      else if(StdDraw.isKeyPressed(67)){
        myTank.cleanTheTank();
      }

      //Creates Food
      else if(StdDraw.isKeyPressed(70)){ //F
      if (Math.random() <=.5) {
        myTank.add(new Poison());
      }
      else{
        myTank.add(new Food());
      }
      }

      double R = (220.0)/(20000.0);

      double y = (ammonia*R)+R;
      ammonia = myTank.getAmmonia();
      ammonia = Math.min(20000.0,ammonia);

      StdDraw.clear(new Color((int)y, 200,200+(int)y/100));

      myTank.update();



      StdDraw.show();// copy offscreen buffer to onscreen

      StdDraw.pause(20);// pause for 20 ms
    }//main animation loop

  }//main



}//FishTales class
