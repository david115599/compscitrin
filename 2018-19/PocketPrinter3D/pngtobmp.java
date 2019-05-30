import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.JApplet;

public class pngtobmp {

  public static void main( final String[] args ) {
    try {
      for (int i = 0;i<100 ;i++ ) {
        if (i<10) {
          File file = new File("out000"+i+".png");
          BufferedImage bi = ImageIO.read(file);
          int scalarx = bi.getWidth()/160;
          int scalary = bi.getHeight()/128;
          BufferedImage img = map( 160, 128 , bi, scalarx ,scalary );
          //img = convertToGrayScale(img);
          savePNG( img, i+".bmp" );
        }
        else if (i<100) {
          File file = new File("out00"+i+".png");
          BufferedImage bi = ImageIO.read(file);
          int scalarx = bi.getWidth()/160;
          int scalary = bi.getHeight()/128;
          BufferedImage img = map( 160, 128 , bi, scalarx ,scalary );
          //img = convertToGrayScale(img);
          savePNG( img, i+".bmp" );
        }
      }
  }catch (IOException e) {
    System.out.println("Exception occured :" + e.getMessage());
  }
  }

  private static BufferedImage map( int sizeX, int sizeY, BufferedImage bi, int scalarx , int scalary){
    final BufferedImage res = new BufferedImage( sizeX, sizeY, BufferedImage.TYPE_INT_RGB );
    int w = bi.getWidth();
     int h = bi.getHeight();
    for (int x = 0; x < sizeX; x++){
      for (int y = 0; y < sizeY; y++){
        res.setRGB(x, y, bi.getRGB( x*scalarx, y*scalary ) );
      }
    }
    return res;
  }

  public static BufferedImage convertToGrayScale(BufferedImage image) {
    BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
    Graphics g = result.getGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return result;
  }

  private static void savePNG( final BufferedImage bi, final String path ){
    try {
      RenderedImage rendImage = bi;
      ImageIO.write(rendImage, "bmp", new File(path));
    } catch ( IOException e) {
      e.printStackTrace();
    }
  }

}
