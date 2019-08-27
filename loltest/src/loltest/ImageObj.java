package loltest;

import java.io.File;
import java.util.ArrayList;

public class ImageObj {
	//private File file;
	private String name;
	private int width;
	private int height;
	private ArrayList<Pix> pixArray;
	private ArrayList<Shape> shapeList;
	
	public ImageObj(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	public ArrayList<Shape> getShapeList(){
		return this.shapeList;
	}
	
	/** Insert pix into pixArray.
	 * 
	 * @param pix
	 */
	public void setPixIntoPixArray(Pix pix){
		if(pixArray == null){
			this.pixArray = new ArrayList<Pix>();
		}
		pixArray.add(pix);
	}
	
	/** Checks if a given pixel is in any shapes in shapeList.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsAlreadyInShapes(Pix pix){
		if(shapeList == null){
			return false;
		}
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(shapeList.get(shapeInx).pixIsInShape(pix)){
				return true;
			}
		}
		return false;
	}
	
	/** Checks if two given pixels are the same colour.
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean pixsSameColour(Pix pix1, Pix pix2){
		if(pix1.getColour() == pix2.getColour()){
			return true;
		}
		return false;
	}
	
	/** Checks if two given pixels are next to each other.
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean pixsAreAdjacent(Pix pix1, Pix pix2){
		int pix2xLow = pix2.getCoord().getX() - 1;
		int pix2xHigh = pix2.getCoord().getX() + 1;
		int pix2yLow = pix2.getCoord().getY() - 1;
		int pix2yHigh = pix2.getCoord().getY() + 1;
		
		if(pix1.getCoord().getX() >= pix2xLow && pix1.getCoord().getX() <= pix2xHigh){
			if(pix1.getCoord().getY() >= pix2yLow && pix1.getCoord().getY() <= pix2yHigh){
				return true;
			}
		}
		return false;
	}
	
	/** Checks if two given pixels should belong to the same shape.
	 * Calls same-colour checking and adjacency checking.
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public boolean pixsSameShape(Pix pix1, Pix pix2){
		if(pix1 != pix2 && pixsSameColour(pix1, pix2) && pixsAreAdjacent(pix1, pix2)){
			return true;	
		}
		return false;
	}
	
	/** Returns the shape that a given pixel belongs to.
	 * Prerequisite: given pixel must already be assigned to a shape
	 * 
	 * @param pix
	 * @return
	 */
	public Shape pixGetShape(Pix pix){
		if(!pixIsAlreadyInShapes(pix)){
			return null;
		}
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(shapeList.get(shapeInx).pixIsInShape(pix)){
				return shapeList.get(shapeInx);
			}
		}
		return null;
	}
	
	/** Sets pix into shape
	 * If 2 pix rgb are equal and adjacent, is same shape
	 * 
	 * @param pix
	 */
	public void autoassignPixToShape(Pix pix){
		if(this.shapeList == null){
			this.shapeList = new ArrayList<Shape>();
		}
		for(int checkPixInx = 0; checkPixInx < pixArray.size(); checkPixInx++){
			if(pixsSameShape(pixArray.get(checkPixInx),pix)){
				if(pixIsAlreadyInShapes(pixArray.get(checkPixInx))){
					pixGetShape(pixArray.get(checkPixInx)).setPixIntoShape(pix);
				}
				else{
					Shape shape = new Shape();
					shape.setPixIntoShape(pix);
					shapeList.add(shape);
				}
			}
		}
	}
	
	/** Generate shapes for the shapeList by iterating through pixs. 
	 * 
	 * If pix is already in shape, pass
	 * 
	 */
	public void pixelwiseGenerateShapes(){
		for(int currentPixInx = 0; currentPixInx < pixArray.size(); currentPixInx++){
			if(!pixIsAlreadyInShapes(pixArray.get(currentPixInx))){
				autoassignPixToShape(pixArray.get(currentPixInx));
			}
		}
	}
	
	/** Checks if given pixel is at image edge
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsImgEdge(Pix pix){
		if(pix.getCoord().getX() == 0 || pix.getCoord().getX() == this.width){
			return true;
		}
		if(pix.getCoord().getY() == 0 || pix.getCoord().getY() == this.height){
			return true;
		}
		return false;
	}
	
	/** Checks if given shape is at image edge
	 * Does so by checking if given shape has pixel at image edge
	 * 
	 * @param shape
	 * @return
	 */
	public boolean shapeIsImgEdge(Shape shape){
		for(int pixInx = 0; pixInx<shape.getPixArray().size(); pixInx++){
			if(pixIsImgEdge(shape.getPixArray().get(pixInx))){
				return true;
			}
		}
		return false;
	}
	
	/** Replaces existing shapeList with new shapeList without edge shapes
	 * 
	 */
	public void cullImgEdgeShapes(){
		ArrayList<Shape> newShapeList = new ArrayList<Shape>();
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(!shapeIsImgEdge(shapeList.get(shapeInx))){
				newShapeList.add(shapeList.get(shapeInx));
			}
		}
		this.shapeList = newShapeList;
	}
	
	/**	Returns pix located at given coord.
	 * 
	 * @param coord
	 * @return
	 */
	public Pix getPixByCoord(Coord coord){
		for(int pixInx = 0; pixInx < pixArray.size(); pixInx++){
			if(pixArray.get(pixInx).getCoord() == coord){
				return pixArray.get(pixInx);
			}
		}
		return null;
	}
	
	/** Removes given shape from shapeList.
	 * 
	 * @param shape
	 * @return
	 */
	public boolean cullShape(Shape shape){
		return shapeList.remove(shape);
	}
	
	/** Checks if given pix is adjacent to another pix of same colour.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsAdjacentToSameColour(Pix pix){
		int pixXLow = pix.getCoord().getX() - 1;
		int pixXHigh = pix.getCoord().getX() + 1;
		int pixYLow = pix.getCoord().getY() - 1;
		int pixYHigh = pix.getCoord().getY() + 1;
		
		for(int checkX = pixXLow; checkX <= pixXHigh; checkX++){
			for(int checkY = pixYLow; checkY <= pixYHigh; checkY++){
				Pix checkPix = getPixByCoord(new Coord(checkX, checkY));
				if(pix != checkPix && pixsAreAdjacent(pix, checkPix) 
						&& this.pixsSameColour(pix, checkPix)){
					return true;
				}
			}
		}
		return false;
	}
	
}
