package main.imageElements.base;

import main.elementInfo.Colour;
import main.elementInfo.Coord;
import main.utility.BaseObject;

public class PixBase extends BaseObject{

	public Colour colour;
	public Coord coord;
	
	
	public PixBase(Colour colour, Coord coord){
		this.colour = colour;
		this.coord = coord;
		this.denyNullArgs();
	}
	
	public PixBase(PixBase pix){
		this.colour = new Colour(pix.getColour());
		this.coord = new Coord(pix.getCoord());
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