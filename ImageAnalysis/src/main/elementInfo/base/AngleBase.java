package main.elementInfo.base;

public class AngleBase{
	
	private double value;
	
	/** Create a new Angle with a float angle.
	 * 
	 * @param angle
	 */
	public AngleBase(double angle){
		this.value = angle;
	}
	
	/** Initializes Angle as a copy of given Angle.
	 * 
	 * @param angle
	 */
	public AngleBase(AngleBase angle){
		this.value = angle.getValue();
	}
	
	public double getValue(){
		return this.value;
	}
	
	public void setValue(double angle){
		this.value = angle;
	}
	
	/** Returns the current angle in radians.
	 * 
	 * @return
	 */
	public double inRadians(){
		return Math.toRadians(this.value);
	}
	
	/** Determines equality between two floating pt numbers 
	 * to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	private boolean flEq(double fl1, double fl2){
		float errMar1 = (float) ((0.000001) * fl1);
		float errMar2 = (float) ((0.000001) * fl2);
		
		if(Math.abs(fl1 - fl2) == 0 || Math.abs(fl1 - fl2) < errMar1 
				&& Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	/** Checks if given Angle has the same attribute values as this Angle.
	 * 
	 * @param otherAngle
	 * @return
	 */
	public boolean sameAs(AngleBase otherAngle){
		if( flEq( this.getValue() , otherAngle.getValue() ) ){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return Double.toString(this.getValue()) + " " + "degrees";
	}

}
	
