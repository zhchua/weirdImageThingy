package main.imageElements;

import main.collections.ShapeList;
import main.elementInfo.Colour;
import main.elementInfo.Coord;
import main.image.ImageObj;
import main.imageElements.base.PixBase;

public class Pix extends PixBase {

	public Pix(Colour colour, Coord coord) {
		super(colour, coord);
	}

	public Pix(Pix pix){
		super(pix);
	}
	
	/** Checks if given pix is the same colour as this pix.
	 * Calls Colour.isSameRGB
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isSameColourAs(PixBase otherPix){
		return this.getColour().isSameRGB(otherPix.getColour());
	}
	
	/** Checks if given pix is the same alpha channel value as this pix.
	 * Calls Colour.isSameAlpha
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean isSameAlphaAs(PixBase otherPix){
		return this.getColour().isSameAlpha(otherPix.getColour());
	}
	
	/** Checks if this pix and given pix have the same ARGB.
	 * Calls Colour.isSameARGB
	 * 
	 * @param otherPix
	 * @return
	 */
	public boolean isSameARGB(PixBase otherPix){
		return this.getColour().equals(otherPix.getColour());
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
		return (!this.equals(otherPix) && this.isSameColourAs(otherPix) 
				&& this.isAdjacentTo(otherPix));
	}
	
	/** Checks if this Pix is located at given coord. 
	 * 
	 * @param otherCoord
	 * @return
	 */
	public boolean isAtCoord(Coord otherCoord){
		return this.getCoord().equals(otherCoord);
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
						&& !shape.getPix(chkCoord).equals(this)
						&& shape.getPix(chkCoord).isAdjacentTo(this)
						&& shape.getPix(chkCoord).isSameColourAs(this)){
					return true;
				}
			}
		}
		return false;
	}
}
