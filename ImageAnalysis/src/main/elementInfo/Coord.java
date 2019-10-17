package main.elementInfo;

import main.elementInfo.base.CoordBase;
import main.image.ImageObj;

public class Coord extends CoordBase {

	public Coord(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Coord(CoordBase coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	
	/** Returns true if this coord is to the left of the given coord.
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isLeftOf(Coord otherCoord){
		if(this.getX() < otherCoord.getX()){
			return true;
		}
		return false;
	}
	
	/** Returns true if this coord is to the right of given coord. 
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isRightOf(Coord otherCoord){
		if(this.getX() > otherCoord.getX()){
			return true;
		}
		return false;
	}
	
	/** Returns true if this coord is above given coord. 
	 * Follows Java image coordinate convention, i.e. top left corner is (0,0)
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isAbove(Coord otherCoord){
		if(this.getY() < otherCoord.getY()){
			return true;
		}
		return false;
	}
	
	/** Returns true if this coord is below the given coord. 
	 * Follows Java image coordinate convention, i.e. top left corner is (0,0)
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isBelow(Coord otherCoord){
		if(this.getY() > otherCoord.getY()){
			return true;
		}
		return false;
	}
	
	public boolean isVerticalOf(Coord otherCoord){
		if(!this.isLeftOf(otherCoord) && !this.isRightOf(otherCoord)){
			return true;
		}
		return false;
	}
	
	public boolean isHorizontalOf(Coord otherCoord){
		if(!this.isAbove(otherCoord) && !this.isBelow(otherCoord)){
			return true;
		}
		return false;
	}
	
	public boolean isDirectlyLeftOf(Coord otherCoord){
		if(this.isHorizontalOf(otherCoord) && this.isLeftOf(otherCoord)){
			return true;
		}
		return false;
	}
	
	public boolean isDirectlyRightOf(Coord otherCoord){
		if(this.isHorizontalOf(otherCoord) && this.isRightOf(otherCoord)){
			return true;
		}
		return false;
	}
	
	public boolean isDirectlyAbove(Coord otherCoord){
		if(this.isVerticalOf(otherCoord) && this.isAbove(otherCoord)){
			return true;
		}
		return false;
	}
	
	public boolean isDirectlyBelow(Coord otherCoord){
		if(this.isVerticalOf(otherCoord) && this.isBelow(otherCoord)){
			return true;
		}
		return false;
	}
	
	/** Finds the vertical displacement of moving from this coord to given coord.
	 * ^ given.y - this.y
	 * 
	 * @param otherCoord
	 * @return
	 */
	public int getYDispTo(Coord otherCoord){
		return otherCoord.getY() - this.getY();
	}
	
	/** Finds the horizontal displacement of moving from this coord to given coord.
	 * ^ given.x - this.x
	 * 
	 * @param otherCoord
	 * @return
	 */
	public int getXDispTo(Coord otherCoord){
		return otherCoord.getX() - this.getX();
	}
	
	public int getYDispFrom(Coord otherCoord){
		return this.getY() - otherCoord.getY();
	}
	
	public int getXDispFrom(Coord otherCoord){
		return this.getX() - otherCoord.getX();
	}
	
	public int getYDistBetween(Coord otherCoord){
		return Math.abs(this.getYDispTo(otherCoord));
	}
	
	public int getXDistBetween(Coord otherCoord){
		return Math.abs(this.getXDispTo(otherCoord));
	}
	
	/** Calculates diagonal distance between this coord and given coord
	 * Pythagoras theorem: a^2 + b^2 = c^2
	 * 
	 * @param coord1
	 * @param coord2
	 * @return
	 */
	public double getDistanceTo(Coord otherCoord){
		
		double dx =  (otherCoord.getX() - this.getX());
		double dy =  (otherCoord.getY() - this.getY());
		
		return  Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
	}
	
	/** Finds the angle from this coord to given coord.
	 * Follows Java coordinate system.
	 * Returns null if fails logic.
	 * 
	 * @param otherCoord
	 * @return
	 */
	public Angle getAngleTo(Coord otherCoord){
		// pure verticals
		if(this.isDirectlyBelow(otherCoord)){
			return new Angle(180);
		}
		if(this.isDirectlyAbove(otherCoord)){
			return new Angle(0);
		}
		// pure horizontals
		if(this.isDirectlyLeftOf(otherCoord)){
			return new Angle(90);
		}
		if(this.isDirectlyRightOf(otherCoord)){
			return new Angle(270);
		}
		double absDX = this.getXDistBetween(otherCoord);
		double absDY = this.getYDistBetween(otherCoord);
		double angDeg =  Math.toDegrees(Math.atan(absDY/absDX));
		// quadrant 0-90
		if(this.isBelow(otherCoord) && this.isLeftOf(otherCoord)){
			return new Angle( 90 - angDeg);
		}
		// quadrant 90 - 180
		if(this.isAbove(otherCoord) && this.isLeftOf(otherCoord)){
			return new Angle( 90 + angDeg);
		}
		// quadrant 180 - 270
		if(this.isAbove(otherCoord) && this.isRightOf(otherCoord)){
			return new Angle( 270 - angDeg);
		}
		// quadrant 270 - 360
		if(this.isBelow(otherCoord) && this.isRightOf(otherCoord)){
			return new Angle( 270 + angDeg);
		}
		return null;
	}
	
	/** Checks if given coord is the given angle from this coord.
	 * Leeway of 3x3 coords given.
	 * Low precision at shorter distances.
	 * 
	 * @param otherCoord
	 * @param angle
	 * @return
	 */
	public boolean isApproxAngleTo(Coord otherCoord, Angle angle){

		double minAng = angle.getValue();
		double maxAng = angle.getValue();
		Coord chkCoord = new Coord(0,0);
		
		for(int chkX = -1; chkX < 2; chkX++){
			for(int chkY = -1; chkY < 2; chkY++){
				chkCoord.setCoord(otherCoord.getX()+chkX, otherCoord.getY()+chkY);
				if(this.getAngleTo(chkCoord).getValue() < minAng){
					minAng = this.getAngleTo(chkCoord).getValue();
				}
				if(this.getAngleTo(chkCoord).getValue() > maxAng){
					maxAng = this.getAngleTo(chkCoord).getValue();
				}
			}
		}
		
		if(minAng < angle.getValue() && maxAng > angle.getValue()){
			return true;
		}
		return false;
	}

	/** Checks if given coord is adjacent to (within 3x3 grid) of this coord.
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isAdjacentTo(Coord otherCoord){
		
		Coord chkCoord = new Coord(0,0);
		
		for(int chkX = -1; chkX < 2; chkX++){
			for(int chkY = -1; chkY < 2; chkY++){
				chkCoord.setCoord(this.getX()+chkX, this.getY()+chkY);
				if(chkCoord != this && otherCoord.sameAs(chkCoord)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isImageEdge(ImageObj imageObj){
		if(this.getX() == 0 || this.getX() >= imageObj.getWidth() - 1){
			return true;
		}
		if(this.getY() == 0 || this.getY() >= imageObj.getHeight() - 1){
			return true;
		}
		return false;
	}
}
