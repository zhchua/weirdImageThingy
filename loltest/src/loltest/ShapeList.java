package loltest;

import java.util.ArrayList;
import java.util.List;

public class ShapeList{
	
	private ArrayList<Shape> aList;
	
	public ShapeList(){
	}
	
	/** Makes this ShapeList a copy of the given ShapeList. 
	 * 
	 * @param otherShapeList
	 */
	public ShapeList(ShapeList otherShapeList){
		for(int inx = 0; inx < otherShapeList.size(); inx++){
			this.aList.add(otherShapeList.get(inx));
		}
	}
	
	/** Adds given Shape into this ShapeList.
	 * 
	 * @param shape
	 */
	public void add(Shape shape){
		if(aList == null){
			aList = new ArrayList<>();
		}
		this.aList.add(shape);
	}

	/** Gets Shape at the given index.
	 * 
	 * @param inx
	 * @return
	 */
	public Shape get(int inx){
		return this.aList.get(inx);
	}
	
	/** Removes Shape at the given index.
	 * 
	 * @param pos
	 */
	public void remove(int pos){
		ArrayList<Shape> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(inx != pos){
				newAList.add(aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes given Shape if it exists in this ShapeList.
	 * 
	 * @param shape
	 */
	public void remove(Shape shape){
		ArrayList<Shape> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(!this.aList.get(inx).sameAs(shape)){
				newAList.add(this.aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes Shapes in given list from this ShapeList, if they exist in this.
	 * 
	 * @param delList
	 */
	public void remove(List<Shape> delList){
		for(int inx = 0; inx < delList.size(); inx++){
			remove(delList.get(inx));
		}
	}
	
	/** Get the size of the ShapeList.
	 * 
	 * @return
	 */
	public int size(){
		return this.aList.size();
	}
	
	/** Checks if this ShapeList's arraylist exists or is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		if(aList == null || aList.isEmpty()){
			return true;
		}
		return false;
	}
	
	/** Get a copy of this ShapeList.
	 * 
	 * @return
	 */
	public ShapeList copy(){
		ShapeList newShapeListObj = new ShapeList();
		for(int inx = 0; inx < this.size(); inx++){
			newShapeListObj.add(this.get(inx));
		}
		return newShapeListObj;
	}
	
	private void _________________________________________(){}
	
	public boolean containsShape(Shape shape){
		for(int inx = 0 ; inx < this.size(); inx++){
			if(this.get(inx).sameAs(shape)){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsPix(Pix pix){
		for(int inx = 0; inx < this.size(); inx++){
			if(this.get(inx).containsPix(pix)){
				return true;
			}
		}
		return false;
	}
}
