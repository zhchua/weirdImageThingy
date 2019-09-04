package loltest;

public class Coord {
	
	private int x;
	private int y;
	
	public Coord(int x, int y){
		setCoord(x, y);
	}
	
	/** Creates a new Coord that is a copy of given Coord.
	 * 
	 * @param coord
	 */
	public Coord(Coord coord){
		this.x = coord.getX();
		this.y = coord.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setCoord(int x, int y){
		setX(x);
		setY(y);
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
		
		if(Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	/** Compares given coord's X and Y with this coord to check if same.
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean sameAs(Coord otherCoord){
		if(this.getX() == otherCoord.getX() && this.getY() == otherCoord.getY()){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private void _________________________________________(){}
	
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
	
	public int getYDispTo(Coord otherCoord){
		return otherCoord.getY() - this.getY();
	}
	
	public int getXDispTo(Coord otherCoord){
		return otherCoord.getX() - this.getX();
	}
	
	/** Calculates diagonal distance between this coord and given coord
	 * Pythagoras theorem: a^2 + b^2 = c^2
	 * 
	 * @param coord1
	 * @param coord2
	 * @return
	 */
	public float getDistanceTo(Coord otherCoord){
		
		float dx = (float) (otherCoord.getX() - this.getX());
		float dy = (float) (otherCoord.getY() - this.getY());
		
		return (float) Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
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
		if(!this.isLeftOf(otherCoord) && !this.isRightOf(otherCoord)){
			if(this.isAbove(otherCoord)){
				return new Angle(180);
			}
			if(this.isBelow(otherCoord)){
				return new Angle(0);
			}
		}
		// pure horizontals
		if(!this.isAbove(otherCoord) && !this.isBelow(otherCoord)){
			if(this.isLeftOf(otherCoord)){
				return new Angle(90);
			}
			if(this.isRightOf(otherCoord)){
				return new Angle(270);
			}
		}
		float absDX = Math.abs(this.getXDispTo(otherCoord));
		float absDY = Math.abs(this.getYDispTo(otherCoord));
		float angDeg = (float) Math.toDegrees(Math.atan(absDY/absDX));
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

		float minAng = angle.value();
		float maxAng = angle.value();
		Coord chkCoord = new Coord(0,0);
		
		for(int chkX = -1; chkX < 2; chkX++){
			for(int chkY = -1; chkY < 2; chkY++){
				chkCoord.setCoord(otherCoord.getX()+chkX, otherCoord.getY()+chkY);
				if(this.getAngleTo(chkCoord).value() < minAng){
					minAng = this.getAngleTo(chkCoord).value();
				}
				if(this.getAngleTo(chkCoord).value() > maxAng){
					maxAng = this.getAngleTo(chkCoord).value();
				}
			}
		}
		
		if(minAng < angle.value() && maxAng > angle.value()){
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
