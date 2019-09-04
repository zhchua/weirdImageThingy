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
	
	public Pix(Pix pix, CopyDepth cd){
		if(cd == CopyDepth.STRICT){
			this.colour = new Colour(pix.getColour());
			this.coord = new Coord(pix.getCoord());
		}
		else{
			this.colour = pix.colour;
			this.coord = pix.coord;
		}
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
	public boolean sameAs(Pix otherPix){
		if(this.isSameARGB(otherPix) && this.getCoord().sameAs(otherPix.getCoord())){
			return true;
		}
		return false;
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
	
	/** Checks if given pix is the same alpha channel value as this pix.
	 * Calls Colour.isSameAlpha
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isSameAlphaAs(Pix otherPix){
		return this.getColour().isSameAlpha(otherPix.getColour());
	}
	
	/** Checks if this pix and given pix have the same ARGB.
	 * Calls Colour.isSameARGB
	 * 
	 * @param otherPix
	 * @return
	 */
	public boolean isSameARGB(Pix otherPix){
		return this.getColour().sameAs(otherPix.getColour());
	}
	
	/** Checks if this pix and given pix are next to each other.
	 * Calls Coord.isAdjacentTo
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isAdjacentTo(Pix otherPix){
		return this.getCoord().isAdjacentTo(otherPix.getCoord());
	}
	
	/** Checks if this pixel is at given image edge
	 * 
	 * @param pix
	 * @return
	 */
	public boolean isImageEdge(ImageObj imageObj){
		return this.getCoord().isImageEdge(imageObj);
	}

	/** Checks if this pix and given pix should belong to the same shape.
	 * Calls same-colour checking and adjacency checking.
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean shouldbeSameShapeAs(Pix otherPix){
		if(!this.sameAs(otherPix) && this.isSameColourAs(otherPix) 
				&& this.isAdjacentTo(otherPix)){
			return true;	
		}
		return false;
	}
	
	/** Checks if this Pix is located at given coord. 
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isAtCoord(Coord otherCoord){
		return this.getCoord().sameAs(otherCoord);
	}
	
	/** Checks if this pix is in given shape.
	 * 
	 * @param shape
	 * @return
	 */
	public boolean isInShape(Shape shape){
		return shape.containsPix(this);
	}
	
	/** Gets the Shape of the given ShapeList that this pix belongs to.
	 * 
	 * @param shapeList
	 * @return
	 */
	public Shape getShape(ShapeList shapeList){
		for(int inx = 0; inx < shapeList.size(); inx++){
			if(shapeList.get(inx).containsPix(this)){
				return shapeList.get(inx);
			}
		}
		return null;
	}
	
	/** Checks if this Pix is in any Shapes of the given ShapeList.
	 * 
	 * @param shapeList
	 * @return
	 */
	public boolean isInShapes(ShapeList shapeList){
		if(shapeList == null || shapeList.isEmpty()){
			return false;
		}
		return(shapeList.containsPix(this));
	}
	
	/** Checks if this Pix is at the edge of given Shape.
	 * 
	 * 
	 * @param shape
	 * @return
	 */
	public boolean isShapeEdge(Shape shape){
		Coord chkCoord;
		
		for(int chkX = -1; chkX < 2; chkX++){
			for(int chkY = -1; chkY < 2; chkY++){
				chkCoord = new Coord(
						this.getCoord().getX() + chkX, 
						this.getCoord().getY() + chkY);
				if(shape.getPix(chkCoord) == null){
					return true;
				}
			}
		}
		return false;
	}
	
	/** Checks if this Pix is adjacent to another Pix of the same Colour in the
	 * given Shape.
	 * 
	 * @param shape
	 * @return
	 */
	public boolean isAdjacentToSameColourPix(Shape shape){
		Coord chkCoord;
		
		for(int chkX = -1; chkX < 2; chkX++){
			for(int chkY = -1; chkY < 2; chkY++){
				chkCoord = new Coord(
						this.getCoord().getX() + chkX, 
						this.getCoord().getY() + chkY);
				if(shape.getPix(chkCoord) != null
						&& !shape.getPix(chkCoord).sameAs(this)
						&& shape.getPix(chkCoord).isAdjacentTo(this)
						&& shape.getPix(chkCoord).isSameColourAs(this)){
					return true;
				}
			}
		}
		return false;
	}
}
