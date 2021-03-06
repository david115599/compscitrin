import java.awt.*;//needed for Color

abstract class LivingObject implements Tankable{
  protected FishTank tank;
  protected double xVelocity, yVelocity, maxSpeed,speed;
  protected double xPos, yPos, size, maxSize;
  protected Color fillColor, outlineColor;
  protected int age, maxAge;
  protected Tankable closestG;
  protected double ammoniaCount;


  public void update() {
    move();
    show();
  }//update


  //Accessor Methods
  public double getSize(){return size/2;}
  public int getAge(){return age/2;}
  public double getX() {return xPos;}
  public double getY() {return yPos;}
  public double getXVelocity() {return xVelocity;}
  public double getYVelocity() {return yVelocity;}

  public double setSize(){return size/2;}
  public double setX(double xPostemp) {return this.xPos = xPostemp;}
  public double setY(double yPostemp) {return this.yPos = yPostemp;}
  public double setXVelocity(double xVelocitytemp) {return this.xVelocity=xVelocitytemp;}
  public double setYVelocity(double yVelocitytemp) {return this.yVelocity=yVelocitytemp;}

  public double setAmmonia(double ammonia){return this.ammoniaCount = ammonia;}
  public double d(Tankable s){ //Get distance to another tankable
    return(Math.sqrt(((this.getX()-s.getX()) * (this.getX()-s.getX())) + ((this.getY()-s.getY()) * (this.getY()-s.getY()))));
  }

  public void closest(Tankable t){
    this.closestG = t;
  }


  public void bounce(){
  this.xVelocity = -this.xVelocity;
  this.yVelocity = -this.yVelocity;
  }

  public abstract boolean isDead();
  public abstract boolean hasCollision(Tankable t);
  abstract protected void move();
  protected abstract void show();

}//LivingObject
