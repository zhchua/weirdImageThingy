package main.imageElements.base;

import main.collections.PixList;

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
		return this.getPixList().sameAs(otherShape.getPixList());
	}
}
