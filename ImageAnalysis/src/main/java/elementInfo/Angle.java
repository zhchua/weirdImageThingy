package main.elementInfo;

import main.elementInfo.base.AngleBase;

public class Angle extends AngleBase{
	
	public Angle(Angle angle) {
		super(angle);
		rerangeAngle();
	}
	
	public Angle(double angle){
		super(angle);
		rerangeAngle();
	}
	
	@Override
	public void setValue(double dbl){
		super.setValue(rerangeAngle(dbl));
	}

	/** Determines if unit X should be -1 (left), 1 (right) 
	 * or 0 (no horizontal component)
	 * 
	 * @param angle
	 * @return
	 */
	public double getUnitXComponent(){
		if(isLeft()){
			return -1;
		}
		if(isRight()){
			return 1;
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
	public double getYPerUnitX(){
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
			return 0 -  Math.tan(Math.toRadians(90 - getValue()));
		}
		// 90 - 180 deg quadrant
		if(isDown() && isRight()){
			return  Math.tan(Math.toRadians(getValue() - 90));
		}
		// 180 - 270 deg quadrant
		if(isDown() && isLeft()){
			return  Math.tan(Math.toRadians(270 - getValue()));
		}
		// 270 - 260 deg quadrant
		if(isUp() && isLeft()){
			return 0 -  Math.tan(Math.toRadians(getValue() - 270));
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
		return (getValue() > 180 && getValue() < 360);
	}
	
	/** Checks if angle has rightward horizontal component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isRight(){
		return (getValue() > 0 && getValue() < 180);
	}
	
	/** Checks if angle has upward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isUp(){
		return ((getValue() > 270 && getValue() < 360) 
				|| (getValue() >= 0 && getValue() < 90));
	}
	
	/** Checks if angle has downward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean isDown(){
		return (getValue() > 90 && getValue() < 270);
	}
	

	
	/** Returns the given angle to within 0 - 360.
	 * If given angle is already within 0 - 360 degrees, returns given angle as is.
	 * 
	 * @param angle
	 * @return
	 */
	public double rerangeAngle(double angle){
		if(angle>=360){
			return angle%360;
		}
		else if(angle<0){
			return 360 + (angle%360);
		}
		else return angle;
	}
	
	/** Sets this angle to within 0 - 360 degrees.
	 * 
	 */
	private void rerangeAngle(){
		this.setValue(rerangeAngle(this.getValue()));
	}
}