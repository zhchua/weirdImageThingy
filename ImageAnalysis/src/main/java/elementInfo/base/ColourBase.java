package elementInfo.base;

import java.util.ArrayList;
import java.util.List;

import exceptions.ARGBRangeException;
import exceptions.NullObjectException;
import utility.BaseObject;

public class ColourBase extends BaseObject{

	private double a;
	private double r;
	private double g;
	private double b;

	public ColourBase(double a, double r, double g, double b){
		if(argbIsValid(a, r, g, b)){
			this.setARGB(a, r, g, b);
		}
	}

	public ColourBase(double r, double g, double b){
		if(argbIsValid(0, r, g, b)){
			this.setARGB(0, r, g, b);
		}
	}
	
	/** Creates a strict copy of given Colour.
	 * 
	 * @param colour
	 */
	public ColourBase(ColourBase givenColourBase){
		super(givenColourBase);

		if(argbIsValid(givenColourBase)){
			this.setARGB(givenColourBase.getA(), givenColourBase.getR()
						, givenColourBase.getG(), givenColourBase.getB());
		}
	}

	/** Checks ARGB for nullity and values <0 or >255. Throw exception if invalid values found.
	 * 
	 */
	private boolean argbIsValid(double a, double r, double g, double b){
		double[] argbArr = {a, r, g, b};
		for(int i = 0; i < 4; i++){
			if(argbArr[i] < 0 || argbArr[i] > 255){
				throw new ARGBRangeException();
			}
		}
		return true;
	}
	
	/** Checks ARGB for nullity and values <0 or >255. Throw exception if invalid values found.
	 * 
	 */
	private boolean argbIsValid(ColourBase givenColourBase){
		return argbIsValid(givenColourBase.getA(), givenColourBase.getR()
				, givenColourBase.getG(), givenColourBase.getB());
	}

	public void setA(double a){
		if(argbIsValid(a, r, g, b)){
			this.a = a;
		}
	}
	
	public void setR(double r){
		if(argbIsValid(a, r, g, b)){
			this.r = r;
		}
	}

	public void setG(double g){
		if(argbIsValid(a, r, g, b)){
			this.g = g;
		}
	}

	public void setB(double b){
		if(argbIsValid(a, r, g, b)){
			this.b = b;
		}
	}

	public void setRGB(double r, double g, double b){
		setARGB(this.getA(), r, g, b);
	}

	public void setARGB(double a, double r, double g, double b){
		setA(a);
		setR(r);
		setG(g);
		setB(b);
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

	/** Checks if given colour has same RGB (excludes A) as this colour.
	 * Calls flEq.
	 * 
	 * @param givenColour
	 * @return
	 */
	public boolean isSameRGB(ColourBase givenColour){
		return (flEq(this.getR(), givenColour.getR())
				&& flEq(this.getG(), givenColour.getG())
				&& flEq(this.getB(), givenColour.getB()));
	}

	/** Checks if given colour has same Alpha channel value as this colour.
	 * Calls flEq.
	 * 
	 * @param givenColour
	 * @return
	 */
	public boolean isSameAlpha(ColourBase givenColour){
		if(givenColour == null){
			throw new NullObjectException();
		}
		return flEq(this.getA(), givenColour.getA());
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
	public List<Integer> getBitsArray(){
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
