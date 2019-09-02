package loltest;

public class DisruptionStat {

	private float length;
	private Angle angle;
	
	public DisruptionStat(Angle angle, float length){
		this.length = length;
		this.angle = angle;
	}
	
	public void setAngle(Angle angle){
		this.angle = angle;
	}
	
	public void setLength(float length){
		this.length = length;
	}
	
	public Angle getAngle(){
		return this.angle;
	}
	
	public float getLength(){
		return this.length;
	}
}
