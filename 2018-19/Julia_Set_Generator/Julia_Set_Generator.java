import java.awt.Color;
/**
* @author      David Bershadsky
* @version     1.0
*/
/*
Julia_Set_Generator
By David Bershadsky
This Program generates a mandelbrot set and allows the user to explore it and graph julia sets that corespond to where they click
*/
import java.lang.Math;
import java.awt.Dimension;
import java.awt.Toolkit;
public  class Julia_Set_Generator {
  public static double map(double x, double in_min, double in_max, double out_min, double out_max)
  {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
  }
  /**
  * graphs complex numbers on the left side of the window
  * @param c1 a complex number
  * @param zoom the zoom scalar
  * @param focuspoint complex number
  * @return graphs a complex point
  */
  public static void graphcomplexnumber(ComplexNumber c1, double zoom, ComplexNumber focuspoint){
    StdDraw.point(0.25+((c1.getReal()-focuspoint.getReal())/8)*zoom,.5+((c1.getImaginary()-focuspoint.getImaginary())/3)*zoom);
    StdDraw.setPenColor(StdDraw.BLACK);
  }
  /**
  * graphs complex numbers on the right side of the window
  * @param c1 a complex number
  * @param zoom the zoom scalar
  * @param centerpoint complex number
  * @return graphs a complex point
  */
  public static void graphcomplexnumberj(ComplexNumber c1, double zoom, ComplexNumber centerpoint){
    StdDraw.point(0.75+((c1.getReal()-centerpoint.getReal())/8)*zoom,.5+((c1.getImaginary()-centerpoint.getImaginary())/3)*zoom);
    //  StdDraw.point(0.25+(c1.getReal()/8)*zoom,.5+((c1.getImaginary())/3)*zoom);
    StdDraw.setPenColor(StdDraw.BLACK);
  }
  /**
  * recusivley calculates the madelbrot set and julia set
  * @param Z a complex number
  * @param C the zoom scalar
  * @param I an integer
  * @return weither a value is in the madelbrot set or julia set or neither
  */
  public static int mandl(ComplexNumber Z, ComplexNumber C,int i){
    Z=C.add(Z.square());
    if (Z.magnitude()>2) {return(i);
    }
    else if (i>255) {
      return(0);
    }
    else{
      return(mandl(Z,C,i+1));
    }
  }
  /**
  * graphs mandelbrot set
  * @param width a Double
  * @param height a Double
  * @param range a Double
  * @param domain a Double
  * @param resolution a Double
  * @param zoom a Double
  * @param focuspoint complex number
  * @return graphs a mandelbrot set
  */
  public static void graphmandelbrotset(double width, double height, double range, double domain, double resolution, double zoom, ComplexNumber focuspoint){
    if (zoom>0) {
      StdDraw.setPenRadius((1/height)*zoom);//*zoom*zoom);
    }
    if (zoom<0) {
      StdDraw.setPenRadius((1/height)/(-1*zoom));//*zoom*zoom);
    }
    double max = 0;
    ComplexNumber  C= new ComplexNumber(0,0);
    for (double a =focuspoint.getReal()-domain/zoom;a<=focuspoint.getReal()+domain/zoom ;a+=resolution/zoom ) {
      for (double b = focuspoint.getImaginary()-range/zoom; b<=focuspoint.getImaginary()+range/zoom;b+=resolution/zoom ) {
        ComplexNumber  CN= new ComplexNumber(a,b);
        StdDraw.setPenColor(Color.getHSBColor((1-(float) (mandl(C,CN,1))/10), 1f, 1f));
        //StdDraw.setPenColor((int)map((mandl(C,CN,1)), (0), 400, 0, 255), 80,   (int)map((mandl(C,CN,1)*3), (0), 400, 0, 255));
        graphcomplexnumber(CN, zoom, focuspoint);
      }

    }
    //System.out.println("done");
  }

