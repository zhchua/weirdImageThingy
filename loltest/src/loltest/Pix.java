package loltest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Pix {

	private float r;
	private float g;
	private float b;
	private float a;
	private int x;
	private int y;
	
	public HashMap<String, Float> getColour(){
		HashMap<String, Float> colour = new HashMap<String, Float>();
		colour.put("r", this.r);
		colour.put("g", this.g);
		colour.put("b", this.b);
		colour.put("a", this.a);
		
		return colour;
	}
	
	public void setColour(HashMap<String,Float> colour){
		this.r = colour.get("r");
		this.g = colour.get("g");
		this.b = colour.get("b");
		this.a = colour.get("a");
	}
	
	public HashMap<String, Integer> getCoords(){
		HashMap<String, Integer> coords = new HashMap<String, Integer>();
		coords.put("x", x);
		coords.put("y", y);
		return coords;
	}
	
	public void setAlpha(float a){
		this.a = a;
	}
	
	
}
