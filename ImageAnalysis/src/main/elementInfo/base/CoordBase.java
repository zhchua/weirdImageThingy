package InfoObjects.Base;

public class CoordBase {
	
	private int x;
	private int y;
	
	public CoordBase(int x, int y){
		setCoord(x, y);
	}
	
	/** Creates a strict copy of given Coord.
	 * 
	 * @param coord
	 */
	public CoordBase(CoordBase coord){
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
	public boolean sameAs(CoordBase otherCoord){
		if(this.getX() == otherCoord.getX() && this.getY() == otherCoord.getY()){
			return true;
		}
		return false;
	}
}
