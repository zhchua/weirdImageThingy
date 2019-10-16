package ImageObjects.Base;

import java.util.ArrayList;
import java.util.List;

import Collections.PixList;
import Image.ImageObj;
import ImageObjects.Pix;
import InfoObjects.Colour;
import InfoObjects.Coord;

public class ShapeBase {

	private PixList pixList;
	
	public ShapeBase(){
	}
	
	public ShapeBase(ShapeBase shapeBase){
		this.pixList = new PixList(shapeBase.getPixList());
	}
	
	
	public PixList getPixList(){
		return this.pixList;
	}
	
	public void setPixList(PixList pixList){
		this.pixList = pixList;
	}
	
	public boolean sameAs(ShapeBase otherShape){
		if(this.getPixList().sameAs(otherShape.getPixList())){
			return true;
		}
		return false;
	}
}
