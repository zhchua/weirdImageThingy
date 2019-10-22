package test.utility;

import main.utility.BaseObject;

public class TestObject extends BaseObject {
	
	private TestObject2 to2 = new TestObject2();
	private String st = "test str";
	private int in = 3;
	private double db = 3.3;

	public TestObject() {
	}

	public TestObject(int in){
		this.in = in;
	}
	
	public TestObject(double db){
		this.db = db;
	}
	
	public TestObject(String st){
		this.st = st;
	}
	
	public TestObject(Object object) {
		super(object);
	}

}
