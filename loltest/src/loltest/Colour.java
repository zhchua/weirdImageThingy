package loltest;

public class Colour {
	
	private float r;
	private float g;
	private float b;
	private float a;
	
	public Colour(float a, float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Colour(int a, int r, int g, int b){
		this.r = (float) r;
		this.g = (float) g;
		this.b = (float) b;
		this.a = (float) a;
	}
	
	public Colour(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 0;
	}
	
	public Colour(int r, int g, int b){
		this.r = (float) r;
		this.g = (float) g;
		this.b = (float) b;
		this.a = 0;
	}
	
	/** Creates a new Colour that is a copy of given Colour.
	 * 
	 * @param colour
	 */
	public Colour(Colour colour){
			this.a = colour.getA();
			this.r = colour.getR();
			this.g = colour.getG();
			this.b = colour.getB();
	}
	
	public void setR(float r){
		this.r = r;
	}
	
	public void setG(float g){
		this.g = g;
	}
	
	public void setB(float b){
		this.b = b;
	}
	
	public void setA(float a){
		this.a = a;
	}
	
	public void setRGB(float r, float g, float b){
		setR(r);
		setG(g);
		setB(b);
	}
	
	public void setARGB(float a, float r, float g, float b){
		setR(r);
		setG(g);
		setB(b);
		setA(a);
	}
	
	public float getR(){
		return this.r;
	}
	
	public float getG(){
		return this.g;
	}
	public float getB(){
		return this.b;
	}
	public float getA(){
		return this.a;
	}
	
	/** Determines equality between two floats to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	private boolean flEq(float fl1, float fl2){
		float errMar1 = (float) ((0.000001) * fl1);
		float errMar2 = (float) ((0.000001) * fl2);
		
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
	public boolean sameAs(Colour otherColour){
		if(this.isSameAlpha(otherColour) && this.isSameRGB(otherColour)){
			return true;
		}
		return false;
	}
	
	private void _________________________________________(){}
	
	/** Checks if given colour has same RGB (excludes A) as this colour.
	 * Calls flEq.
	 * 
	 * @param otherColour
	 * @return
	 */
	public boolean isSameRGB(Colour otherColour){
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
	public boolean isSameAlpha(Colour otherColour){
		if(flEq(this.getA(), otherColour.getA())){
			return true;
		}
		return false;
	}
}
