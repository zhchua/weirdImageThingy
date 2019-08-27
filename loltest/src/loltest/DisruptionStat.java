package loltest;

public class DisruptionStat {

	private float length;
	private float angle;
	
	public DisruptionStat(float angle, float length){
		this.length = length;
		this.angle = angle;
	}
	
	public void setAngle(float angle){
		this.angle = angle;
	}
	
	public void setLength(float length){
		this.length = length;
	}
	
	public float getAngle(){
		return this.angle;
	}
	
	public float getLength(){
		return this.length;
	}
}
