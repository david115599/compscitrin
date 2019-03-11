/**
* @author      Justin Ghode and David Bershadsky
* @version     1.0
*/
import java.lang.Math;
public class ComplexNumber {

	private double a;
	private double b;

	/**
	* Creates a new ComplexNumber with both real and imaginary components
	* @param a the real component of the complex number
	* @param b the imaginary component of the complex number
	*/
	public ComplexNumber(){
		this.a = 0;
		this.b = 0;
	}
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;
	}
	/**
	* The "copy constructor"
	* Creates a new ComplexNumber from an existing ComplexNumber
	* @param c a ComplexNumber
	*/
	public ComplexNumber(ComplexNumber c){
		a = c.getReal();
		b = c.getImaginary();
	}
	/**
	* adds two complex numbers
	* @param c1 a complex number
	* @param c2 a complex number
	* @return a complex number which is the sum of the paramiters
	*/
	public static ComplexNumber add(ComplexNumber c1,ComplexNumber c2 ){
		double	a1 = (double)c1.getReal();
		double  b1 = (double)c1.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1+a2,b1+b2);
		return c3;
	}
	/**
	* adds two complex numbers
	* @param c2 a complex number
	* @return a complex number which is the sum of the paramiters
	*/
	public ComplexNumber add(ComplexNumber c2 ){
		double	a1 = (double)this.getReal();
		double  b1 = (double)this.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1+a2,b1+b2);
		return c3;
	}
	/**
	* subtracts two complex numbers
	* @param c1 a complex number
	* @param c2 a complex number
	* @return a complex number which is the difference of the paramiters
	*/
	public static ComplexNumber subtract(ComplexNumber c1,ComplexNumber c2 ){
		double	a1 = (double)c1.getReal();
		double  b1 = (double)c1.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1-a2,b1-b2);
		return c3;
	}
	/**
	* subtracts two complex numbers
	* @param c2 a complex number
	* @return a complex number which is the difference of the paramiters
	*/
	public ComplexNumber subtract(ComplexNumber c2 ){
		double	a1 = (double)this.getReal();
		double  b1 = (double)this.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1-a2,b1-b2);
		return c3;
	}
	/**
	* multiplies two complex numbers
	* @param c1 a complex number
	* @param c2 a complex number
	* @return a complex number which is the product of the paramiters
	*/
	public static ComplexNumber multiply(ComplexNumber c1,ComplexNumber c2 ){
		double	a1 = (double)c1.getReal();
		double  b1 = (double)c1.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1*a2-b1*b2,b1*a2+b2*a1);
		return c3;
	}
	/**
	* multiplies two complex numbers
	* @param c2 a complex number
	* @return a complex number which is the product of the paramiters
	*/
	public ComplexNumber multiply(ComplexNumber c2 ){
		double	a1 = (double)this.getReal();
		double  b1 = (double)this.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1*a2-b1*b2,b1*a2+b2*a1);
		return c3;
	}
	/**
	* divides two complex numbers
	* @param c1 a complex number
	* @param c2 a complex number
	* @return a complex number which is the quotient of the paramiters
	*/
	public static ComplexNumber divide(ComplexNumber c1,ComplexNumber c2 ){
		double	a1 = (double)c1.getReal();
		double  b1 = (double)c1.getImaginary();
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		if (b2 == 0 && a2 == 0) {
			throw new IllegalArgumentException("cannot divide by zero");
		}
		ComplexNumber conjugate = new ComplexNumber(a2,b2*(-1));
		ComplexNumber temp1 =  ComplexNumber.multiply(c2,conjugate);
		ComplexNumber temp2 =  ComplexNumber.multiply(c1,conjugate);
		double	temp2a = (double)temp2.getReal();
		double  temp2b = (double)temp2.getImaginary();
		double	temp1a = (double)temp1.getReal();
		double  temp1b = (double)temp1.getImaginary();
		double temp1c = temp1a-temp1b;
		ComplexNumber c3 = new ComplexNumber(temp2a/temp1c,temp2b/temp1c);
		return c3;
	}
	/**
	* divides two complex numbers
	* @param c2 a complex number
	* @return a complex number which is the quotient of the paramiters
	*/
	public ComplexNumber divide(ComplexNumber c2 ){
		double 	a2 = (double)c2.getReal();
		double	b2 = (double)c2.getImaginary();
		if (b2 == 0 && a2 == 0) {
			throw new IllegalArgumentException("cannot divide by zero");
		}
		ComplexNumber conjugate = new ComplexNumber(a2,b2*(-1));
		ComplexNumber temp1 =  ComplexNumber.multiply(c2,conjugate);
		ComplexNumber temp2 =  ComplexNumber.multiply(this,conjugate);
		double	temp2a = (double)temp2.getReal();
		double  temp2b = (double)temp2.getImaginary();
		double	temp1a = (double)temp1.getReal();
		double  temp1b = (double)temp1.getImaginary();
		double temp1c = temp1a-temp1b;
		ComplexNumber c3 = new ComplexNumber(temp2a/temp1c,temp2b/temp1c);
		return c3;
	}
	/**
	* squares a complex
	* @param c1 a complex number
	* @return a complex number which is the square of paramiter
	*/
	public static ComplexNumber square(ComplexNumber c1 ){
		double	a1 = (double)c1.getReal();
		double  b1 = (double)c1.getImaginary();
		double 	a2 = (double)c1.getReal();
		double	b2 = (double)c1.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1*a2-b1*b2,b1*a2+b2*a1);
		return c3;
	}
	/**
	* squares a complex
	* @return a complex number which is the square of the original
	*/
	public ComplexNumber square(){
		double	a1 = (double)this.getReal();
		double  b1 = (double)this.getImaginary();
		double 	a2 = (double)this.getReal();
		double	b2 = (double)this.getImaginary();
		ComplexNumber c3 = new ComplexNumber(a1*a2-b1*b2,b1*a2+b2*a1);
		return c3;
	}
	/**
	* compares two complex numbers
	* @param c2 a complex number
	* @return either 1, 0, or -1 depending of which number is bigger or if they are the same
	*/
	public int	compareTo(ComplexNumber c2 ){
		double	a1 = this.magnitude();
		double 	a2 = c2.magnitude();
		int c3;
		if (a1<a2) {
			c3 = -1;
		}
		else if (a1>a2) {
			c3 = 1;
		}
		else if (a1==a2){
			c3 = 0;
		}
		else{
			c3 = 0;
		}
		return c3;
	}
	/**
	* calculates the magnitude of a complex number
	* @return the magnitude of the complex number
	*/
	public double magnitude( ){
		double	a1 = 	this.a;
		double  b1 = 	this.b;
		double c3 = Math.sqrt(a1*a1+b1*b1);
		return c3;
	}
	/**
	* prints the complex number in standard from
	* @return the complex number in the form of a string
	*/
	public String toString(){
		double	a1 = 	this.a;
		double  b1 = 	this.b;
		String c3 = "0";
		if (b1>0&&a1 != 0) {
			c3 = Double.toString(a1)+"+"+Double.toString(b1)+"i";
		}
	else if (b1==0 &&a1 != 0) {
			c3 = Double.toString(a1);
		}
		else if (a1==0 &&b1 != 0) {
			c3 = Double.toString(b1);
		}
		else if (b1<0 &&a1 != 0) {
			c3 = Double.toString(a1)+Double.toString(b1)+"i";
		}
		else if (b1==0.0&&a1==0.0) {
			c3 = "0";
		}

		return c3;
	}
	/**
	* An "accessor" method
	* Returns the real component of this ComplexNumber
	* @return a the private real component of this ComplexNumber
	*/
	public double getReal(){
		return a;
	}

	/**
	* An "accessor" method
	* Returns the imaginary component of this ComplexNumber
	* @return b the private imaginary component of this ComplexNumber
	*/
	public double getImaginary(){
		return b;
	}

	/**
	* A tester method
	* @param args
	*/
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(1, -2);
		ComplexNumber c2 = new ComplexNumber(2, -4);
		ComplexNumber cc1 = new ComplexNumber(20, -4);
		ComplexNumber cc2 = new ComplexNumber(3, 2);

		System.out.println("Specific Constructor, Accessor. (c1:1-2i r:1 i:-2):");
		System.out.println("c1:"+c1+" r:"+c1.getReal()+" i"+c1.getImaginary());
		System.out.println("Copy Constructor, Accessor. (c2:2-4i r:2 i:-4):");
		System.out.println("c2:"+c2+" r:"+c2.getReal()+" i:"+c2.getImaginary());
		ComplexNumber c4 = ComplexNumber.add(c1,c2);
		System.out.println();
		System.out.println("add ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:3 i:-6):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(c4).getReal()+" i:"+(c4).getImaginary());
		ComplexNumber cc4 = c1.add(c2);
		System.out.println();
		System.out.println("add ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:3 i:-6):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(cc4).getReal()+" i:"+(cc4).getImaginary());
		ComplexNumber c5 = ComplexNumber.subtract(c1,c2);
		System.out.println();
		System.out.println("subtract ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:-1 i:2):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(c5).getReal()+" i:"+(c5).getImaginary());
		ComplexNumber cc5 = c1.subtract(c2);
		System.out.println();
		System.out.println("subtract ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:-1 i:2):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(cc5).getReal()+" i:"+(cc5).getImaginary());
		ComplexNumber c6 = ComplexNumber.multiply(c1,c2);
		System.out.println();
		System.out.println("multiply ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:-6 i:-8):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(c6).getReal()+" i:"+(c6).getImaginary());
		ComplexNumber cc6 = c1.multiply(c2);
		System.out.println();
		System.out.println("multiply ComplexNumber, Accessor. (c1:1-2i c2:2-4i r:-6 i:-8):");
		System.out.println("c1:"+c1+"c2:"+c2+" r:"+(cc6).getReal()+" i:"+(cc6).getImaginary());
		ComplexNumber c7 = ComplexNumber.divide(cc1,cc2);
		System.out.println();
		System.out.println("divide ComplexNumber, Accessor. (cc1:20-4i cc2:3+2i r:4 i:-4):");
		System.out.println("cc1:"+cc1+"cc2:"+cc2+" r:"+(c7).getReal()+" i:"+(c7).getImaginary());
		ComplexNumber cc7 = cc1.divide(cc2);
		System.out.println();
		System.out.println("divide ComplexNumber, Accessor. (cc1:20-4i cc2:3+2i r:4 i:-4):");
		System.out.println("cc1:"+cc1+"cc2:"+cc2+" r:"+(cc7).getReal()+" i:"+(cc7).getImaginary());
		ComplexNumber c8 = ComplexNumber.square(cc1);
		System.out.println();
		System.out.println("square ComplexNumber, Accessor. (cc1:20-4i r:384 i:-160):");
		System.out.println("cc1:"+cc1+" r:"+(c8).getReal()+" i:"+(c8).getImaginary());
		ComplexNumber cc8 = cc1.square();
		System.out.println();
		System.out.println("square ComplexNumber, Accessor. (cc1:20-4i r:384 i:-160):");
		System.out.println("cc1:"+cc1+" r:"+(cc8).getReal()+" i:"+(cc8).getImaginary());
		double c9 = cc1.magnitude();
		System.out.println();
		System.out.println("magnitude ComplexNumber, Accessor. (cc1:20-4i r:20.396078054371138):");
		System.out.println("cc1:"+cc1+" r:"+(c9));
		int c10 = cc1.compareTo(cc2);
		System.out.println();
		System.out.println("compareTo ComplexNumber, Accessor. (cc1:20-4i cc2:3+2i r:4 i:-4):");
		System.out.println("cc1:"+cc1+"cc2:"+cc2+" r:"+(c10));
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c4.toString());
		System.out.println(c5.toString());
		System.out.println(c6.toString());
		System.out.println(c7.toString());
		System.out.println(c8.toString());
		System.out.println(cc1.toString());
		System.out.println(cc2.toString());



		ComplexNumber ca1 = ComplexNumber.add(new ComplexNumber(0,-1),new ComplexNumber(0,0));

		System.out.println(ca1.toString());
	}
}
