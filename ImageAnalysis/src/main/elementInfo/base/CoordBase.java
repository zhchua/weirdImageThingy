package main.elementInfo.base;

import main.utility.BaseObject;

public class CoordBase extends BaseObject{
	
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
		super(coord);
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
	
	/** Compares given coord's X and Y with this coord to check if same.
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean sameAs(CoordBase otherCoord){
		return (this.getX() == otherCoord.getX() && this.getY() == otherCoord.getY());
	}
}
