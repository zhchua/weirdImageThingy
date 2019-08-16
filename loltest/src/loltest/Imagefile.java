package loltest;

import java.io.File;
import java.util.ArrayList;

public class Imagefile {
	private File file;
	private String name;
	private int width;
	private int height;
	private String outname;
	private String savename;
	private ArrayList<Pix> pixArray;
	private ArrayList<Shape> shapeList;
	
	public void setOutname(String savename){
		this.savename = savename;
	}
	
	public void setSavename(){
		this.outname = this.name + "output";
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public ArrayList<Pix> getPixArray(){
		return this.pixArray;
	}
	
	// checks if a given pixel is in a given shape
	public boolean pixIsInShape(Pix pix, Shape shape){
		for(int pixInx = 0; pixInx < shape.getPixArray().size(); pixInx++){
			if(pix == shape.getPixArray().get(pixInx)){
				return true;
			}
		}
		return false;
	}
	
	// checks if a given pixel is in any shapes in image shapelist
	public boolean pixIsAlreadyInShapes(Pix pix){
		if(shapeList == null){
			return false;
		}
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(pixIsInShape(pix, shapeList.get(shapeInx))){
				return true;
			}
		}
		return false;
	}
	
	// checks if two given pixels are the same colour
	public boolean pixsSameColour(Pix pix1, Pix pix2){
		if(pix1.getColour() == pix2.getColour()){
			return true;
		}
		return false;
	}
	
	// checks if two given pixels are within 1 pixel radius of each other
	public boolean pixsAreAdjacent(Pix pix1, Pix pix2){
		int pix2xLow = pix2.getCoord().getX() - 1;
		int pix2xHigh = pix2.getCoord().getX() + 1;
		int pix2yLow = pix2.getCoord().getY() - 1;
		int pix2yHigh = pix2.getCoord().getY() + 1;
		
		if(pix1.getCoord().getX() >= pix2xLow && pix1.getCoord().getX() <= pix2xHigh){
			if(pix1.getCoord().getY() >= pix2yLow && pix1.getCoord().getY() <= pix2yHigh){
				return true;
			}
		}
		return false;
	}
	
	// checks if two given pixels should belong to the same shape
	public boolean pixsSameShape(Pix pix1, Pix pix2){
		if(pix1 != pix2 && pixsSameColour(pix1, pix2) && pixsAreAdjacent(pix1, pix2)){
			return true;	
		}
		return false;
	}
	
	// returns the shape that a given pixel belongs to
	// prerequisite: given pixel must already be assigned to a shape
	public Shape pixGetShape(Pix pix){
		if(!pixIsAlreadyInShapes(pix)){
			return null;
		}
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(pixIsInShape(pix, shapeList.get(shapeInx))){
				return shapeList.get(shapeInx);
			}
		}
		return null;
	}
	
	// if 2 pix rgb are equal and adjacent, is same shape
	// requires refactoring
	public void pixelwiseGenerateShapes(){
		for(int currentPixInx = 0; currentPixInx < pixArray.size(); currentPixInx++){
			if(!pixIsAlreadyInShapes(pixArray.get(currentPixInx))){
				for(int checkPixInx = 0; checkPixInx < pixArray.size(); checkPixInx++){
					if(pixsSameShape(pixArray.get(checkPixInx),pixArray.get(currentPixInx))){
						if(pixIsAlreadyInShapes(pixArray.get(checkPixInx))){
							pixGetShape(pixArray.get(checkPixInx)).setPixIntoShape(pixArray.get(currentPixInx));
						}
						else{
							Shape shape = new Shape();
							shape.setPixIntoShape(pixArray.get(currentPixInx));
							shapeList.add(shape);
						}
					}
				}
			}
		}
	}
	
	// checks if given pixel is at image edge
	public boolean pixIsEdge(Pix pix){
		if(pix.getCoord().getX() == 0 || pix.getCoord().getX() == this.width){
			return true;
		}
		if(pix.getCoord().getY() == 0 || pix.getCoord().getY() == this.height){
			return true;
		}
		return false;
	}
	
	// checks if given shape is at image edge
	// does so by checking if given shape has pixel at image edge
	public boolean shapeIsEdge(Shape shape){
		for(int pixInx = 0; pixInx<shape.getPixArray().size(); pixInx++){
			if(pixIsEdge(shape.getPixArray().get(pixInx))){
				return true;
			}
		}
		return false;
	}
	
	// replaces existing shapeList with new shapeList without edge shapes
	public void cullEdgeShapes(){
		ArrayList<Shape> newShapeList = new ArrayList<Shape>();
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(!shapeIsEdge(shapeList.get(shapeInx))){
				newShapeList.add(shapeList.get(shapeInx));
			}
		}
		this.shapeList = newShapeList;
	}
	
	
}
