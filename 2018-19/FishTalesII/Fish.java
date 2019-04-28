import java.awt.*;//needed for Color

abstract class Fish extends LivingObject{
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  double swidth = screenSize.getWidth()*.9; //sets width
  double sheight = screenSize.getHeight()*.9; //sets height
  private String name;
  private boolean vitalSigns=false;
  private boolean beeneaten=false;
  private double randomGen;
boolean collision;

  Fish() {
    super();
    this.closestG = null;
  }//default fish constructor


  public boolean hasCollision(Tankable t){
    if(this instanceof Goldfish && t instanceof Piranha){
      //this.vitalSigns = true;
      this.beeneaten = true;
    }

    if(this instanceof Fish && t instanceof Fish){
      double thisSpeed = Math.sqrt(this.xVelocity*this.xVelocity+this.yVelocity*this.yVelocity);
      double vecLength=Math.sqrt((this.xPos-t.getX())*(this.xPos-t.getX())+(this.yPos-t.getY())*(this.yPos-t.getY()));
      this.xVelocity = (this.xPos-t.getX())/vecLength*thisSpeed;
      this.yVelocity = (this.yPos-t.getY())/vecLength*thisSpeed;

    }
    if(this instanceof Fish && t instanceof Food){
        this.size +=.01;
    }
    if(this instanceof Fish && t instanceof Poison){
        this.size -=.01;
    }

    if(this instanceof Piranha && t instanceof Goldfish){
      this.size +=.01;
    }
   collision=false;
    return collision;
  }//hasCollision

  protected void move() {

    if(this.vitalSigns == true){
      this.xVelocity = 0;
      this.yVelocity = 0.01;
      if(this.yPos > 0.9){
        this.yVelocity = 0;
      }

    }


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

  public boolean isEaten(){
    //boolean beeneaten=false;

    return beeneaten;
  }//isEaten

  abstract public boolean tryToEat(Tankable t);

}
