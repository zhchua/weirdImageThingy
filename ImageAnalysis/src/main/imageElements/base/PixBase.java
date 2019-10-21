package main.imageElements.base;

import main.elementInfo.Colour;
import main.elementInfo.Coord;

public class PixBase {

	public Colour colour;
	public Coord coord;
	
	
	public PixBase(Colour colour, Coord coord){
		this.colour = colour;
		this.coord = coord;
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
	
	/** Checks if given pix has the same shape and coord as this pix.
	 * 
	 * @param otherPix
	 * @return
	 */
	public boolean sameAs(PixBase otherPix){
		return (this.colour.sameAs(otherPix.getColour()) 
				&& this.getCoord().sameAs(otherPix.getCoord()));
	}
	
}