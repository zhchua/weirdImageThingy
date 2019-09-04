package loltest;

import java.util.ArrayList;
import java.util.List;

public class DisStatList{
	
	private ArrayList<DisStat> aList;
	
	public DisStatList(){
		this.aList = new ArrayList<DisStat>();
	}
	
	/** Makes this DisStatList a copy of the given DisStatList. 
	 * 
	 * @param otherDisStatList
	 */
	public DisStatList(DisStatList disStatList, CopyDepth cd){
		for(int inx = 0; inx < disStatList.size(); inx++){
			this.aList.add(disStatList.get(inx));
		}
	}
	
	/** Adds given DisStat into this DisStatList.
	 * 
	 * @param shape
	 */
	public void add(DisStat disStat){
		if(aList == null){
			aList = new ArrayList<>();
		}
		this.aList.add(disStat);
	}

	/** Gets Shape at the given index.
	 * 
	 * @param inx
	 * @return
	 */
	public DisStat get(int inx){
		return this.aList.get(inx);
	}
	
	/** Removes Shape at the given index.
	 * 
	 * @param pos
	 */
	public void remove(int pos){
		ArrayList<DisStat> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(inx != pos){
				newAList.add(aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes given Shape if it exists in this DisStat.
	 * 
	 * @param shape
	 */
	public void remove(DisStat disStat){
		ArrayList<DisStat> newAList = new ArrayList<>();
		for(int inx = 0; inx < this.aList.size(); inx++){
			if(!this.aList.get(inx).sameAs(disStat)){
				newAList.add(this.aList.get(inx));
			}
		}
		this.aList = newAList;
	}
	
	/** Removes DisStats in given list from this DisStat, if they exist in this.
	 * 
	 * @param delList
	 */
	public void remove(List<DisStat> delList){
		for(int inx = 0; inx < delList.size(); inx++){
			remove(delList.get(inx));
		}
	}
	
	/** Get the size of the DisStat.
	 * 
	 * @return
	 */
	public int size(){
		return this.aList.size();
	}
	
	/** Checks if this DisStat's arraylist exists or is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		if(aList == null || aList.isEmpty()){
			return true;
		}
		return false;
	}
	
	/** Get a copy of this DisStat.
	 * 
	 * @return
	 */
	public DisStatList copy(){
		DisStatList newDisStatObj = new DisStatList();
		for(int inx = 0; inx < this.size(); inx++){
			newDisStatObj.add(this.get(inx));
		}
		return newDisStatObj;
	}
	
	/** Determines equality between two floats to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	private boolean flEq(float fl1, float fl2){
		float errMar1 = (float) ((0.000001) * fl1);
		float errMar2 = (float) ((0.000001) * fl2);
		
		if(Math.abs(fl1 - fl2) == 0 || Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	private void _________________________________________(){}
	
	public boolean containsDisStat(DisStat disStat){
		for(int inx = 0 ; inx < this.size(); inx++){
			if(this.get(inx).sameAs(disStat)){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAngle(Angle angle){
		for(int inx = 0; inx < this.size(); inx++){
			if(this.get(inx).getAngle().sameAs(angle)){
				return true;
			}
		}
		return false;
	}
	
	public float getMinLengthAtAngle(Angle angle){
		float min = -1;
		for(int dsInx = 0; dsInx < this.size(); dsInx++){
			if(this.get(dsInx).getAngle().sameAs(angle)){
				if(flEq(min,-1) || min > this.get(dsInx).getLength()){
					min = this.get(dsInx).getLength();
				}
			}
		}
		return min;
	}
	
	public float getMaxLengthAtAngle(Angle angle){
		float max = -1;
		for(int dsInx = -1; dsInx < this.size(); dsInx++){
			if(this.get(dsInx).getAngle().sameAs(angle)){
				if(flEq(max,-1) || max < this.get(dsInx).getLength()){
					max = this.get(dsInx).getLength();
				}
			}
		}
		return max;
	}
}
