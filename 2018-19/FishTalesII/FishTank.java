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
      if(myStuff.get(i) instanceof Piranha){
        myStuff.get(i).closest(nearestGoldfish(myStuff.get(i)));
      }

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
      if(Math.sqrt(Math.pow((Math.abs(myStuff.get(z).getX()-t.getX())),2)+Math.pow(Math.abs(myStuff.get(z).getY()-t.getY()),2)) <= ((myStuff.get(z).getSize())+t.getSize())*2){
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

  Tankable nearestGoldfish(Tankable p){
    Tankable g = null;
    double minD = Double.POSITIVE_INFINITY;
    for(int i = 0;i<myStuff.size();i++){
      if(myStuff.get(i) instanceof Goldfish && p.d(myStuff.get(i)) <minD){
      minD = p.d(myStuff.get(i));
      g = myStuff.get(i);
      }
    }
    return g;
  }//nearestGoldfish

}//FishTank
