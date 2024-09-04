package Exe.Ex2;
import java.lang.String;
import static java.lang.Double.parseDouble;//A library that converts a string to a double
/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 *
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		if (p1==null && p2==null)return true;//if the two polynomial are empty then they equal and therefore return true.
		boolean ans = true;
		int max= Math.max(p1.length, p2.length);//Defining a variable that is the maximum between the size of p1 and p2
		int min= Math.min(p1.length, p2.length);//Defining a variable that is the minimum between the size of 1 and 2
		
		for( int i=0;i<min;i++)//A loop of the minimum size between the two arrays that checks whether the coefficients of each polynomial stays up to epsilon
		{
			if (Math.max(p1[i], p2[i])-Math.min(p1[i], p2[i])>EPS) return false;		
		}
		//When one polynomial is greater than another polynomial, 
		//the loop checks whether the values ​​in the places in the large array that are not defined in the small array are equal to 0
		//If the values ​​in these places are different from 0 then the polynomials are different and therefore we will return false
		if(p1.length>p2.length) {
		for( int i=min;i<max;i++)
		{
			if (p1[i]!=0)return false;
		}
		}
		if(p1.length<p2.length) {
			for( int i=min;i<max;i++)
			{
				if (p2[i]!=0)return false;
			}
			}
		return ans;//if the two polynomials are the same we will return true
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */	
	public static double f(double[] poly, double x) {
		if (poly==null)return 0;//If the array is empty the value of the polynomial is 0
		double ans = 0;
		for(int i=0;i<poly.length;i++)
		{
			ans+=poly[i]*Math.pow(x,i);
			// loop that adds to the variable the value in the first place in the array multiplied by X to the 1st power and the value in the second place in the array multiplied by X to the 2nd power and so on until the last place in the array

		}
		return ans;//returns the polynom value at x.
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		for(int i=0;i<=poly.length-1;i++)
		{
			//If the loop has not reached the last place in the array, it will add to the string the value inside the array at the index of the number of members in the array minus 1 minus i.
			//If the loop reached the last place, simply add the first place in the array to the string.
			if(i!=poly.length-1) {
			ans+=""+poly[poly.length-1-i]+"x^"+(poly.length-1-i)+" +";
			}else {ans+=""+poly[poly.length-1-i];}
		}
		String ans2 = "";
		for(int i=0;i<ans.length();i++) {//fixing the +- problem
			if(ans.charAt(i)=='+' &&ans.charAt(i+1)=='-')ans2+=""; else ans2+=ans.charAt(i);
		}
		return ans2;//returns the string
	}

	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;//The midpoint between X1 and X2
		//
		while(Math.abs(f(p,x12))>=EPS)
			{
			//If f(x1) times f(x12) is less than 0, it means that the point of intersection with the x-axis is between them, so we will set x2 to be x12 and find a new x12.
			if(f(p,x1)*f(p,x12)>0)
				{
					x1=x12;
				}
			//If f(x12) multiplied by f(x2) is less than 0, this means that the intersection point with the x-axis is between them, so we will set x1 to be x12 and find a new x12.
		if(f(p,x1)*f(p,x12)<0)
		{
			x2=x12;
		}
		x12 = (x1+x2)/2;
			}
		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;//The midpoint between X1 and X2
		//If f(x1) times f(x12) is less than 0, it means that the point of intersection with the x-axis is between them, so we will set x2 to be x12 and find a new x12.
		if(f(p,x1)*f(p,x12)>0)
		{
			x1=x12;
		}
		//If f(x12) multiplied by f(x2) is less than 0, this means that the intersection point with the x-axis is between them, so we will set x1 to be x12 and find a new x12.
        if(f(p,x1)*f(p,x12)<0)
         {
        	x2=x12;
          }
            x12 = (x1+x2)/2;//create new x12
            if(Math.abs(f(p,x12))>=EPS) {//If we still haven't found such a value, we will run the function one more time, but now in a smaller environment
            return root_rec(p, x1, x2, eps);
            }
		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */

	//The function checks the values ​​of one polynomial minus the other epsilon.
 //If the difference is less than epsilon, then they are equal up to epsilon value.
 //otherwise,will repeat the loop, if it is greater than epsilon, they are not equal up to epsilon value.
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		while(Math.abs((f(p1,x12)-f(p2,x12)))>=eps)
			// while loop that check if  the Absolute value of the value of x in one polynomial 
			//is bigger than its value in the other polynomial and compares it to epsilon.
		{
		if((f(p1,x1)-f(p2,x1))*(f(p1,x12)-f(p2,x12))>0)
		//If the value of x in one polynomial is bigger than its value in the other polynomial
			{
				x1=x12;
			}
	if((f(p1,x1)-f(p2,x1))*(f(p1,x12)-f(p2,x12))<0)
		//If the value of x in one polynomial is less than its value in the other polynomial
	{
		x2=x12;
	}
	x12 = (x1+x2)/2;
		}
		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and a]
	 * n integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfBoxes - a natural number representing the number of boxes between x1 and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	
   //The function takes 2 polynomials, compares them, identifies the area between their common x's.
	//and then activates the Riemann method to calculate the area between polynomials.
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		double widthBox = (x2-x1)/numberOfBoxes;
		// Divide the area into boxes according to the Reimann method
		for(int i=0;i<numberOfBoxes;i++) {
		//A loop that ran from I to the number of boxes
		if((x1+i*(widthBox))>=x2) break;
		//Takes the minimum value of X2 plus the width of the boxes and checks if it is less than X2
		double HighBox = Math.max(f(p1,x1+i*(widthBox)), f(p2,x1+i*(widthBox)))-Math.min(f(p1,x1+i*(widthBox)), f(p2,x1+i*(widthBox)));
		ans+=widthBox*HighBox;
		//The maximum value of 2 polynomials minus the minimum of 2 polynomials
		//and performs an area calculation formula by multiplying height by width
		}
		return ans;
	}

	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
