package main.elementInfo.base;

import java.util.ArrayList;

public class ColourBase {

	private double a;
	private double r;
	private double g;
	private double b;


	public ColourBase(double a, double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		invalidNumberCheck();
	}

	public ColourBase(int a, int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		invalidNumberCheck();
	}

	public ColourBase(double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 0;
		invalidNumberCheck();
	}

	public ColourBase(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 0;
		invalidNumberCheck();
	}

	/** Creates a strict copy of given Colour.
	 * 
	 * @param colour
	 */
	public ColourBase(ColourBase colour){
		this.a = colour.getA();
		this.r = colour.getR();
		this.g = colour.getG();
		this.b = colour.getB();
		invalidNumberCheck();
	}

	private void _____GETTERS_AND_SETTERS_____(){}

	/** Checks ARGB for values <0 or >255. Throw exception if invalid values found.
	 * 
	 */
	private void invalidNumberCheck(){
		if(this.a < 0 || this.a > 255){
			throw new IllegalArgumentException("Alpha is out of range.");
		}
		if(this.r < 0 || this.r > 255){
			throw new IllegalArgumentException("Red is out of range.");
		}
		if(this.g < 0 || this.g > 255){
			throw new IllegalArgumentException("Green is out of range.");
		}
		if(this.b < 0 || this.b > 255){
			throw new IllegalArgumentException("Blue is out of range.");
		}
	}
	
	public void setR(double r){
		this.r = r;
	}

	public void setG(double g){
		this.g = g;
	}

	public void setB(double b){
		this.b = b;
	}

	public void setA(double a){
		this.a = a;
	}

	public void setRGB(double r, double g, double b){
		setR(r);
		setG(g);
		setB(b);
	}

	public void setARGB(double a, double r, double g, double b){
		setR(r);
		setG(g);
		setB(b);
		setA(a);
	}

	public double getR(){
		return this.r;
	}

	public double getG(){
		return this.g;
	}
	public double getB(){
		return this.b;
	}
	public double getA(){
		return this.a;
	}

	/** Determines equality between two doubles to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	private boolean flEq(double fl1, double fl2){
		double errMar1 = ((0.000001) * fl1);
		double errMar2 = ((0.000001) * fl2);

		if(Math.abs(fl1 - fl2) == 0 || Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}

	/** Checks if this colour has the same ARGB values as given colour.
	 * 
	 * @param otherColour
	 * @return
	 */
	public boolean sameAs(ColourBase otherColour){
		if(this.isSameAlpha(otherColour) && this.isSameRGB(otherColour)){
			return true;
		}
		return false;
	}



	/** Checks if given colour has same RGB (excludes A) as this colour.
	 * Calls flEq.
	 * 
	 * @param otherColour
	 * @return
	 */
	public boolean isSameRGB(ColourBase otherColour){
		if(flEq(this.getR(), otherColour.getR())
				&& flEq(this.getG(), otherColour.getG())
				&& flEq(this.getB(), otherColour.getB())){
			return true;
		}
		return false;
	}

	/** Checks if given colour has same Alpha channel value as this colour.
	 * Calls flEq.
	 * 
	 * @param otherColour
	 * @return
	 */
	public boolean isSameAlpha(ColourBase otherColour){
		if(flEq(this.getA(), otherColour.getA())){
			return true;
		}
		return false;
	}

	/** Returns the integer of n to the power of p. Integers only.
	 * 
	 * @param n
	 * @param p
	 * @return
	 */
	private int pow(int n, int p){
		// cant believe i needed to write this
		if(p == 0){
			return 1;
		}
		if(p == 1){
			return n;
		}
		int prod = n;
		for(int i = 2; i <= p; i++){
			prod = prod*n;
		}
		return prod;
	}

	/** Get an ArrayList representing the bit pattern of this Colour's ARGB.
	 * 
	 * @param num
	 * @return
	 */
	public ArrayList<Integer> getBitsArray(){
		ArrayList<Integer> bits = new ArrayList<Integer>();
		int col = 0;
		int[] colours = {(int) this.getA()
				,(int) this.getR()
				,(int) this.getG()
				,(int) this.getB()};

		// 128, 64, 32, 16, 8, 4, 2, 1
		for(int clrInx = 0 ; clrInx < colours.length; clrInx++){
			col = colours[clrInx];
			for( int i = 7; i >= 0 ; i--){
				if(col / pow(2,i) >= 1){
					bits.add(1);
					col = col - pow(2,i);
				}
				else bits.add(0);
			}
		}
		return bits;
	}

	public String getBitsString(){
		StringBuilder argbStrBld = new StringBuilder();
		for(int i = 0; i < getBitsArray().size(); i++){
			argbStrBld.append(getBitsArray().get(i));
		}
		return argbStrBld.toString();
	}

	public int getIntARGB(){
		return (int) Long.parseLong(getBitsString(), 2);
	}
}
