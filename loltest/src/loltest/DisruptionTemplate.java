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
	
	/** 
	 * 
	 * @param shape
	 * @param pix
	 * @param angle
	 * @return
	 */
	public int getDistanceToOtherEdge(Shape shape, Pix pix, float angle){
		
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
