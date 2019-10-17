package main.elementInfo;

import main.elementInfo.base.ColourBase;

public class Colour extends ColourBase {

	public Colour(double a, double r, double g, double b) {
		super(a, r, g, b);
	}

	public Colour(int a, int r, int g, int b) {
		super(a, r, g, b);
	}

	public Colour(double r, double g, double b) {
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