*/
//The function checks the values ​​of one polynomial and compares it to the other polynomial if equality exists
	public static double[] getPolynomFromString(String p) {
		
		String[] a = p.split("x");
		double[] b = new double[a.length];
		int count = 0;
		b[0] += parseDouble(a[0]);
		String sum="";
		for (int i = 1; i < b.length; i++) 
		//A  for loop that ran from 1 over the size of the array of b
		{
			while(count <= a[i].length()-1) {
				if (a[i].charAt(count) == '+') {
					count++;
					while (count <= a[i].length()-1) {
						sum += a[i].charAt(count);
						count++;
		// 2 while loop which sums up all the members of the amarch and becomes a string
					}
				}
				if(count > a[i].length()-1)break;
				if (a[i].charAt(count) == '-') 
				//If the total is greater than the size of the array minus 1 then we are done
				// if the array is the size of a string inside the total then enters the next loop
					{
					while (count <= a[i].length()-1) {
						sum += a[i].charAt(count);
						count++;
					//while loop that run up to the size of the array-list minus 1 .
						// check if the total Small or equal to the size of array.
					}
					
				}
				count++;
			}
			try {
				b[i] = parseDouble(sum);
			}catch(NumberFormatException ex){}
			sum = "";
			count = 0;
		//The function checks if our variable is of double type, if not then it will catch it and ignore it.
		}
		double []d = new double[b.length];
		for(int i=0;i<b.length;i++)//for loop that run all over the array-list.
		{
				d[i]=b[b.length-i-1]; // array of d gets the size of B's ​​array minus I minus 1.
		}
		return d;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {

		for(int i=0;i<Math.max(p1.length , p2.length);i++)
			// for loop that run from o up to maximum of size of 2 polynomials.
		{
			p1[i]+=p2[i]; //A polynomial 1 in a certain place is equal to a polynomial 2 in a certain place
		}
		return p1;
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		double[] a = new double[p1.length-1+p2.length-1+1];
		for(int i=0;i<p1.length;i++)
			// for loop that run all over the size of polynom 1
		{
			for(int j=0;j<p2.length;j++)
				// for loop in for loop that run all over the size of polynom 2
			{
				a[i+j]+=p1[i]*p2[j];
				//  The element in the array of A PLUS j in the I place
			}
		}
		return a;
	}
	/**
	 * This function computes the derivative polynom of po.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		double [] a = new double [po.length-1];
		for(int i=0;i<a.length;i++)
		{
			a[i]=po[i+1]*(i+1);
	//A loop that defines the first term in a to be the second term in po multiplied by 2.
// the second term in a defines to be the third term in po multiplied by 3 
// so on for the entire array po except for the last place.
		}
		return a;
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing three points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 * Note: you can assume xx[0]!=xx[1]!=xx[2]
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans= new double[3];
		if(xx!=null && yy!=null && xx.length==3 && yy.length==3)
		// if xx and yy arrays not empty and the size of both is 3 than go to the formula
			{
			//Formula that i took from the Internet 
			//(https://math.stackexchange.com/questions/680646/get-polynomial-function-from-3-points)
			ans[2]=(xx[0]*(yy[2]-yy[1])+xx[1]*(yy[0]-yy[2])+xx[2]*(yy[1]-yy[0]))/((xx[0]-xx[1])*(xx[0]-xx[2])*(xx[1]-xx[2]));
			ans[1]=(yy[1]-yy[0])/(xx[1]-xx[0])-ans[2]*(xx[0]+xx[1]);
			ans[0]=yy[0]-ans[2]*Math.pow(xx[0], 2)-ans[1]*xx[0];
			return ans;
		}
		return null;
	}
	///////////////////// Private /////////////////////
	// you can add any additional functions (private) below
	
}