  /**
  * graphs julia set
  * @param width a Double
  * @param height a Double
  * @param range a Double
  * @param domain a Double
  * @param resolution a Double
  * @param zoom a Double
  * @param focuspoint complex number
  * @param centerpoint complex number
  * @return graphs a julia set
  */
  public static void graphjuliaset(double width, double height, double range, double domain, double resolution, double zoom, ComplexNumber focuspoint, ComplexNumber centerpoint){
    if (zoom>0) {
      StdDraw.setPenRadius(1/height*zoom);//*zoom*zoom);
    }
    if (zoom<0) {
      StdDraw.setPenRadius(1/height/(-1*zoom));//*zoom*zoom);
    }
    double max = 0;
    ComplexNumber  C = focuspoint;
    for (double a =centerpoint.getReal()-domain/zoom;a<=centerpoint.getReal()+domain/zoom ;a+=resolution/zoom ) {
      for (double b = centerpoint.getImaginary()-range/zoom; b<=centerpoint.getImaginary()+range/zoom;b+=resolution/zoom ) {
        //  for (double a =-domain/zoom;a<=domain/zoom ;a+=resolution/zoom ) {
        //    for (double b = -range/zoom; b<=range/zoom;b+=resolution/zoom ) {
        ComplexNumber  CN= new ComplexNumber(a,b);
        StdDraw.setPenColor(Color.getHSBColor((1-(float) (mandl(CN,C,1))/40), 1f, 1f));
        //StdDraw.setPenColor((int)map((mandl(C,CN,1)), (0), 400, 0, 255), 80,   (int)map((mandl(C,CN,1)*3), (0), 400, 0, 255));
        graphcomplexnumberj(CN, zoom, centerpoint);
      }

    }
    //System.out.println("done");
  }
  public static void main(String[] args) {
    StdDraw.enableDoubleBuffering();
    double zoomm = .99999999999999999;
    double zoomj = .99999999999999999;
    ComplexNumber focuspoint = new ComplexNumber(0,0);
    ComplexNumber focuspointj = new ComplexNumber(0,0);
    ComplexNumber centerpoint = new ComplexNumber(0,0);
    double range = 1.5; //sets range
    double resolution =(.006); //sets resolution
    double domain = 2; // sets domain
    ComplexNumber c1 = new ComplexNumber(1,2);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = (((((screenSize.getHeight())/2)*2)/1.5)*2); //sets width
    double height = ((screenSize.getHeight())/2); //sets height
    StdDraw.setCanvasSize((int)width,(int)height);
    //  graphcomplexnumber(c1);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.005);
    StdDraw.line(0.5, 1, 0.5, 0);
    StdDraw.setPenRadius(0.001);
    StdDraw.line(0,.5,.5,.5 );
    StdDraw.line(.25,0,.25,1 );
    StdDraw.line(.5,.5,1,.5 );
    StdDraw.line(.75,0,.75,1 );
    graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
    /*  for (int i = 0 ;i<1000 ;i++ ) {//graphing tester
    graphcomplexnumber(new ComplexNumber(2*Math.random(),1.5*Math.random()));
    graphcomplexnumber(new ComplexNumber(-2*Math.random(),1.5*Math.random()));
    graphcomplexnumber(new ComplexNumber(2*Math.random(),-1.5*Math.random()));
    graphcomplexnumber(new ComplexNumber(-2*Math.random(),-1.5*Math.random()));
  }*/
  //System.out.println("done");
  double x = ((double)(int)((StdDraw.mouseX()*4*zoomm)*1000))/1000;
  double y = ((double)(int)((StdDraw.mouseY()*1.5*zoomm)*1000))/1000;
  focuspoint = new ComplexNumber(0,0);
  while (true) {
    if (StdDraw.mouseX()<=.5 ) {
      x = ((double)(int)((StdDraw.mouseX()*4*zoomm)*1000))/1000;
      y = ((double)(int)((StdDraw.mouseY()*1.5*zoomm)*1000))/1000;
    }
    else if(StdDraw.mouseX()>.5 ) {
      x = ((double)(int)((StdDraw.mouseX()*4*zoomj)*1000))/1000;
      y = ((double)(int)((StdDraw.mouseY()*1.5*zoomj)*1000))/1000;
    }

    StdDraw.setPenColor(StdDraw.WHITE);
    if (StdDraw.mousePressed()){
      StdDraw.setPenColor(StdDraw.CYAN);
      if (StdDraw.mouseX()<=.5 ) {
        focuspoint = new ComplexNumber((x*2/zoomm-2)/zoomm+focuspoint.getReal(),(y*2/zoomm-1.5)/zoomm+focuspoint.getImaginary());
        focuspointj = new ComplexNumber((x*2/zoomm-2)/zoomm+focuspoint.getReal(),(y*2/zoomm-1.5)/zoomm+focuspoint.getImaginary());
      }
      if(StdDraw.mouseX()>.5 ) {
        centerpoint = new ComplexNumber(((x*2/zoomj-2)/zoomj+centerpoint.getReal())-4.0/zoomj,(y*2/zoomj-1.5)/zoomj+centerpoint.getImaginary()+0.0);
      }
      //  System.out.println((x*2/zoom-2)/zoom+","+(y*2/zoom-1.5)/zoom+"i");
      StdDraw.clear();
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }
    if (StdDraw.isKeyPressed(73)){
      StdDraw.clear();
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.005);
      StdDraw.line(0.5, 1, 0.5, 0);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(0,.5,.5,.5 );
      StdDraw.line(.25,0,.25,1 );
      StdDraw.line(.5,.5,1,.5 );
      StdDraw.line(.75,0,.75,1 );
      if (StdDraw.mouseX()<=.5 ) {
        zoomm+=.2;
      }
      else if(StdDraw.mouseX()>.5 ) {
        zoomj+=.2;
      }

      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }
    if (StdDraw.isKeyPressed(79)){
      StdDraw.clear();
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.005);
      StdDraw.line(0.5, 1, 0.5, 0);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(0,.5,.5,.5 );
      StdDraw.line(.25,0,.25,1 );
      StdDraw.line(.5,.5,1,.5 );
      StdDraw.line(.75,0,.75,1 );
      if (StdDraw.mouseX()<=.5 ) {
        zoomm-=.2;
      }
      else if(StdDraw.mouseX()>.5 ) {
        zoomj-=.2;
      }
      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }


