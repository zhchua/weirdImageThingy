package loltest;

import java.util.ArrayList;
import java.util.HashMap;

// this code assumes disruption template is a white/black image
// where white 255 is the background and black 0 is the disruption target
// and disruption target is a contiguous black shape

public class DisruptionTemplate extends Imagefile {
	
	private ArrayList<Pix> targetEdges;
	
	private HashMap<Float, Float> disruptionStats;
	
	private Colour white;
	
	private Colour black;
	
	public DisruptionTemplate(){
		this.white = createColour(255,255,255);
		this.black = createColour(0,0,0);
	}
	
	private Colour createColour(float r, float g, float b){
		Colour colour = new Colour();
		colour.setRGB(r, g, b);
		return colour;
	}
	
	public HashMap<Float, Float> getDisruptionStats(){
		return this.disruptionStats;
	}
	
	
	
	// randomly searches for an edge pixel
	// edge pixel being adjacent to WHITE
	public HashMap<Integer, Integer> getStartPoint(){
		for(int pixInx = 0; pixInx < getPixArray().size(); pixInx++){
			if(getPixArray().get(pixInx).getColour() == black){
				
			}
		}
	}
	
	public void generateDisruptionStats(){
		
	}
}
