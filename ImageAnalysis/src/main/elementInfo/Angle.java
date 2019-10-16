package main.elementInfo;

import main.elementInfo.base.AngleBase;

public class Angle extends AngleBase{
	
	public Angle(Angle angle) {
		super(angle);
	}
	
	public Angle(float angle){
		super(angle);
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
			return 0 - (float) Math.tan(Math.toRadians(90 - getValue()));
		}
		// 90 - 180 deg quadrant
		if(isDown() && isRight()){
			return (float) Math.tan(Math.toRadians(getValue() - 90));
		}
		// 180 - 270 deg quadrant
		if(isDown() && isLeft()){
			return (float) Math.tan(Math.toRadians(270 - getValue()));
		}
		// 270 - 260 deg quadrant
		if(isUp() && isLeft()){
			return 0 - (float) Math.tan(Math.toRadians(getValue() - 270));
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
		if(getValue() > 180 && getValue() < 360){
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
		if(getValue() > 180 && getValue() < 360){
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
		if(getValue() > 0 && getValue() < 90){
			return true;
		}
		if(getValue() > 270 && getValue() < 360){
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
		if(getValue() > 90 && getValue() < 270){
			return true;
		}
		return false;
	}
	

	
	/** Returns the given angle to within 0 - 360.
	 * If given angle is already within 0 - 360 degrees, returns given angle as is.
	 * 
	 * @param angle
	 * @return
	 */
	public float rerangeAngle(float angle){
		if(angle>=360){
			return angle - 360;
		}
		else if(angle<0){
			return 360 - angle;
		}
		else return angle;
	}
}
