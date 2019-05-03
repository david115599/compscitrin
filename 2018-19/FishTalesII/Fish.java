import java.awt.*;//needed for Color


abstract class Fish extends LivingObject{
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  double swidth = screenSize.getWidth()*.9; //sets width
  double sheight = screenSize.getHeight()*.9; //sets height
  private String name;
  private boolean vitalSigns=false;
  private boolean beeneaten=false;
  private double randomGen;
  protected boolean breed = false;
  private double ammoniaNum = 0;
  boolean collision;

  Fish() {
    super();
    this.breed = false;
    this.closestG = null;
  }//default fish constructor


  public boolean hasCollision(Tankable t){
    if(this instanceof Goldfish && t instanceof Piranha){
      //this.vitalSigns = true;
      this.beeneaten = true;
    }
    if(this instanceof Piranha && t instanceof Piranha && this.size < t.getSize()){
      //this.vitalSigns = true;
      this.beeneaten = true;
    }
    if(this instanceof Goldfish && t instanceof Goldfish){
      if (Math.random()<= .2) {
        this.breed = true;
      }
    }

    if(this instanceof Fish && t instanceof Fish){
      double thisSpeed = Math.sqrt(this.xVelocity*this.xVelocity+this.yVelocity*this.yVelocity);
      double vecLength=Math.sqrt((this.xPos-t.getX())*(this.xPos-t.getX())+(this.yPos-t.getY())*(this.yPos-t.getY()));
      this.xVelocity = (this.xPos-t.getX())/vecLength*thisSpeed;
      this.yVelocity = (this.yPos-t.getY())/vecLength*thisSpeed;
    }

    if(this instanceof Fish && t instanceof Food){
        this.size +=t.getSize()*.2;
    }
    if(this instanceof Fish && t instanceof Poison){
        this.size -=t.getSize()*.2;
    }

    if(this instanceof Piranha && t instanceof Goldfish){
      this.size +=t.getSize()*.2;
    }
   collision=false;
    return collision;
  }//hasCollision

  protected void move() {
    //If dead, float to the top
    if(this.vitalSigns == true){
      this.xVelocity = 0;
      this.yVelocity = 0.01;
      if(this.yPos > sheight/200-(this.size/2)-.1){
        this.yVelocity = 0;
      }

    }

    //Checks size, fish dies if it's too big
    if(this.size>=this.maxSize){
      this.vitalSigns = true;
    }

    //Checks ammonia, increased age based on ammonia count
    if(this.ammoniaNum >=10000){
      this.age+=1;
    }
    else{
      this.age+=2;
    }
    //Checks age, fish dies if it's too old
    if(this.age>=this.maxAge){
      this.vitalSigns = true;
    }
    //Updates age
    this.age+=0.05;

    randomGen = Math.random();

    if(randomGen >= 0.5){
      this.xVelocity = this.xVelocity+ 0.1*Math.random()*this.xVelocity;
    }else if(randomGen <= 0.5){
      this.xVelocity = this.xVelocity- 0.1*Math.random()*this.xVelocity;
    }
    randomGen = Math.random();
    if(randomGen >= 0.5){
      this.yVelocity = this.yVelocity+ 0.1*Math.random()*this.yVelocity;
    }else if(randomGen <= 0.5){
      this.yVelocity = this.yVelocity- 0.1*Math.random()*this.yVelocity;
    }
if (Math.abs(this.xVelocity) <=.01) {
  this.xVelocity*=2;
}
if (Math.abs(this.yVelocity) <=.01) {
  this.yVelocity*=2;
}
    if(this.xVelocity >=0.06){
      this.xVelocity -= 0.005;
    }
    else if(this.xVelocity <=-0.06){
      this.xVelocity += 0.005;
    }
    if(this.yVelocity >=0.06){
      this.yVelocity -= 0.005;
    }
    else if(this.yVelocity <=-0.06){
      this.yVelocity += 0.005;
    }
    if (Math.abs(this.xPos + this.xVelocity) > swidth/200 - this.size && !(this instanceof ToroidalFin)) this.xVelocity = -this.xVelocity;
    if (Math.abs(this.yPos + this.yVelocity) > sheight/200 - this.size && !(this instanceof ToroidalFin)) this.yVelocity = -this.yVelocity;
    if (Math.abs(this.xPos + this.xVelocity) > swidth/200 - this.size && (this instanceof ToroidalFin)) this.xPos = -this.xPos;
    if (Math.abs(this.yPos + this.yVelocity) > sheight/200 - this.size && (this instanceof ToroidalFin)) this.yPos = -this.yPos;
    this.xPos = this.xPos + this.xVelocity;
    this.yPos = this.yPos + this.yVelocity;

    if(this instanceof Whale){
      this.yVelocity = 0;
    }
    if(this instanceof Piranha && collision != true){
      if(closestG != null){
      // //  it currently ignores collisions and needs to maintain their old velocity
      // double targetX = (closestG.getX()-this.xPos)/this.d(closestG);
      // double targetY = (closestG.getY()-this.yPos)/this.d(closestG);
      // int xSign = 1;
      // int ySign = 1;
      //
      // if(targetX<this.xVelocity){
      //   xSign = -1;
      // }
      //
      // if(targetY<this.yVelocity){
      //   ySign = -1;
      // }
      //
      // if(Math.abs(this.xVelocity+(xSign*.01)<Math.abs(this.maxSpeed) && Math.abs(this.yVelocity+(ySign*.01)<Math.abs(this.maxSpeed)){
      //   this.xVelocity+=xSign*.01;
      //   this.yVelocity-=ySign*.01;
      // }
      // else{
      //   this.xVelocity = targetX;
      //   this.yVelocity = targetY;
      // }
      //

       this.xVelocity = (closestG.getX()-this.xPos)/this.d(closestG)*this.maxSpeed;
       this.yVelocity = (closestG.getY()-this.yPos)/this.d(closestG)*this.maxSpeed;

      }
    }
  }//move

  public int compareTo(Fish f){
    return 0;
  }//compareTo

  public boolean isDead(){
    //boolean vitalSigns=false;

    return vitalSigns;
  }//isDead

  public void setAmmonia(double am){
    this.ammoniaNum = am;
  }

  public boolean isEaten(){
    //boolean beeneaten=false;

    return beeneaten;
  }//isEaten

  abstract public boolean tryToEat(Tankable t);

  public boolean tryToBreed(Tankable t){
    boolean hasBred = false;
    if (Math.random()<=.2) {
      hasBred = true;
    }


    return hasBred;
  }//tryToBreed


  //Tap the Tank
  public void tap(){
    double thisSpeed = Math.sqrt(this.xVelocity*this.xVelocity+this.yVelocity*this.yVelocity);
    this.xVelocity = Math.random()-0.5;
    this.yVelocity = Math.random()-0.5;
    double newSpeed = Math.sqrt(this.xVelocity*this.xVelocity+this.yVelocity*this.yVelocity);
    this.xVelocity = (this.xVelocity)/newSpeed*thisSpeed;
    this.yVelocity = (this.yVelocity)/newSpeed*thisSpeed;
  }
}
