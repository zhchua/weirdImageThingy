package loltest;

import java.util.ArrayList;

public class Shape {

	private ArrayList<Pix> pixArray;
	private ArrayList<Pix> verticeArray;
	
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
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	/** Checks if given pix is in this shape
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsInShape(Pix pix){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(pix == getPixArray().get(pixInx)){
				return true;
			}
		}
		return false;
	}
	
	/** Inserts given pix into this shape. 
	 * 
	 * @param pix
	 */
	public void setPixIntoShape(Pix pix){
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
	public Pix findPixByCoord(Coord coord){
		for(int pixInx = 0; pixInx < pixArray.size(); pixInx++){
			if(pixArray.get(pixInx).getCoord() == coord){
				return pixArray.get(pixInx);
			}
		}
		return null;
	}
	
	/** Checks if given pix is the edge of the shape
	 * Does so by checking if pixs exist with adjacent coords.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsShapeEdge(Pix pix){
		int pixXLow = pix.getCoord().getX() - 1;
		int pixXHigh = pix.getCoord().getX() + 1;
		int pixYLow = pix.getCoord().getY() - 1;
		int pixYHigh = pix.getCoord().getY() + 1;
		
		for(int checkX = pixXLow; checkX <= pixXHigh; checkX++){
			for(int checkY = pixYLow; checkY <= pixYHigh; checkY++){
				if(findPixByCoord(new Coord(checkX,checkY)) == null){
					return true;
				}
			}
		}
		return false;
	}
}
