import java.util.*;//needed for ArrayList
import java.awt.*;//needed for Color


public class FishTank{
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  double swidth = screenSize.getWidth()*.9; //sets width
  double sheight = screenSize.getHeight()*.9; //sets height
  private ArrayList<Tankable> myStuff = new ArrayList<Tankable>();
  private double length, width;
  Font font;
  private int fishNum;
  private int pelletsNum;
  private double ammoniaCount;
  private Tankable oldestFish;

  public FishTank(){
    font = new Font("Arial", Font.BOLD, 20);
    StdDraw.setFont(font);
  }

  //Accessor Methods
  public double getLength(){return this.length;}
  public double getWidth(){return this.width;}
  public double getAmmonia(){return this.ammoniaCount;}

  public void update(){
    fishNum = 0;
    pelletsNum = 0;
    for(int i = 0; i<myStuff.size();i++){
      if(myStuff.get(i).isEaten() == true){
        myStuff.remove(i);
      }
      if(myStuff.get(i) instanceof Fish){
        fishNum++;
        ammoniaCount+=0.1;
      }

      else if(myStuff.get(i) instanceof Pellet){
        pelletsNum++;
        ammoniaCount+=0.02;
      }
      myStuff.get(i).update();
      if(myStuff.get(i) instanceof Piranha){
        myStuff.get(i).closest(nearestGoldfish(myStuff.get(i)));
      }

      for(int z = 0; z<myStuff.size();z++){
        if(myStuff.get(z).isEaten() == true){
          myStuff.remove(z);
        }
        //if(myStuff.get(z).tryToBreed() == true){
        //  boolean sucess = false;
        //  while(sucess ==false){
          //  if(add(new Goldfish("Goldfish",myStuff.get(z).getX()-.05-Math.random(),myStuff.get(z).getY()-.05-Math.random(),0.1f,0.1f)) == true){
          //    sucess = true;
            //}
          //}
        //}
        else{
          //  if(myStuff.get(i).isDead() == false && myStuff.get(z).isDead() == false && myStuff.get(i).d(myStuff.get(z)) <= ((myStuff.get(i).getSize())+myStuff.get(z).getSize())*2){
          if(myStuff.get(z)!= myStuff.get(i) && myStuff.get(i).isDead() == false && myStuff.get(z).isDead() == false && Math.sqrt(Math.pow((Math.abs(myStuff.get(i).getX()-myStuff.get(z).getX())),2)+Math.pow(Math.abs(myStuff.get(i).getY()-myStuff.get(z).getY()),2)) <= ((myStuff.get(i).getSize())+myStuff.get(z).getSize())*2){
            myStuff.get(i).hasCollision(myStuff.get(z));
          }
        }
      }
    }
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.textLeft((-swidth/200)+1,(sheight/200)-.8, "Fish : " + Integer.toString(fishNum));
    StdDraw.textLeft((-swidth/200)+1,(sheight/200)-1, "Ammonia : " + Float.toString(Math.round(ammoniaCount)));
    StdDraw.textLeft((-swidth/200)+1,(sheight/200)-1.2, "Pellets : " + Integer.toString(pelletsNum));
    StdDraw.textLeft((-swidth/200)+1,(sheight/200)-1.4, "Oldest Fish : " );



    show();
  }



  private void show(){
  }



  public void cleanTheTank(){
    for(int i = 0;i<myStuff.size();i++){
      if(myStuff.get(i).isDead() == true){
        remove(myStuff.get(i));
      }
    }

    ammoniaCount = 0;
  }
  public void tapTheTank(){
  }

  boolean add(Tankable t){
    boolean added =true;
    for(int z = 0; z<myStuff.size();z++){
      if(Math.sqrt(Math.pow((Math.abs(myStuff.get(z).getX()-t.getX())),2)+Math.pow(Math.abs(myStuff.get(z).getY()-t.getY()),2)) <= ((myStuff.get(z).getSize())+t.getSize())*2){
        return false;
      }

    }


    myStuff.add(t);
    return true;
  }//add a Tankable object to the FishTank


  boolean remove(Tankable t){
    boolean removed =true;
    myStuff.remove(t);
    return true;
  }//remove a Tankable object from the FishTank

  Tankable nearestGoldfish(Tankable p){
    Tankable g = null;
    double minD = Double.POSITIVE_INFINITY;
    for(int i = 0;i<myStuff.size();i++){
      if(myStuff.get(i) instanceof Goldfish && p.d(myStuff.get(i)) <minD && myStuff.get(i).isDead() == false){
        minD = p.d(myStuff.get(i));
        g = myStuff.get(i);
      }
    }
    return g;
  }//nearestGoldfish

}//FishTank
