package loltest;

public class DisStat {

	private float length;
	private Angle angle;
	
	public DisStat(Angle angle, float length){
		this.length = length;
		this.angle = angle;
	}
	
	public DisStat(DisStat disStat, CopyDepth cd){
		if(cd == CopyDepth.STRICT){
			this.angle = new Angle(disStat.getAngle());
		}
		else if(cd == CopyDepth.REFERENCE){
			this.angle = disStat.getAngle();
		}
		else{
			this.angle = disStat.angle;
		}
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
	
	/** Determines equality between two floats to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	private boolean flEq(float fl1, float fl2){
		float errMar1 = (float) ((0.000001) * fl1);
		float errMar2 = (float) ((0.000001) * fl2);
		
		if(Math.abs(fl1 - fl2) == 0 || Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1-fl2) < errMar2){
			return true;
		}
		return false;
	}
	
	public boolean sameAs(DisStat otherDisStat){
		if(this.getAngle().sameAs(otherDisStat.getAngle())
				&& flEq(this.getLength(), otherDisStat.getLength())){
			return true;
		}
		return false;
	}
	
	
}
