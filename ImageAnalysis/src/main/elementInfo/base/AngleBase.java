package main.elementInfo.base;

import main.utility.BaseObject;

public class AngleBase extends BaseObject{
	
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
	 * @throws Exception 
	 */
	public AngleBase(AngleBase givenAngleBase){
		super(givenAngleBase);
		this.value = givenAngleBase.getValue();
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
	
	/** Checks if given Angle has the same attribute values as this Angle.
	 * 
	 * @param otherAngle
	 * @return
	 */
	public boolean sameAs(AngleBase givenAngle){
		if(givenAngle == null){
			return false;
		}
		else return flEq(this.getValue() , givenAngle.getValue());
	}
	
	public String toString(){
		return Double.toString(this.getValue()) + " " + "degrees";
	}
}
	
