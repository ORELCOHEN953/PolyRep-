package Exe.Ex2;
import static java.lang.Double.parseDouble;//A library that converts a string to a double
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	 double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1, 0.1,3};

	@Test
    void testF() {
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0,2);
		assertEquals(fx1,4);
		assertEquals(fx2,6);
	}

	@Test
	void testRoot() {
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}
	
	@Test
	void testAdd() {
		double[] p12 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(p12, pp2);
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));
	}

	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);
		double dd = Ex2.f(p12, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
	}
	@Test
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);
		assertEquals(dp1[0], dp2[0],Ex2.EPS);
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);
	}
	@Test
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1x^2+2.3x-1.1
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);
		boolean isSame = Ex2.equals(p, p1);
		if(!isSame) {fail();}
		assertEquals(sp, Ex2.poly(p1));
	}
	
	///////////////////////////////////////SELF
	
	@Test 
	void testequals() {
		boolean check = Ex2.equals(po1, po2); //  check if polynomial 1 equals to polynomial 2
		double fx1 = Ex2.f(po1, 1); //posting
		double fx2 = Ex2.f(po1, 2); //posting
		boolean check2 = fx1==fx2; // check if The Y value of polynomial 1 equals to The Y value of polynomial 2
		assertEquals(check,check2);
	}
	
	@Test 
	void testFunctions() {
		 double[] a1 = new double[po1.length]; // posting
		 double[] a2 = new double[po2.length]; // posting
			for(int i=0;i<po1.length;i++) 
			// for loop that run all over the size of polynomial 1
				{
				a1[i]=po1[i]; // the index in a1 array-list is equals to the index of polynomial 1
			}
			for(int i=0;i<po2.length;i++) {
			// for loop that run all over the size of polynomial 2	
				a2[i]=po2[i];
			//// the index in a2 array-list is equals to the index of polynomial 2	
			}
		double[] pol1 = Ex2.add(po1, po2);// Addition function on the 2 polynomials
		String s1= Ex2.poly(a1);// posting
		String s2= Ex2.poly(a2);// posting
		double[] b1 = Ex2.getPolynomFromString(s1); // array makes polynom from string
		double[] b2 = Ex2.getPolynomFromString(s2); // array makes polynom from string
		double[] pol2 = Ex2.add(b1, b2);  // array makes polynom from string
		boolean isSame = Ex2.equals(pol1,pol2);  // Compares the values
		if(!isSame) {fail();}
		// check if is not the same so fail
	   	String ans=Ex2.poly(po1);//posting
		for(int i=0;i<ans.length();i++)
		// for loop that runs all over the array.
		{
		if(ans.charAt(i)=='+' &&ans.charAt(i+1)=='-') {fail();}
		//check for string +-
		}
	}
	@Test 
	void testroot_rec() {
		double x1 = Ex2.root(po1, 0, 10, Ex2.EPS);//posting
		double x2 = Ex2.root_rec(po1, 0, 10, Ex2.EPS);//posting
		double x3 = Ex2.root_rec(po1, 0, 10, Ex2.EPS);//posting
		assertEquals(x1,x2,x3); // assert is fanction that check if thay both equals if not is fail
		
		double[]p1= {0,0,1};//posting
		double[]p2= {0,0,-1};//posting
		double t1 = Ex2.root(p1, -1,1,Ex2.EPS);//posting
		double t2 = Ex2.root_rec(p1, -1,1,Ex2.EPS);//posting
		assertEquals(t1,t2);// assert is function that check if they both equals if not is fail
	}
	
	@Test
    void testderivative() {
		double []a1=Ex2.derivative(po1); //Constructing a derivative function
		double []a2=Ex2.derivative(po2); //Constructing a derivative function
		double []a12=Ex2.add(a1,a2); //posting at function add 
		double []b=Ex2.add(po1,po2);//posting
		double []bDerivative=Ex2.derivative(b);//Checks if the 2 derivatives are equal
		boolean isSame = Ex2.equals(bDerivative,a12);
		if(!isSame) {fail();}
		// check if is not the same so fail
	}
	
	@Test
	void testPolynomFromPoints() {
		double []xx= {6,8666666,2.555}; //posting
		double []yy= {7711,8.655478886,32.878964323}; //posting
		double [] p= Ex2.PolynomFromPoints(xx,yy);
		for(int i=0;i<2;i++)
		// for loop that runs from 0  up to 2 not included
		{
		boolean test = Ex2.f(p,xx[i])-yy[i]<=Ex2.EPS;
		if(!test) {fail();}
		// check if is not the same so fail
		}
	}
	@Test
    void testarea() {
		double x1 = Ex2.sameValue(po1,po2, -5,0,Ex2.EPS);//posting
		double x2 = Ex2.sameValue(po1,po2, 0,5,Ex2.EPS);//posting
		double area1 = 0;//posting
		int numberOfBoxes = 13;
		double delta = (x2-x1)/numberOfBoxes;
		for(double x = x1;x<x2;x+=delta) 
		// for loop that runs from the value of x up to x2 
		{
			double y1 = Ex2.f(po1, x);//posting
			double y2 = Ex2.f(po2, x);//posting
			double y12 = (y1+y2)/2;//posting
			double dist = Math.abs(y1-y2);//posting
			area1 += dist*delta; //update
		}
		double area2 = Ex2.area(po1, po2, x1, x2, numberOfBoxes); // posting
		if(Math.abs(area1-area2)>=Ex2.EPS) {fail();}
		// check if the area in abs math will be bigger than EPS if so than it is fail
	}
	
	@Test
    void testsameValue() {
    double[] p = {};//empty arrayList
	double x12 = Ex2.sameValue(po1,p, 0, 10, Ex2.EPS);
	assertEquals(x12, 3.1958, Ex2.EPS);
	}
}
