/******************************************************************************
 *  Compilation:  javac StringOps.java
 *  Execution:    java StringOps a b
 *
 *  Illustrates the integer operations a + b, a * b, and a / b.
 *
 *  % java StringOps 1234 99
 *  1234.0 + 99.0 = 1333.0
 *  1234.0 * 99.0 = 122166.0
 *  1234.0 / 99.0 = 12.464646464646465
 *  1234.0 % 99.0 = 46.0
 *
 *  % java StringOps 10 -3
 *  10.0 + -3.0 = 7.0
 *  10.0 * -3.0 = -30.0
 *  10.0 / -3.0 = -3.3333333333333335
 *  10.0 % -3.0 = 1.0
 *
 *  % java StringOps Infinity 3
 *  Infinity + 3.0 = Infinity
 *  Infinity * 3.0 = Infinity
 *  Infinity / 3.0 = Infinity
 *  Infinity % 3.0 = NaN
 *
 ******************************************************************************/

public class StringOps {

    public static void main(String[] args) {
      String a = args[0];
      String b = args[1];
      String c = args[2];

      System.out.println(a.length());
      System.out.println(b.length());
      System.out.println(c.length());

      System.out.println(a.charAt(1));
      System.out.println(b.charAt(1));
      System.out.println(c.charAt(1));

      System.out.println(a.concat(b));
      System.out.println(a.concat(c));
      System.out.println(b.concat(a));
      System.out.println(b.concat(c));
      System.out.println(c.concat(a));
      System.out.println(c.concat(b));

    }
}
