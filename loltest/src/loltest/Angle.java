package loltest;

public class Angle {
	
	private float angVal;
	
	private int count;
	
	public Angle(float angle){
		this.angVal = angle;
	}
	
	public float value(){
		return this.angVal;
	}
	
	public float getAngle(){
		return this.angVal;
	}
	
	public void setAngle(float angle){
		this.angVal = angle;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public int getCount(){
		return this.count;
	}
	
	/** Returns the current angle in radians.
	 * 
	 * @return
	 */
	public float inRadians(){
		return (float) Math.toRadians(this.angVal);
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
