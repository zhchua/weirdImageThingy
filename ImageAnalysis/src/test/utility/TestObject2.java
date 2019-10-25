package test.utility;

import main.utility.BaseObject;

public class TestObject2 extends BaseObject {

	private String st = "test str2";
	private int in = 5;
	private double db = 5.5;
	
	public TestObject2() {
	}

	public TestObject2(int in){
		this.in = in;
	}
	
	public TestObject2(Object object) {
		super(object);
	}

}
