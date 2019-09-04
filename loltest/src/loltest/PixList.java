package loltest;

import java.util.ArrayList;
import java.util.List;

public class PixList{
	
	private ArrayList<Pix> aList;
	
	public PixList(){
	}
	
	/** Makes this PixList a copy of the given PixList. 
	 * 
	 * @param otherPixList
	 */
	public PixList(PixList otherPixList, CopyDepth cd){
		if(cd == CopyDepth.STRICT){
			for(int inx = 0; inx < otherPixList.size(); inx++){
				this.aList.add(new Pix(otherPixList.get(inx), CopyDepth.STRICT));
			}
		}
		else if(cd == CopyDepth.REFERENCE){
			for(int inx = 0; inx < otherPixList.size(); inx++){
				this.aList.add(otherPixList.get(inx));
			}
		}
		else{
			this.aList = otherPixList.getList();
		}

	}
	
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
	
	private void _________________________________________(){}
}
