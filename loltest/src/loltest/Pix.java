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
	
	private void _________________________________________(){}
	
	/** Checks if given pix is the same colour as this pix.
	 * Calls Colour.isSameRGB
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isSameColourAs(Pix otherPix){
		return this.getColour().isSameRGB(otherPix.getColour());
	}
	
	/** Checks if two given pixels are next to each other.
	 * Calls Coord.isAdjacentTo
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isAdjacentTo(Pix otherPix){
		return this.getCoord().isAdjacentTo(otherPix.getCoord());
	}
	
	
}
