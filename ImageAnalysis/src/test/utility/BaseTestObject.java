package test.utility;

import main.utility.BaseObject;

public class BaseTestObject extends BaseObject {

	public BaseTestObject() {
	}

	public BaseTestObject(Object object) {
		super(object);
	}
	
	public int rndInt(int min, int max){
		return (int) rndDbl(min, max);
	}
	
	public double rndDbl(double min, double max){
		return Math.random() * (max - min);
	}
}
