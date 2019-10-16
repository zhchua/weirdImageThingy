package InfoObjects;

import InfoObjects.Base.ColourBase;

public class Colour extends ColourBase {

	public Colour(float a, float r, float g, float b) {
		super(a, r, g, b);
	}

	public Colour(int a, int r, int g, int b) {
		super(a, r, g, b);
	}

	public Colour(float r, float g, float b) {
		super(r, g, b);
	}

	public Colour(int r, int g, int b) {
		super(r, g, b);
	}

	public Colour(ColourBase colour) {
		super(colour);
	}
	
	public Colour(Colour colour){
		super(colour);
	}

	
}
