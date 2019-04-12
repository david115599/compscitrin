import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class pngtobmp {

  public static void main(String[] args) {
    try {

      File file = new File("./test.png");
      BufferedImage bi = ImageIO.read(file);

      ImageIO.write(bi, "png", new File("./image.png"));
      ImageIO.write(bi, "jpeg", new File("./image.jpeg"));
      ImageIO.write(bi, "BMP", new File("./image.bmp"));

      System.out.println("Images were written succesfully.");

    } catch (IOException e) {
      System.out.println("Exception occured :" + e.getMessage());
    }
  }

}
