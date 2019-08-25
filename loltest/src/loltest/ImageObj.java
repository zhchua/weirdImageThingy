package loltest;

import java.io.File;
import java.util.ArrayList;

public class ImageObj {
	//private File file;
	private String name;
	private int width;
	private int height;
	private ArrayList<Pix> pixArray;
	private ArrayList<Shape> shapeList;
	
	public ImageObj(int width, int height){
		this.width = width;
		this.height = height;
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
	
	public ArrayList<Shape> getShapeList(){
		return this.shapeList;
	}
	
	public void setPixIntoPixArray(Pix pix){
		pixArray.add(pix);
	}
	
	// checks if a given pixel is in any shapes in image shapelist
	public boolean pixIsAlreadyInShapes(Pix pix){
		if(shapeList == null){
			return false;
		}
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(shapeList.get(shapeInx).pixIsInShape(pix)){
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
			if(shapeList.get(shapeInx).pixIsInShape(pix)){
				return shapeList.get(shapeInx);
			}
		}
		return null;
	}
	
	public void autoassignPixToShape(Pix pix){
		for(int checkPixInx = 0; checkPixInx < pixArray.size(); checkPixInx++){
			if(pixsSameShape(pixArray.get(checkPixInx),pix)){
				if(pixIsAlreadyInShapes(pixArray.get(checkPixInx))){
					pixGetShape(pixArray.get(checkPixInx)).setPixIntoShape(pix);
				}
				else{
					Shape shape = new Shape();
					shape.setPixIntoShape(pix);
					shapeList.add(shape);
				}
			}
		}
	}
	
	// if 2 pix rgb are equal and adjacent, is same shape
	// requires refactoring
	public void pixelwiseGenerateShapes(){
		for(int currentPixInx = 0; currentPixInx < pixArray.size(); currentPixInx++){
			if(!pixIsAlreadyInShapes(pixArray.get(currentPixInx))){
				autoassignPixToShape(pixArray.get(currentPixInx));
			}
		}
	}
	
	// checks if given pixel is at image edge
	public boolean pixIsImgEdge(Pix pix){
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
	public boolean shapeIsImgEdge(Shape shape){
		for(int pixInx = 0; pixInx<shape.getPixArray().size(); pixInx++){
			if(pixIsImgEdge(shape.getPixArray().get(pixInx))){
				return true;
			}
		}
		return false;
	}
	
	// replaces existing shapeList with new shapeList without edge shapes
	public void cullImgEdgeShapes(){
		ArrayList<Shape> newShapeList = new ArrayList<Shape>();
		for(int shapeInx = 0; shapeInx < shapeList.size(); shapeInx++){
			if(!shapeIsImgEdge(shapeList.get(shapeInx))){
				newShapeList.add(shapeList.get(shapeInx));
			}
		}
		this.shapeList = newShapeList;
	}
	
	public Pix getPixByCoord(Coord coord){
		for(int pixInx = 0; pixInx < pixArray.size(); pixInx++){
			if(pixArray.get(pixInx).getCoord() == coord){
				return pixArray.get(pixInx);
			}
		}
		return null;
	}
	
	public boolean cullShape(Shape shape){
		return shapeList.remove(shape);
	}
	
	public boolean pixIsAdjacentToSameColour(Pix pix){
		int pixXLow = pix.getCoord().getX() - 1;
		int pixXHigh = pix.getCoord().getX() + 1;
		int pixYLow = pix.getCoord().getY() - 1;
		int pixYHigh = pix.getCoord().getY() + 1;
		
		for(int checkX = pixXLow; checkX <= pixXHigh; checkX++){
			for(int checkY = pixYLow; checkY <= pixYHigh; checkY++){
				Pix checkPix = getPixByCoord(new Coord(checkX, checkY));
				if(pix != checkPix && pixsAreAdjacent(pix, checkPix) 
						&& this.pixsSameColour(pix, checkPix)){
					return true;
				}
			}
		}
		return false;
	}
	
}
