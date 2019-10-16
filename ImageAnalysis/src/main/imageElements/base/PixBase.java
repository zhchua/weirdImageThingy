package ImageObjects.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import InfoObjects.Colour;
import InfoObjects.Coord;

public class PixBase {

	private Colour colour;
	private Coord coord;
	
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
		if(this.colour.sameAs(otherPix.getColour()) && this.getCoord().sameAs(otherPix.getCoord())){
			return true;
		}
		return false;
	}
}
