package loltest;

import java.util.ArrayList;
import java.util.HashMap;

// this code assumes disruption template is a white/black image
// where white 255 is the background and black 0 is the disruption target
// and disruption target is a contiguous black shape

// actual code logic to use all shapes as "targets"
// and calculate across all shapes
public class DisruptionTemplate extends ImageObj {
	
	private ArrayList<DisruptionStat> disruptionStats;
	
	private int angleInterval;
	
	public DisruptionTemplate(int width, int height){
		super(width, height);
	}
	
	public ArrayList<DisruptionStat> getDisruptionStats(){
		return this.disruptionStats;
	}
	
	public void setAngleInterval(int angleInterval){
		this.angleInterval = angleInterval;
	}
	
	public int getAngleInterval(){
		return this.angleInterval;
	}
	
	/*
	// randomly searches for an edge pixel
	// edge pixel being adjacent to WHITE
	public Coord getStartPoint(){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(getPixArray().get(pixInx).getColour() == black){
				if(!this.pixIsAdjacentToSameColour(getPixArray().get(pixInx))){
					return getPixArray().get(pixInx).getCoord();
				}
			}
		}
		return null;
	}
	*/
	
	/** Determines equality between two floats to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	public boolean flEq(float fl1, float fl2){
		float errMar1 = (float) ((0.001) * (fl1/100));
		float errMar2 = (float) ((0.001) * (fl2/100));
		
		if(Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	/** Determines equality between two floats to a given precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	public boolean flEq(float fl1, float fl2, float pres){
		float errMar1 = (float) ((pres) * (fl1/100));
		float errMar2 = (float) ((pres) * (fl2/100));
		
		if(Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	/** Unused function in code to space getters&setters from actual functions
	 * 
	 */
	private void SPACER(){}

	/** Returns the angle that should be checked currently.
	 * 
	 * @param count
	 * @return
	 */
	public float getAngleAtCount(int count){
		if(angleInterval == 0){
			return 0;
		}
		return (float)count * (((float)360)/((float)angleInterval));
	}
	
	/** Calculates the appropriate change in Y per unit X traversed
	 * i.e. find vertical component of angle
	 * X may traverse 1 or -1 in actuality but this doesn't matter here.
	 * For straight up/down cases, X traverses zero but Y can be any value.
	 * 
	 * @param angle
	 * @return
	 */
	public float getYXRatio(float angle){
		// straight up, 0 deg
		if(angleIsUp(angle) && !angleIsLeft(angle) && !angleIsRight(angle)){
			return 1;
		}
		// straight down, 180 deg
		if(angleIsDown(angle) && !angleIsLeft(angle) && !angleIsRight(angle)){
			return -1;
		}
		// 0 - 90 deg quadrant
		if(angleIsUp(angle) && angleIsRight(angle)){
			return (float) Math.tan(Math.toRadians(90 - angle));
		}
		// 90 - 180 deg quadrant
		if(angleIsDown(angle) && angleIsRight(angle)){
			return 0 - (float) Math.tan(Math.toRadians(angle - 90));
		}
		// 180 - 270 deg quadrant
		if(angleIsDown(angle) && angleIsLeft(angle)){
			return 0 - (float) Math.tan(Math.toRadians(270 - angle));
		}
		// 270 - 260 deg quadrant
		if(angleIsUp(angle) && angleIsLeft(angle)){
			return (float) Math.tan(Math.toRadians(angle - 270));
		}
		// straight left or straight right
		if(!angleIsUp(angle) && !angleIsDown(angle)){
			return 0;
		}
		return 0;
	}
	
	/** Checks if angle has leftward horizontal component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean angleIsLeft(float angle){
		if(angle > 180 && angle < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has rightward horizontal component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean angleIsRight(float angle){
		if(angle > 180 && angle < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has upward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean angleIsUp(float angle){
		if(angle > 0 && angle < 90){
			return true;
		}
		if(angle > 270 && angle < 360){
			return true;
		}
		return false;
	}
	
	/** Checks if angle has downward vertical component.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean angleIsDown(float angle){
		if(angle > 90 && angle < 270){
			return true;
		}
		return false;
	}
	
	/** Finds distance to pix on other side of given shape, from the given pix,
	 * at the given angle.
	 * 
	 * Straight-up is considered 0deg, goes clockwise.
	 * 
	 * If no pixs are found with the angle, return 0
	 * 
	 * @param shape
	 * @param pix
	 * @param angle
	 * @return
	 */
	// neon for extranet
	// latest for eserv
	public float getDistanceToOtherEdge(Shape shape, Pix pix, float angle){
		for(int pixInx = 0; pixInx < shape.getPixArray().size(); pixInx++){
			if( shape.getPixArray().get(pixInx) != pix 
					&& shape.pixIsShapeEdge(shape.getPixArray().get(pixInx))){
				
			}
		}
		return 1;
	}
	
	/**  Generates single disruptionStat by drawing line from origin (pix)
	 *  in the direction specified by angle.
	 * 
	 * @param shape
	 * @param pix
	 * @param angle
	 */
	public void generateDisruptionStatsForAngle(Shape shape, Pix pix, float angle){
		
	}
	
	/** Populates disruptionStats list by iterating through every angle of the 
	 * desired interval, at the given pix.
	 * 
	 * @param shape
	 * @param pix
	 */
	public void generateDisruptionStatsForPix(Shape shape, Pix pix){
		for(int intervalCount = 0; intervalCount < angleInterval; intervalCount++){
			generateDisruptionStatsForAngle(shape, pix, getAngleAtCount(intervalCount));
		}
	}
	
	/** Populates disruptionStats list by iterating through every edge pix 
	 * in given shape.
	 * 
	 * @param shape
	 */
	public void generateDisruptionStatsForShape(Shape shape){
		for(int pixInx = 0; pixInx < shape.getPixArray().size(); pixInx++){
			if(shape.pixIsShapeEdge(shape.getPixArray().get(pixInx))){
				generateDisruptionStatsForPix(shape, shape.getPixArray().get(pixInx));
			}
		}
	}
	
	/**	Populates disruptionStats list by iterating through every shape.
	 * 
	 */
	public void generateDisruptionStatsForAllShapes(){
		if(this.disruptionStats == null){
			this.disruptionStats = new ArrayList<DisruptionStat>();
		}
		for(int shapeInx = 0; shapeInx < this.getShapeList().size(); shapeInx++){
			generateDisruptionStatsForShape(this.getShapeList().get(shapeInx));
		}
	}
}
