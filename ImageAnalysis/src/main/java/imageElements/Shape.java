package main.imageElements;

import main.collections.PixList;
import main.elementInfo.Colour;
import main.elementInfo.Coord;
import main.image.ImageObj;
import main.imageElements.base.ShapeBase;

public class Shape extends ShapeBase {
	
	//private PixList verticeList;
	private PixList edgeList;

	public Shape() {
	}
	
	public Shape(Shape shape){
		super(shape);
	}
	
	/** Sets alpha channel of all pixs in shape to the specified value.
	 * 
	 * @param a
	 * @return
	 */
	public boolean setAllAlpha(float a){
		if(this.getPixList() == null){
			return false;
		}
		for(int i = 0; i < this.getPixList().size(); i++){
			this.getPixList().get(i).getColour().setA(a);
		}
		return true;
	}

	
	/** Checks if Shape's pixList contains pix.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean containsPix(Pix pix){
		return this.getPixList().contains(pix);
	}
	
	/** Inserts given pix into this shape. 
	 * 
	 * @param pix
	 */
	public void insertPix(Pix pix){
		if(this.getPixList() == null){
			this.setPixList(new PixList());
		}
		this.getPixList().add(pix);
	}
	
	/** Removes given pix from this shape.
	 * 
	 * @param pix
	 * @return
	 */
	public void deletePixFromShape(Pix pix){
		this.getPixList().remove(pix);
	}
	
	/** Sets all pix in the shape to the given colour.
	 * 
	 * @param colour
	 * @return
	 */
	public boolean setAllColour(Colour colour){
		if(this.getPixList() == null){
			return false;
		}
		for(int i = 0; i < this.getPixList().size(); i++){
			this.getPixList().get(i).setColour(colour);
		}
		return true;
	}
	
	/** Returns a pix with the given coord.
	 * If no pix with matching coord exists, returns null.
	 * 
	 * @param coord
	 * @return
	 */
	public Pix getPix(Coord coord){
		for(int pixInx = 0; pixInx < this.getPixList().size(); pixInx++){
			if(this.getPixList().get(pixInx).getCoord() == coord){
				return this.getPixList().get(pixInx);
			}
		}
		return null;
	}
	
	/** Gets the Pix in the pixList of this Shape by index.
	 * This shape should already have a pixList initialized, 
	 * and inx should be within pixList.size().
	 * 
	 * @param inx
	 * @return
	 */
	public Pix getPix(int inx){
		return this.getPixList().get(inx);
	}
	
	/** Checks if this Shape is at the image's edge.
	 * Does so by calling Pix.isImageEdge on every component Pix.
	 * 
	 * @param pix
	 * @param imageObj
	 * @return
	 */
	public boolean isImageEdge(ImageObj imageObj){
		for(int inx = 0; inx < this.getPixList().size(); inx++){
			if(this.getPixList().get(inx).isImageEdge(imageObj)){
				return true;
			}
		}
		return false;
	}
	
	/** Generates and returns arrayList of Pixs that are edges of this Shape.
	 * 
	 * @return
	 */
	private PixList generateEdgeArray(){
		if(this.edgeList == null){
			this.edgeList = new PixList();
		}
		for(int inx = 0; inx < this.getPixList().size(); inx++){
			if(this.getPixList().get(inx).isShapeEdge(this)){
				this.edgeList.add(this.getPixList().get(inx));
			}
		}
		return this.edgeList;
	}
	
	/** Gets arrayList of Pixs that are edges of this Shape.
	 * If edgeList does not yet exist, will attempt to generate.
	 * 
	 * @return
	 */
	public PixList getEdgeList(){
		if(this.edgeList == null){
			generateEdgeArray();
		}
		return this.edgeList;
	}
	
	/** Clears edgeList by making it null.
	 * 
	 */
	public void deleteEdgeList(){
		this.edgeList = null;
	}
}
