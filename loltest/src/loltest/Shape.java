package loltest;

import java.util.ArrayList;

public class Shape {

	private ArrayList<Pix> pixArray;
	private ArrayList<Pix> verticeArray;
	
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
	
	// checks if pix is in this shape
	public boolean pixIsInShape(Pix pix){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(pix == getPixArray().get(pixInx)){
				return true;
			}
		}
		return false;
	}
	
	public void setPixIntoShape(Pix pix){
		this.pixArray.add(pix);
	}
	
	public boolean deletePixFromShape(Pix pix){
		return pixArray.remove(pix);
	}
}
