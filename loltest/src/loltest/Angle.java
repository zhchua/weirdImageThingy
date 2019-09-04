package loltest;

public class Angle {
	
	private float angVal;
	
	public Angle(float angle){
		this.angVal = angle;
	}
	
	/** Initializes Angle as a copy of given Angle.
	 * 
	 * @param angle
	 */
	public Angle(Angle angle){
		this.angVal = angle.getAngVal();
	}
	
	public float value(){
		return this.angVal;
	}
	
	public float getAngVal(){
		return this.angVal;
	}
	
	public void setAngle(float angle){
		this.angVal = angle;
	}
	
	/** Returns the current angle in radians.
	 * 
	 * @return
	 */
	public float inRadians(){
		return (float) Math.toRadians(this.angVal);
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
	
	/** Checks if given Angle has the same attribute values as this Angle.
	 * 
	 * @param otherAngle
	 * @return
	 */
	public boolean sameAs(Angle otherAngle){
		if( flEq( this.getAngVal() , otherAngle.getAngVal() ) ){
			return true;
		}
		return false;
	}
	
	private void _________________________________________(){
	}
	
	
	/** Determines if unit X should be -1 (left), 1 (right) 
	 * or 0 (no horizontal component)
	 * 
	 * @param angle
	 * @return
	 */
	public float getUnitXComponent(){
		if(isLeft()){
			return 1;
		}
		if(isRight()){
			return -1;
		}
		return 0;
	}
	
	/** Calculates the appropriate change in Y per unit X traversed
	 * i.e. find vertical component of angle
	 * X may traverse 1 or -1 in actuality but this doesn't matter here.
	 * For straight up/down cases, X traverses zero but Y can be any value.
	 * Follows Java image coordinate system where +y is DOWN.
	 * 
	 * @param angle
	 * @return
	 */
	public float getYPerUnitX(){
		// straight up, 0 deg
		if(isUp() && !isLeft() && !isRight()){
			return -1;
		}
		// straight down, 180 deg
		if(isDown() && !isLeft() && !isRight()){
			return 1;
		}
		// 0 - 90 deg quadrant
		if(isUp() && isRight()){
			return 0 - (float) Math.tan(Math.toRadians(90 - angVal));
		}
		// 90 - 180 deg quadrant
		if(isDown() && isRight()){
			return (float) Math.tan(Math.toRadians(angVal - 90));
		}
		// 180 - 270 deg quadrant
		if(isDown() && isLeft()){
			return (float) Math.tan(Math.toRadians(270 - angVal));
		}
		// 270 - 260 deg quadrant
		if(isUp() && isLeft()){
			return 0 - (float) Math.tan(Math.toRadians(angVal - 270));
		}
		// straight left or straight right
		if(!isUp() && !isDown()){
			return 0;
		}
		return 0;
	}
	
	/** Checks if angle has leftward horizontal component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isLeft(){
		if(angVal > 180 && angVal < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has rightward horizontal component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isRight(){
		if(angVal > 180 && angVal < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has upward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isUp(){
		if(angVal > 0 && angVal < 90){
			return true;
		}
		if(angVal > 270 && angVal < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has downward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isDown(){
		if(angVal > 90 && angVal < 270){
			return true;
		}
		return false;
	}
}
