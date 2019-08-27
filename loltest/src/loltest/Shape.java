package loltest;

import java.util.ArrayList;

public class Shape {

	private ArrayList<Pix> pixArray;
	private ArrayList<Pix> verticeArray;
	
	/** Sets alpha channel of all pixs in shape to the specified value.
	 * 
	 * @param a
	 * @return
	 */
	public boolean setAllAlpha(float a){
		if(pixArray == null){
			return false;
		}
		for(int i = 0; i < pixArray.size(); i++){
			pixArray.get(i).getColour().setA(a);
		}
		return true;
	}
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	/** Checks if given pix is in this shape
	 * 
	 * @param pix
	 * @return
	 */
	public boolean pixIsInShape(Pix pix){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(pix == getPixArray().get(pixInx)){
				return true;
			}
		}
		return false;
	}
	
	/** Inserts given pix into this shape. 
	 * 
	 * @param pix
	 */
	public void setPixIntoShape(Pix pix){
		if(this.pixArray == null){
			this.pixArray = new ArrayList<Pix>();
		}
		this.pixArray.add(pix);
	}
	
	/** Removes given pix from this shape.
	 * 
	 * @param pix
	 * @return
	 */
	public boolean deletePixFromShape(Pix pix){
		return pixArray.remove(pix);
	}
}
