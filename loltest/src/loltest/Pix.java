package loltest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Pix {

	private Colour colour;
	private Coord coord;
	
	public Pix(Colour colour, Coord coord){
		this.colour = colour;
		this.coord = coord;
	}
	
	public Colour getColour(){
		return this.colour;
	}
	
	public void setColour(Colour colour){
		this.colour = colour;
	}
	
	public Coord getCoord(){
		return this.coord;
	}
	
	public void setCoord(Coord coord){
		this.coord = coord;
	}
	
	
}
