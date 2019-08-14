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
			pixArray.get(i).setAlpha(a);
		}
		return true;
	}
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	public void setPixIntoShape(Pix pix){
		this.pixArray.add(pix);
	}
}
