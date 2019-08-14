package loltest;

import java.util.ArrayList;
import java.util.HashMap;

// this code assumes disruption template is a white/black image
// where white is the background and black is the disruption target
// and disruption target is a contiguous black shape

public class DisruptionTemplate extends Imagefile {
	
	private ArrayList<Pix> targetEdges;
	
	private HashMap<Float, Float> disruptionStats;
	
	public HashMap<Float, Float> getDisruptionStats(){
		return this.disruptionStats;
	}
	
	public void generateDisruptionStats(){
		int start
	}
}
