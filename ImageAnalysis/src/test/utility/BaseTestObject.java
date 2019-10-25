package test.utility;

import main.utility.BaseObject;

public class BaseTestObject extends BaseObject {

	public BaseTestObject() {
	}

	public BaseTestObject(Object object) {
		super(object);
	}
	
	public int rndInt(){
		return rndInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public double rndDbl(){
		return rndDbl(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public int rndInt(int min, int max){
		return (int) rndDbl(min, max);
	}
	
	public int rndInt(double min, double max){
		return (int) rndDbl(min, max);
	}
	
	public double rndDbl(double min, double max){
		return min + (Math.random() * (max - min));
	}
}
