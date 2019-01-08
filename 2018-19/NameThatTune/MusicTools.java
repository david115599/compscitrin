public class MusicTools{

  /**
  * <h1> prints the content of an array </h1>
  *@param array, an array of double values
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static void printArray(double array[]){
    System.out.println("printArray");
    if (array.length>0) {
      for (int i=0;i<array.length-1 ;i++ ) {
        System.out.print(array[i]+", ");
      }
      System.out.print(array[array.length-1]);
      System.out.println();
    }
  }

  /**
  * <h1> Converts the array to a string</h1>
  *@param array, an array of double values
  *@return, a string of the input array
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static String arrayToString(double array[]){
    System.out.println("arrayToString");
    String out ="";
    if (array.length>0) {
      for (int i=0;i<array.length-1 ;i++ ) {
        out += array[i] + ", ";
      }
      out += array[array.length-1];
    }
    return(out);
  }

  /**
  * <h1> Reverses the content of an array </h1>
  *@param array, an array of double values
  *@return, the inverse of the input array
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] reverseArray(double array[]){
    System.out.println("reverseArray");
    double out[] = new double[array.length];
    if (array.length>0) {
      for (int i=array.length-1;i>=0 ;i-- ) {
        //System.out.println(" i= "+i+" array.length"+(array.length-i+1));
        out[array.length-i-1] = array[i];
      }
    }
    return(out);
  }


  /**
  * <h1> generates a random Array </h1>
  *@param n, an integer
  *@return, a random array of length n
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] randarray(int n){
    System.out.println("randarray");
    double out[] = new double[n];
    if (n>0) {
      for (int i=0;i<n ;i++ ) {
        out[i]= (int)(Math.random()*10);
      }
    }
    return(out);
  }

  /**
  * <h1> returns the max value of an array </h1>
  *@param array, an array of double values
  *@return, the max value of on array in an array of length 1
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double findArrayMax(double array[]){
    System.out.println("findArrayMax");
    //    double[] ret = new double[1];
    double c = Double.NEGATIVE_INFINITY;
    if (array.length>0) {
      c = array[0];
      for (int i=0;i<array.length ;i++ ) {
        if (array[i]>c) {
          c=array[i];
        }
      }
      //      ret[0]= c;
    }
    return(c);
  }

  /**
  * <h1> returns the min value of an array </h1>
  *@param array, an array of double values
  *@return, the min value of on array in an array of length 1
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double findArrayMin(double array[]){
    System.out.println("findArrayMin");
    //    double[] ret = new double[1];
    double c = Double.POSITIVE_INFINITY;
    if (array.length>0) {
      c = array[0];
      for (int i=0;i<array.length ;i++ ) {
        if (array[i]<c) {
          c=array[i];
        }
      }
      //      ret[0]= c;
    }
    return(c);
  }

  /**
  * <h1> returns the average value of an array </h1>
  *@param array, an array of double values
  *@return, the average value of on array in an array of length 1
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double arrayave(double array[]){
  //  System.out.println("arrayave");
    double c=0;
    if (array.length>0) {
      for (int i=0;i<array.length ;i++ ) {
        c+= array[i];
      }
    }
    c=c/array.length;
    return(c);
  }

  /**
  * <h1>  Returns true if the arrays are equal </h1>
  *@param array, an array of double values
  *@param array1, an array of double values
  *@return, Returns true if the arrays are equal
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static boolean arraysAreEqual(double array[], double array1[]){
    System.out.println("arraysAreEqual");
  //  printArray(array);
  //  printArray(array1);
    if(array.length == array1.length){
      for(int i=0; i<array.length; i++){
        if (array[i] != array1[i]) {
          return(false);
        }
      }
      return(true);
    }
    return(false);
  }

  /**
  * <h1>  Returns an array scaled by n </h1>
  *@param array, an array of double values
  *@param n, an integer value
  *@return, Returns an array scaled by n
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] scaleArray(double array[], int n){
    System.out.println("scaleArray");
    double[] ret = new double[array.length];
    if (array.length>0) {
      for (int i=0;i<array.length ;i++ ) {
        ret[i]=array[i]*n;
      }
      return(ret);
    }
    return(ret);
  }

  /**
  * <h1>Returns an array composed of the wieghted addition of the two input arrays</h1>
  *@param array, an array of double values
  *@param array1, an array of double values
  *@return, Returns an array composed of the wieghted addition of the two input arrays
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] weightedAddArray(double array[], double array1[] ,double arrayscalar ,double array1scalar){
    System.out.println("weightedAddArray");
    double[] empty_a = {};
  //  printArray(array);
  //  printArray(array1);
    if(arrayscalar+array1scalar != 1){
      return(empty_a);
    }
    int mn = (array.length>array1.length) ? array.length : array1.length;
    double[] ret = new double[mn];
    if (array.length>=array1.length) {
      for (int i=0;i<array1.length ;i++ ) {
        ret[i]=array[i]*arrayscalar+array1[i]*array1scalar;
      }
      for (int i=array1.length;i<array.length ;i++ ) {
        ret[i]=array[i]*arrayscalar;
      }
    }
    else{
      for (int i=0;i<array.length ;i++ ) {
        ret[i]=array[i]*arrayscalar+array1[i]*array1scalar;
      }
      for (int i=array.length;i<array1.length ;i++ ) {
        ret[i]=array1[i]*array1scalar;
      }
    }
    return(ret);
  }


  /**
  * <h1>Returns a section of an array copied out from two designated points</h1>
  *@param array, an array of double values
  *@param n, interger value for the location of the copy start.
  *@param n1, interger value for the location of the copy end.
  *@return, Returns a section of an array copied out from two designated points
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] copyArray(double array[], int n, int n1){
    System.out.println("copyArray");
  //  printArray(array);
    double[] empty_a = {};
    if (n > n1 || n<0) {
      return(empty_a);
    }
    double[] ret = new double[n1-n];
    if (array.length>=n1) {
      for (int i=n;i<n1 ;i++ ) {
        ret[i-n]+=array[i];
      }
      return(ret);
    }
    return(empty_a);
  }

  /**
  * <h1>returns the array with a section missing</h1>
  *@param array, an array of double values
  *@param n, interger value for the location of the cut start.
  *@param n1, interger value for the location of the cut end.
  *@return, returns the array with a section missing
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] cutArray(double array[], int n, int n1){
    System.out.println("cutArray");
  //  printArray(array);
    double[] empty_a = {};
    if (n>n1 || n<0 || n1>array.length) {
      return(empty_a);
    }
    double[] ret = new double[array.length-n1+n];
    for (int i=0;i<n ;i++ ) {
      ret[i]=array[i];
    }
    for (int i=n1;i<array.length ;i++ ) {
      ret[i-(n1-n)]=array[i];
    }
    return(ret);
  }


  /**
  * <h1>concats two arrays </h1>
  *@param array, an array of double values
  *@param array1, an array of double values
  *@return, returns a concatination of the two arrays
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] concatArray(double array[], double array1[]){
    System.out.println("concatArray");
    //printArray(array);
    //printArray(array1);
    double[] ret = new double[array.length+array1.length];
    for (int i=0;i<array.length ;i++ ) {
      ret[i]=array[i];
    }
    for (int i=0;i<array1.length ;i++ ) {
      ret[i+array.length]=array1[i];
    }
    return(ret);
  }

  /**
  * <h1>splices two arrays together at a given point </h1>
  *@param array, an array of double values
  *@param array1, an array of double values
  *@param n, an interger value for the begining of the splice
  *@return, returns an array which is the splicing of two arrays at a given Point
  *@author, By David Claude Michalovich Bershadsky
  **/
  public static double[] spliceArray(double array[],double array1[], int n){
    System.out.println("spliceArray");
  //  printArray(array);
  //  printArray(array1);
    double[] ret = new double[array.length+array1.length];
    double[] empty_a = {};
    if(n<0 || n>array.length){
      return empty_a;
    }
    if(n==0){
      ret = concatArray(array1,array);
      return(ret);
    }
    //  System.out.println("ret length = "+ret.length);
    for (int i=0;i<n ;i++ ) {
      //  System.out.println("i1= "+i);
      ret[i]=array[i];
    }
    for (int i=0;i<array1.length ;i++ ) {
      //  System.out.println("i2= "+i);
      ret[n+i]=array1[i];
    }
    for (int i=n;i<array.length ;i++ ) {
      //  System.out.println("i3= "+i);
      ret[i+array1.length]+=array[i];
    }
    return(ret);
  }





  public static void main(String args[]){
    int n1 = 2;
    int n2 = 2;
    int n3 = 2;
    int n4 = 2;

    double[] a = randarray(n1);
    System.out.println();
    System.out.println("done");
    System.out.println();

    double[] b = randarray(n2);
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println("printing a");
    printArray(a);
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println("printing b");
    printArray(b);
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(reverseArray(a));
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println(arrayave(a));
    System.out.println();
    System.out.println("done");
    System.out.println();

    findArrayMax(a);
    System.out.println();
    System.out.println("done");
    System.out.println();

    findArrayMin(a);
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println(arraysAreEqual(a,b));
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println(arraysAreEqual(a,a));
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println(arraysAreEqual(b,b));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(scaleArray(a,n1));
    System.out.println();
    System.out.println("done");
    System.out.println();

    //printArray(weightedAddArray(a,a));
    System.out.println();
    System.out.println("done");
    System.out.println();

    //  printArray(weightedAddArray(b,b));
    System.out.println();
    System.out.println("done");
    System.out.println();

    //  printArray(weightedAddArray(a,b));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(copyArray(a,n3,n4));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(cutArray(a,n3,n4));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(spliceArray(a,a,n3));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(spliceArray(b,b,n3));
    System.out.println();
    System.out.println("done");
    System.out.println();

    printArray(spliceArray(a,b,n3));
    System.out.println();
    System.out.println("done");
    System.out.println();

    System.out.println(arrayToString(a));
    System.out.println();
    System.out.println("done");
    System.out.println();


  }
}
