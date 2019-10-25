package main.collections.base;

import java.util.ArrayList;
import java.util.List;

import main.imageElements.Pix;
import main.utility.BaseObject;


public class PixListBase extends BaseObject{
	
	private ArrayList<Pix> aList;
	
	/** Initialize PixList without parameters.
	 * 
	 */
	public PixListBase(){
	}
	
	/** Makes this PixList a copy of the given PixList. 
	 * 
	 * @param otherPixList
	 */
	public PixListBase(PixListBase pixListBase){
		this.aList = new ArrayList<Pix>();
		for(int pixInx = 0; pixInx < pixListBase.size(); pixInx++){
			this.aList.add(new Pix(pixListBase.get(pixInx)));
		}
	}
	
	public List<Pix> getList(){
		return this.aList;
	}
	
	/** Adds given Pix into this PixList.
	 * 
	 * @param pix
	 */
	public void add(Pix pix){
		if(aList == null){
			aList = new ArrayList<Pix>();
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
		ArrayList<Pix> newAList = new ArrayList<Pix>();
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
		ArrayList<Pix> newAList = new ArrayList<Pix>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(!this.aList.get(inx).equals(pix)){
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
		return (aList == null || aList.isEmpty());
	}
	
	
	/** Checks if this PixList contains given Pix, or has Pix with matching
	 * Colour and Coord.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean contains(Pix pix){
		for(int inx = 0; inx < this.size(); inx++){
			if(this.get(inx).equals(pix) || this.get(inx)==pix){
				return true;
			}
		}
		
		return false;
	}
}
