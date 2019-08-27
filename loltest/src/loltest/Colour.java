package loltest;

public class Colour {
	
	private float r;
	private float g;
	private float b;
	private float a;
	
	public Colour(float a, float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void setR(float r){
		this.r = r;
	}
	
	public void setG(float g){
		this.g = g;
	}
	
	public void setB(float b){
		this.b = b;
	}
	
	public void setA(float a){
		this.a = a;
	}
	
	public void setRGB(float r, float g, float b){
		setR(r);
		setG(g);
		setB(b);
	}
	
	public void setARGB(float a, float r, float g, float b){
		setR(r);
		setG(g);
		setB(b);
		setA(a);
	}
	
	public float getR(){
		return this.r;
	}
	
	public float getG(){
		return this.g;
	}
	public float getB(){
		return this.b;
	}
	public float getA(){
		return this.a;
	}
}
