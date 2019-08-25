package loltest;

public class ColourInt {

	private int a;
	private int r;
	private int g;
	private int b;

	public ColourInt(Colour colour){
		this.a = (int) colour.getA();
		this.b = (int) colour.getB();
	}
	
	public int getA(){
		return this.a;
	}
	
	public int getR(){
		return this.r;
	}
	
	public int getG(){
		return this.g;
	}
	
	public int getB(){
		return this.b;
	}
}
