package main.imageElements;

import main.utility.BaseObject;

public class PTest extends BaseObject {

	private Pix pix;
	
	public PTest() {
	}

	public PTest(Object givenObject) {
		super(givenObject);
	}
	
	public PTest(Pix pix){
		this.pix = pix;
	}

}
