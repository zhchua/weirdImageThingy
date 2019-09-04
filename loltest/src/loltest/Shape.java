package loltest;

import java.util.ArrayList;
import java.util.List;

public class Shape {

	private ArrayList<Pix> pixArray;
	private ArrayList<Pix> verticeArray;
	private ArrayList<Pix> edgeArray;
	
	/** Sets alpha channel of all pixs in shape to the specified value.
	 * 
	 * @param a
	 * @return
	 */
	public boolean setAllAlpha(float a){
		if(pixArray == null){
			return false;
		}
		for(int i = 0; i < pixArray.size(); i++){
			pixArray.get(i).getColour().setA(a);
		}
		return true;
	}
	
	public boolean sameAs(Shape otherShape){
		if(this.getPixArray().size() == otherShape.getPixArray().size()){
			for(int inx = 0; inx < this.getPixArray().size(); inx++){
				if(!otherShape.containsPix(this.getPixArray().get(inx))){
					return false;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	/** Checks if Shape's pixArray contains pix.
	 * Uses for loop and Pix.sameAs comparison because .contains calls .equals
	 * 
	 * @param pix
	 * @return
	 */
	public boolean containsPix(Pix pix){
		if(this.getPixArray().contains(pix)){
			return true;
		}
		for(int inx = 0; inx < this.getPixArray().size(); inx++){
			if(this.getPixArray().get(inx).sameAs(pix)
					|| this.getPixArray().get(inx).equals(pix)){
				return true;
			}
		}
		return false;
	}
	
	/** Inserts given pix into this shape. 
	 * 
	 * @param pix
	 */
	public void insertPix(Pix pix){
		if(this.pixArray == null){
			this.pixArray = new ArrayList<Pix>();
		}
		this.pixArray.add(pix);
	}
	
	/** Removes given pix from this shape.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean deletePixFromShape(Pix pix){
		return pixArray.remove(pix);
	}
	
	/** Sets all pix in the shape to the given colour.
	 * 
	 * @param colour
	 * @return
	 */
	public boolean setAllColour(Colour colour){
		if(pixArray == null){
			return false;
		}
		for(int i = 0; i < pixArray.size(); i++){
			pixArray.get(i).setColour(colour);
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
		for(int pixInx = 0; pixInx < pixArray.size(); pixInx++){
			if(pixArray.get(pixInx).getCoord() == coord){
				return pixArray.get(pixInx);
			}
		}
		return null;
	}
	
	/** Gets the Pix in the pixArray of this Shape by index.
	 * This shape should already have a pixArray initialized, 
	 * and inx should be within pixArray.size().
	 * 
	 * @param inx
	 * @return
	 */
	public Pix getPix(int inx){
		return pixArray.get(inx);
	}
	
	/** Checks if this Shape is at the image's edge.
	 * Does so by calling Pix.isImageEdge on every component Pix.
	 * 
	 * @param pix
	 * @param imageObj
	 * @return
	 */
	public boolean isImageEdge(ImageObj imageObj){
		for(int inx = 0; inx < pixArray.size(); inx++){
			if(pixArray.get(inx).isImageEdge(imageObj)){
				return true;
			}
		}
		return false;
	}
	
	/** Generates and returns arrayList of Pixs that are edges of this Shape.
	 * 
	 * @return
	 */
	public ArrayList<Pix> generateEdgeArray(){
		if(this.edgeArray == null){
			this.edgeArray = new ArrayList<>();
		}
		
		for(int inx = 0; inx < this.pixArray.size(); inx++){
			if(this.pixArray.get(inx).isShapeEdge(this)){
				this.edgeArray.add(this.pixArray.get(inx));
			}
		}
		return this.edgeArray;
	}
	
	/** Gets arrayList of Pixs that are edges of this Shape.
	 * If edgeArray does not yet exist, will attempt to generate.
	 * 
	 * @return
	 */
	public ArrayList<Pix> getEdgeArray(){
		if(this.edgeArray == null){
			generateEdgeArray();
		}
		return this.edgeArray;
	}
	
	/** Clears edgeArray by making it null.
	 * 
	 */
	public void deleteEdgeArray(){
		this.edgeArray = null;
	}
}
