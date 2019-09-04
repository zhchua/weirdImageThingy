package loltest;

import java.util.ArrayList;
import java.util.List;

public class PixList{
	
	private ArrayList<Pix> aList;
	
	/** Initialize PixList without parameters.
	 * 
	 */
	public PixList(){
		this.aList = new ArrayList<Pix>();
	}
	
	private void strictCopy(PixList otherPixList){
		if(otherPixList.getList() != null){
			for(int inx = 0; inx < otherPixList.size(); inx++){
				this.aList.add(new Pix(otherPixList.get(inx), CopyDepth.STRICT));
			}
		}
	}
	
	private void refCopy(PixList otherPixList){
		if(otherPixList.getList() != null){
			for(int inx = 0; inx < otherPixList.size(); inx++){
				this.aList.add(otherPixList.get(inx));
			}
		}
	}
	
	/** Makes this PixList a copy of the given PixList. 
	 * 
	 * @param otherPixList
	 */
	public PixList(PixList pixList, CopyDepth cd){
		if(cd == CopyDepth.STRICT){
			strictCopy(pixList);
		}
		else if(cd == CopyDepth.REFERENCE){
			refCopy(pixList);
		}
		else{
			this.aList = pixList.getList();
		}
	}
	
	private void _____GETTERS_AND_SETTERS_____(){}
	
	public ArrayList<Pix> getList(){
		return this.aList;
	}
	
	/** Adds given Pix into this PixList.
	 * 
	 * @param pix
	 */
	public void add(Pix pix){
		if(aList == null){
			aList = new ArrayList<>();
		}
		this.aList.add(pix);
	}

	/** Gets Pix at the given index.
	 * 
	 * @param inx
	 * @return
	 */
	public Pix get(int inx){
		return this.aList.get(inx);
	}
	
	/** Removes Pix at the given index.
	 * 
	 * @param pos
	 */
	public void remove(int pos){
		ArrayList<Pix> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(inx != pos){
				newAList.add(aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes given Pix if it exists in this PixList.
	 * 
	 * @param pix
	 */
	public void remove(Pix pix){
		ArrayList<Pix> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(!this.aList.get(inx).sameAs(pix)){
				newAList.add(this.aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes Pixs in given list from this PixList, if they exist in this.
	 * 
	 * @param delList
	 */
	public void remove(List<Pix> delList){
		for(int inx = 0; inx < delList.size(); inx++){
			remove(delList.get(inx));
		}
	}
	
	/** Get the size of the PixList.
	 * 
	 * @return
	 */
	public int size(){
		return this.aList.size();
	}
	
	/** Checks if this PixList's arraylist exists or is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		if(aList == null || aList.isEmpty()){
			return true;
		}
		return false;
	}
	
	/** Get a copy of this PixList.
	 * 
	 * @return
	 */
	public PixList copy(){
		PixList newPixListObj = new PixList();
		for(int inx = 0; inx < this.size(); inx++){
			newPixListObj.add(this.get(inx));
		}
		return newPixListObj;
	}
	
	private void __________METHODS__________(){}
	
	/** Gets Pix with the given Coord.
	 * Returns null if it doesn't exist.
	 * 
	 * @param coord
	 * @return
	 */
	public Pix get(Coord coord){
		for(int pixInx = 0; pixInx < this.size(); pixInx++){
			if(this.get(pixInx).getCoord() == coord){
				return this.get(pixInx);
			}
		}
		return null;
	}
	
	/** Gets Pix with the given x and y coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Pix get(int x, int y){
		Coord getCoord = new Coord(x,y);
		return get(getCoord);
	}
	
	/** Checks if this PixList contains given Pix, or has Pix with matching
	 * Colour and Coord.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean contains(Pix pix){
		for(int inx = 0; inx < this.size(); inx++){
			if(this.get(inx).equals(pix) || this.get(inx).sameAs(pix)){
				return true;
			}
		}
		return false;
	}
}
