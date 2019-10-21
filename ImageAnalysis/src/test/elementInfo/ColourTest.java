package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Colour;
import test.elementInfo.base.ColourBaseTest;

public class ColourTest extends ColourBaseTest {

	public Colour col;
	public double fl1;
	public double fl2;
	public double fl3;
	public double fl4;
	
	
	@Before
	public void setUp() throws Exception {
		fl1 = Math.random() * 255;
		fl2 = Math.random() * 255;
		fl3 = Math.random() * 255;
		fl4 = Math.random() * 255;
	}

	@Test
	public void testColourFloatFloatFloatFloat() {
		col = new Colour(fl1, fl2, fl3, fl4);
		assertTrue(col.getA() == fl1 && col.getR() == fl2 && col.getG() == fl3
				&& col.getB() == fl4);
	}

	@Test
	public void testColourIntIntIntInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColourFloatFloatFloat() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColourIntIntInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColourColourBase() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColourColour() {
		fail("Not yet implemented"); // TODO
	}

}
