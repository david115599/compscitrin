import java.util.*;//needed for ArrayList
import java.awt.*;//needed for Color


public class FishTank{
  private ArrayList<Tankable> myStuff = new ArrayList<Tankable>();
  private float ammoniaCount;
  private double length, width;

  public FishTank(){
  }

  //Accessor Methods
  public double getLength(){return this.length;}
  public double getWidth(){return this.width;}
  public double getAmmonia(){return this.ammoniaCount;}

  public void update(){
    for(int i = 0; i<myStuff.size();i++){
      myStuff.get(i).update();
      for(int z = 0; z<myStuff.size();z++){
        if(checkCollision(myStuff.get(i),myStuff.get(z)){

        }
      }
    }
    show();
  }

  private void checkCollision(Tankable me, Tankable other){
    
  }


  private void show(){
  }

  public void cleanTheTank(){
  }
  public void tapTheTank(){
  }

  boolean add(Tankable t){
    boolean added =true;
    myStuff.add(t);
    return true;
  }//add a Tankable object to the FishTank

  boolean remove(Tankable t){
    boolean removed =true;

    return true;
  }//remove a Tankable object from the FishTank

  Goldfish nearestGoldfish(Piranha p){
    Goldfish g = null;

    return g;
  }//nearestGoldfish

}//FishTank
