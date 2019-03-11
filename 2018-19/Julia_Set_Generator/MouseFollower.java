 public class MouseFollower{
    public static void main(String[] args) {

        while (true) {
            // mouse click
            if (StdDraw.mousePressed()) 
                StdDraw.setPenColor(StdDraw.CYAN);
            else                          
                StdDraw.setPenColor(StdDraw.BLUE);

            // mouse location
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            System.out.println("x:"+x+" y:"+y);
            StdDraw.filledCircle(x, y, 0.05);
            StdDraw.pause(10);
        }
    }
}
