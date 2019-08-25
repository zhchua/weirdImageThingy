package loltest;

import java.util.ArrayList;
import java.util.HashMap;

// this code assumes disruption template is a white/black image
// where white 255 is the background and black 0 is the disruption target
// and disruption target is a contiguous black shape

public class DisruptionTemplate extends ImageObj {
	
	private ArrayList<Pix> targetEdges;
	
	private Shape targetShape;
	
	private HashMap<Float, Float> disruptionStats;
	
	private Colour white;
	
	private Colour black;
	
	public DisruptionTemplate(int width, int height){
		super(width, height);
		this.white = new Colour((float) 255,(float)255,(float)255,(float) 255);
		this.black = new Colour((float)0,(float)0,(float)0, (float) 0);
	}
	
	public HashMap<Float, Float> getDisruptionStats(){
		return this.disruptionStats;
	}
	
	// creates target shape from arraylist of all existing shapes
	// determines target shape by looking for black shape
	// prerequisite: shapes must be generated and there should be more than one black shape
	public boolean generateTargetShape(){
		if(this.getShapeList() == null){
			return false;
		}
		for(int shapeInx = 0; shapeInx < this.getShapeList().size(); shapeInx++){
			if(this.getShapeList().get(shapeInx).getPixArray().get(0).getColour() 
					== black){
				this.targetShape = this.getShapeList().get(shapeInx);
				return true;
			}
		}
		return false;
	}
	
	// randomly searches for an edge pixel
	// edge pixel being adjacent to WHITE
	public Coord getStartPoint(){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(getPixArray().get(pixInx).getColour() == black){
				if(!this.pixIsAdjacentToSameColour(getPixArray().get(pixInx))){
					return getPixArray().get(pixInx).getCoord();
				}
			}
		}
		return null;
	}
	
	public void generateDisruptionStats(){	
	}
}
