package loltest;

import java.io.File;
import java.util.ArrayList;

public class ImageObj {
	private String name;
	private int width;
	private int height;
	private ArrayList<Pix> pixArray;
	private ShapeList shapeList;
	
	/** Create new ImageObj with only width and height information.
	 * 
	 * @param width
	 * @param height
	 */
	public ImageObj(int width, int height){
		this.width = width;
		this.height = height;
		this.pixArray = new ArrayList<>();
		this.shapeList = new ShapeList();
	}
	
	/** Create new ImageObj as a deep copy of given ImageObj.
	 * 
	 * @param otherImgObj
	 */
	public ImageObj(ImageObj otherImgObj){
		if(otherImgObj.getShapeList() != null){
			if(!otherImgObj.getShapeList().isEmpty()){
				this.shapeList = new ShapeList(otherImgObj.getShapeList());
			}
		}
		if(otherImgObj.getPixArray() != null){
			if(!otherImgObj.getPixArray().isEmpty()){
				this.pixArray = new ArrayList<>(otherImgObj.getPixArray());
			}
		}
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
	
	public ShapeList getShapeList(){
		return this.shapeList;
	}
	
	/** Insert pix into pixArray.
	 * 
	 * @param pix
	 */
	public void addPix(Pix pix){
		if(pixArray == null){
			this.pixArray = new ArrayList<>();
		}
		if(pixArray.size() < this.getWidth()*this.getHeight()){
			pixArray.add(pix);
		}
	}

	/** Sets pix into shape
	 * If 2 pix rgb are equal and adjacent, is same shape
	 * 
	 * @param pix
	 */
	public void autoassignPixToShape(Pix pix){
		if(this.shapeList == null){
			this.shapeList = new ShapeList();
		}
		for(int checkPixInx = 0; checkPixInx < pixArray.size(); checkPixInx++){
			if(pix.shouldbeSameShapeAs(pixArray.get(checkPixInx))){
				if(shapeList.containsPix((pixArray.get(checkPixInx)))){
					pixArray.get(checkPixInx).getShape(shapeList)
					.insertPix(pix);
				}
				else{
					Shape shape = new Shape();
					shape.insertPix(pix);
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
			if(!pixArray.get(currentPixInx).isInShapes(shapeList)){
				autoassignPixToShape(pixArray.get(currentPixInx));
			}
		}
	}
	
	/** Replaces existing shapeList with new shapeList without edge shapes
	 * 
	 */
	public void cullImgEdgeShapes(){
		ShapeList newShapeList = new ShapeList();
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(!shapeList.get(shapeInx).isImageEdge(this)){
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
	public Pix getPix(Coord coord){
		for(int pixInx = 0; pixInx < pixArray.size(); pixInx++){
			if(pixArray.get(pixInx).getCoord() == coord){
				return pixArray.get(pixInx);
			}
		}
		return null;
	}
	
	public Pix getPix(int inx){
		return pixArray.get(inx);
	}
	
	public Pix getPix(int x, int y){
		return getPix(new Coord(x,y));
	}
}
