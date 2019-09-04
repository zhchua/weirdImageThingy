package loltest;

import java.io.File;
import java.util.ArrayList;

public class ImageObj {
	private String name;
	private int width;
	private int height;
	private PixList pixList;
	private ShapeList shapeList;
	
	/** Create new ImageObj with only width and height information.
	 * 
	 * @param width
	 * @param height
	 */
	public ImageObj(int width, int height){
		this.width = width;
		this.height = height;
		this.pixList = new PixList();
		this.shapeList = new ShapeList();
	}
	
	/** Strict or Referenced copy of mutable object attributes. 
	 * 
	 * @param otherImgObj
	 */
	private void specialCopy(ImageObj otherImgObj, CopyDepth cd){
		if(otherImgObj.getShapeList() != null){
			if(!otherImgObj.getShapeList().isEmpty()){
				this.shapeList = new ShapeList(otherImgObj.getShapeList()
						, cd);
			}
		}
		if(otherImgObj.getPixList() != null){
			if(!otherImgObj.getPixList().isEmpty()){
				this.pixList = new PixList(otherImgObj.getPixList()
						, cd);
			}
		}
	}
	
	/** Create new ImageObj as a deep copy of given ImageObj.
	 *
	 * @param otherImgObj
	 * @param cd
	 */
	public ImageObj(ImageObj otherImgObj, CopyDepth cd){
		if(cd == CopyDepth.STRICT || cd == CopyDepth.REFERENCE){
			specialCopy(otherImgObj, cd);
		}
		else{
			this.pixList = otherImgObj.pixList;
			this.shapeList = otherImgObj.shapeList;
		}

		this.name = otherImgObj.getName();
		this.width = otherImgObj.getWidth();
		this.height = otherImgObj.getHeight();
	}
	
	private void _____GETTERS_AND_SETTERS_____(){}
	
	public String getName(){
		return this.name;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public PixList getPixList(){
		return this.pixList;
	}
	
	public ShapeList getShapeList(){
		return this.shapeList;
	}
	
	/** Spacer
	 * 
	 */
	private void __________METHODS__________(){}

	/** Sets pix into shape
	 * If 2 pix rgb are equal and adjacent, is same shape
	 * 
	 * @param pix
	 */
	public void autoassignPixToShape(Pix pix){
		if(this.shapeList == null){
			this.shapeList = new ShapeList();
		}
		for(int checkPixInx = 0; checkPixInx < pixList.size(); checkPixInx++){
			if(pix.shouldbeSameShapeAs(pixList.get(checkPixInx))){
				if(shapeList.containsPix((pixList.get(checkPixInx)))){
					pixList.get(checkPixInx).getShape(shapeList)
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
		for(int currentPixInx = 0; currentPixInx < pixList.size(); currentPixInx++){
			if(!pixList.get(currentPixInx).isInShapes(shapeList)){
				autoassignPixToShape(pixList.get(currentPixInx));
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
}