    if (StdDraw.mouseX()<=.5 ) {
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(.25,.95,.05,.05);
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.text( .25,  .95, "x:"+(((double)((int)((((StdDraw.mouseX()*(8))-2)+focuspoint.getReal())/zoomm*100)))/100)+" y:"+(((double)((int)((((StdDraw.mouseY()*(3))-1.5)+focuspoint.getImaginary())/zoomm*100))/100)));
    }
    else if(StdDraw.mouseX()>.5 ) {
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(.75,.95,.05,.05);
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.text( .75,  .95, "x:"+(((double)((int)((((StdDraw.mouseX()*(8))-6)+centerpoint.getReal())/zoomj*100)))/100)+" y:"+(((double)((int)((((StdDraw.mouseY()*(3))-1.5)+centerpoint.getImaginary())/zoomj*100))/100)));
    }

    if (StdDraw.isKeyPressed(37)){
      if (StdDraw.mouseX()<=.5 ) {
        focuspoint = new ComplexNumber(focuspoint.getReal()-.1,focuspoint.getImaginary());
      }
      else if(StdDraw.mouseX()>.5 ) {
        centerpoint = new ComplexNumber(centerpoint.getReal()-.1,centerpoint.getImaginary());
      }
      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      StdDraw.clear();
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }
    if (StdDraw.isKeyPressed(39)){
      if (StdDraw.mouseX()<=.5 ) {
        focuspoint = new ComplexNumber(focuspoint.getReal()+.1,focuspoint.getImaginary());
      }
      else if(StdDraw.mouseX()>.5 ) {
        centerpoint = new ComplexNumber(centerpoint.getReal()+.1,centerpoint.getImaginary());
      }
      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      StdDraw.clear();
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }
    if (StdDraw.isKeyPressed(38)){
      if (StdDraw.mouseX()<=.5 ) {
        focuspoint = new ComplexNumber(focuspoint.getReal(),focuspoint.getImaginary()-.1);
      }
      else if(StdDraw.mouseX()>.5 ) {
        centerpoint = new ComplexNumber(centerpoint.getReal(),centerpoint.getImaginary()-.1);
      }
      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      StdDraw.clear();
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }
    if (StdDraw.isKeyPressed(40)){
      if (StdDraw.mouseX()<=.5 ) {
        focuspoint = new ComplexNumber(focuspoint.getReal(),focuspoint.getImaginary()+.1);
      }
      else if(StdDraw.mouseX()>.5 ) {
        centerpoint = new ComplexNumber(centerpoint.getReal(),centerpoint.getImaginary()+.1);
      }
      //focuspoint = new ComplexNumber((x*2/zoom-2)/zoom,(y*2/zoom-1.5)/zoom);
      StdDraw.clear();
      graphmandelbrotset(width,height,range,domain,resolution,zoomm,focuspoint);
      graphjuliaset(width, height, range, domain, resolution, zoomj, focuspointj, centerpoint);
    }

    StdDraw.show();
    //  System.out.println("x:"+x+" y:"+y);
    //  StdDraw.pause(10);
  }
}
}
