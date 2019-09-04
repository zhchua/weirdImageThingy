package loltest;

import java.util.ArrayList;
import java.util.HashMap;

// this code assumes disruption template is a white/black image
// where white 255 is the background and black 0 is the disruption target
// and disruption target is a contiguous black shape

// actual code logic to use all shapes as "targets"
// and calculate across all shapes
public class DisruptionTemplate extends ImageObj {
	
	private DisStatList disStatList;
	
	private int angleInterval;
	
	public DisruptionTemplate(int width, int height){
		super(width, height);
	}
	
	public DisruptionTemplate(ImageObj otherImgObj, CopyDepth cd){
		super(otherImgObj, cd);
	}
	
	// copying incomplete FOR DISRUPTION STATS
	public DisruptionTemplate(DisruptionTemplate disTempl, CopyDepth cd){
		super(disTempl, cd);
		this.angleInterval = disTempl.getAngleInterval();
	}
	
	public DisStatList getDisruptionStats(){
		return this.disStatList;
	}
	
	public void setAngleInterval(int angleInterval){
		this.angleInterval = angleInterval;
	}
	
	public int getAngleInterval(){
		return this.angleInterval;
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
		
		if(Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1 - fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	/** Unused function in code to space getters&setters from actual functions
	 * 
	 */
	private void _________________________________________(){}

	/** Returns the angle that should be checked currently.
	 * 
	 * @param count
	 * @return
	 */
	public Angle getAngleAtCount(int count){
		if(angleInterval == 0){
			return new Angle(0);
		}
		return new Angle((float)count * (((float)360)/((float)angleInterval)));
	}
	
	/**  Generates single disruptionStat for given Angle from given Pix of
	 * given Shape.
	 * 
	 * Iterates through all edge Pixs of given Shape, checks for approximate angle.
	 * 
	 * @param shape
	 * @param pix
	 * @param angle
	 */
	public DisStat generateDisruptionStats(
			Angle angle, Pix pix, Shape shape){
		
		ArrayList<Float> distances = new ArrayList<>();
		for(int edgeInx = 0; edgeInx < shape.getEdgeList().size(); edgeInx++){
			if(!shape.getEdgeList().get(edgeInx).sameAs(pix) 
					&& !shape.getEdgeList().get(edgeInx).equals(pix)){
				if(pix.getCoord().isApproxAngleTo(
						shape.getEdgeList().get(edgeInx).getCoord(), angle)){
					distances.add(pix.getCoord().getDistanceTo(
							shape.getEdgeList().get(edgeInx).getCoord()));
				}
			}
		}
		float shortest = distances.get(0);
		for(int dInx = 0; dInx < distances.size(); dInx++){
			if(shortest > distances.get(dInx)){
				shortest = distances.get(dInx);
			}
		}
		return new DisStat(angle, shortest);
	}
	
	/** Populates disruptionStats list by iterating through every Angle of the 
	 * desired interval, at the given Pix.
	 * 
	 * @param shape
	 * @param pix
	 */
	public DisStat generateDisruptionStats(Pix pix, Shape shape){
		for(int intervalCount = 0; intervalCount < angleInterval; intervalCount++){
			return generateDisruptionStats(getAngleAtCount(intervalCount)
					, pix, shape);
		}
	}
	
	/** Populates disruptionStats list by iterating through every edge pix 
	 * in given shape.
	 * 
	 * @param shape
	 */
	public void generateDisruptionStats(Shape shape){
		for(int edgeInx = 0; edgeInx < shape.getEdgeList().size(); edgeInx++){
			generateDisruptionStats(shape.getPixList().get(edgeInx), shape);
		}
	}
	
	/**	Populates disruptionStats list by iterating through every Shape
	 *	in ShapeList.
	 * 
	 */
	public void generateDisruptionStats(){
		if(this.disruptionStats == null){
			this.disruptionStats = new ArrayList<DisruptionStat>();
		}
		for(int shapeInx = 0; shapeInx < this.getShapeList().size(); shapeInx++){
			generateDisruptionStats(this.getShapeList().get(shapeInx));
		}
	}
}
