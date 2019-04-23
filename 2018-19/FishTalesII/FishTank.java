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
        if(Math.sqrt(Math.pow((Math.abs(myStuff.get(i).getX()-myStuff.get(z).getX())),2)+Math.pow(Math.abs(myStuff.get(i).getY()-myStuff.get(z).getY()),2)) <= ((myStuff.get(i).getSize())+myStuff.get(z).getSize())*2){
          myStuff.get(i).hasCollision(myStuff.get(z));
        }
      }
    }
    show();
  }



  private void show(){
  }

  public void cleanTheTank(){
  }
  public void tapTheTank(){
  }

  boolean add(Tankable t){
    boolean added =true;
    for(int z = 0; z<myStuff.size();z++){
      if(myStuff.get(z).getX()==0 || myStuff.get(z).getY()==0){
            return false;
      }

    }
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
