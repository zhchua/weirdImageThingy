package loltest;

import java.util.ArrayList;
import java.util.List;

public class Shape {

	private PixList pixList;
	private PixList verticeList;
	private PixList edgeList;
	
	public Shape(){
		this.pixList = new PixList();
		this.verticeList = new PixList();
		this.edgeList = new PixList();
	}
	
	/** Sets alpha channel of all pixs in shape to the specified value.
	 * 
	 * @param a
	 * @return
	 */
	public boolean setAllAlpha(float a){
		if(pixList == null){
			return false;
		}
		for(int i = 0; i < pixList.size(); i++){
			pixList.get(i).getColour().setA(a);
		}
		return true;
	}
	
	public boolean sameAs(Shape otherShape){
		if(this.getPixList().size() == otherShape.getPixList().size()){
			for(int inx = 0; inx < this.getPixList().size(); inx++){
				if(!otherShape.containsPix(this.getPixList().get(inx))){
					return false;
				}
			}
		}
		return false;
	}
	
	public PixList getPixList(){
		return this.pixList;
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
		if(this.pixList == null){
			this.pixList = new PixList();
		}
		this.pixList.add(pix);
	}
	
	/** Removes given pix from this shape.
	 * 
	 * @param pix
	 * @return
	 */
	public void deletePixFromShape(Pix pix){
		pixList.remove(pix);
	}
	
	/** Sets all pix in the shape to the given colour.
	 * 
	 * @param colour
	 * @return
	 */
	public boolean setAllColour(Colour colour){
		if(pixList == null){
			return false;
		}
		for(int i = 0; i < pixList.size(); i++){
			pixList.get(i).setColour(colour);
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
		for(int pixInx = 0; pixInx < pixList.size(); pixInx++){
			if(pixList.get(pixInx).getCoord() == coord){
				return pixList.get(pixInx);
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
		return pixList.get(inx);
	}
	
	/** Checks if this Shape is at the image's edge.
	 * Does so by calling Pix.isImageEdge on every component Pix.
	 * 
	 * @param pix
	 * @param imageObj
	 * @return
	 */
	public boolean isImageEdge(ImageObj imageObj){
		for(int inx = 0; inx < pixList.size(); inx++){
			if(pixList.get(inx).isImageEdge(imageObj)){
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
		for(int inx = 0; inx < this.pixList.size(); inx++){
			if(this.pixList.get(inx).isShapeEdge(this)){
				this.edgeList.add(this.pixList.get(inx));
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
