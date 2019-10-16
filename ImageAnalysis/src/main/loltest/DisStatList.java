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
		if(cd == CopyDepth.STRICT){
			this.aList = new ArrayList<DisStat>();
			for(int dsInx = 0; dsInx < disStatList.size(); dsInx++){
				this.aList.add(new DisStat(disStatList.get(dsInx)
						, CopyDepth.STRICT));
			}
		}
		else if(cd == CopyDepth.REFERENCE){
			this.aList = new ArrayList<DisStat>();
			for(int dsInx = 0; dsInx < disStatList.size(); dsInx++){
				this.aList.add(disStatList.get(dsInx));
			}
		}
		else{
			this.aList = disStatList.getList();
		}
	}
	
	public ArrayList<DisStat> getList(){
		return this.aList;
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
	
	/** Checks if this DisStatList contains the given DisStat,
	 * or has a DisStat with identical parameters.
	 * 
	 * @param disStat
	 * @return
	 */
	public boolean containsDisStat(DisStat disStat){
		for(int inx = 0 ; inx < this.size(); inx++){
			if(this.get(inx).sameAs(disStat) || this.get(inx).equals(disStat)){
				return true;
			}
		}
		return false;
	}
	
	/** Checks if there exists a DisStat with an Angle corresponding to the
	 * given Angle.
	 * 
	 * @param angle
	 * @return
	 */
	public boolean containsAngle(Angle angle){
		for(int inx = 0; inx < this.size(); inx++){
			if(this.get(inx).getAngle().sameAs(angle)){
				return true;
			}
		}
		return false;
	}
	
	/** Determines the shortest length for the given Angle.
	 * Returns -1 if does not exist.
	 * 
	 * @param angle
	 * @return
	 */
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
	
	/** Determines the longest length for the given Angle.
	 * Returns -1 if does not exist.
	 * 
	 * @param angle
	 * @return
	 */
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
	
	/** Calculates the average length for the given Angle.
	 * 
	 * @param angle
	 * @return
	 */
	public float getAvgLengthAtAngle(Angle angle){
		float sum = 0;
		int cnt = 0;
		
		for(int dsInx = -1; dsInx < this.size(); dsInx++){
			if(this.get(dsInx).getAngle().sameAs(angle)){
				sum = sum + this.get(dsInx).getLength();
				cnt++;
			}
		}
		if(flEq(sum,0) || cnt==0){
			return -1;
		}
		return sum/cnt;
	}
}
