package loltest;

public class Coord {
	
	private int x;
	private int y;
	
	public Coord(int x, int y){
		setCoord(x, y);
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
}
