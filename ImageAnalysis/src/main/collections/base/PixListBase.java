package Collections.Base;

import java.util.ArrayList;
import java.util.List;

import ImageObjects.Pix;

public class PixListBase{
	
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
		this.aList = new ArrayList<>();
		for(int pixInx = 0; pixInx < pixListBase.size(); pixInx++){
			this.aList.add(new Pix(pixListBase.get(pixInx)));
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
	
	public boolean sameAs(PixListBase otherPixListBase){
		if(otherPixListBase.getList() == null && this.getList() == null){
			return true;
		}
		if(otherPixListBase.isEmpty() && this.isEmpty()){
			return true;
		}
		if(this.size() != otherPixListBase.size()){
			return false;
		}
		for(int pixInx = 0; pixInx < this.size(); pixInx++){
			if(!this.contains(otherPixListBase.get(pixInx))){
				return false;
			}
		}
		return true;
	}
}
