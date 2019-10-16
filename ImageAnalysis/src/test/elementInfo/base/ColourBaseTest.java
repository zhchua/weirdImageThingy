package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.elementInfo.base.ColourBase;
import main.util.O;

public class ColourBaseTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testColourBaseFloatFloatFloatFloat() {
		float fl1 = (float) Math.random() * 255;
		float fl2 = (float) Math.random() * 255;
		float fl3 = (float) Math.random() * 255;
		float fl4 = (float) Math.random() * 255;
		
		ColourBase colourBase = new ColourBase(fl1, fl2, fl3, fl4);
		Assert.assertTrue(colourBase.getA() == fl1 && colourBase.getR() == fl2
				&& colourBase.getG() == fl3 && colourBase.getB() == fl4);
	}

	@Test
	public void testColourBaseIntIntIntInt() {
		int int1 = (int) Math.random() * 255;
		int int2 = (int) Math.random() * 255;
		int int3 = (int) Math.random() * 255;
		int int4 = (int) Math.random() * 255;
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		Assert.assertTrue(colourBase.getA() == int1 && colourBase.getR() == int2
				&& colourBase.getG() == int3 && colourBase.getB() == int4);
	}

	@Test
	public void testColourBaseFloatFloatFloat() {
		float fl1 = (float) Math.random() * 255;
		float fl2 = (float) Math.random() * 255;
		float fl3 = (float) Math.random() * 255;
		
		ColourBase colourBase = new ColourBase(fl1, fl2, fl3);
		Assert.assertTrue(colourBase.getA() == 0 && colourBase.getR() == fl1
				&& colourBase.getG() == fl2 && colourBase.getB() == fl3);
	}

	@Test
	public void testColourBaseIntIntInt() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3);
		Assert.assertTrue(colourBase.getA() == 0 && colourBase.getR() == int1
				&& colourBase.getG() == int2 && colourBase.getB() == int3);
	}

	@Test
	public void testColourBaseColourBase() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(colourBase);
		colourBase.setA((int) (Math.random() * 255));
		System.out.println(colourBase.getA());
		System.out.println(colourBase2.getA());
		Assert.assertNotEquals(colourBase.getA(), colourBase2.getA());
	}

	@Test
	public void testSetR() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setR(int4 - 1);
		Assert.assertEquals(int4-1, colourBase.getR(), (int4-1)/1000);
	}

	@Test
	public void testSetG() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setG(int4 - 1);
		Assert.assertEquals(int4-1, colourBase.getG(), (int4-1)/1000);
	}

	@Test
	public void testSetB() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setB(int4 - 1);
		Assert.assertEquals(int4-1, colourBase.getB(), (int4-1)/1000);
	}

	@Test
	public void testSetA() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setA(int4 - 1);
		Assert.assertEquals(int4-1, colourBase.getA(), (int4-1)/1000);
	}

	@Test
	public void testSetRGB() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		float fl1 = (float) Math.random() * 255;
		float fl2 = (float) Math.random() * 255;
		float fl3 = (float) Math.random() * 255;
		colourBase.setRGB(fl1, fl2, fl3);
		
		Assert.assertTrue(colourBase.getR() == fl1 && colourBase.getG() == fl2
				&& colourBase.getB() == fl3);
	}

	@Test
	public void testSetARGB() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		float fl1 = (float) Math.random() * 255;
		float fl2 = (float) Math.random() * 255;
		float fl3 = (float) Math.random() * 255;
		float fl4 = (float) Math.random() * 255;
		
		colourBase.setARGB(fl1, fl2, fl3, fl4);
		
		Assert.assertTrue(colourBase.getR() == fl2 && colourBase.getG() == fl3
				&& colourBase.getB() == fl4 && colourBase.getA() == fl1);
	}

	@Test
	public void testGetR() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		Assert.assertEquals(int2, colourBase.getR(), int2/1000);
	}

	@Test
	public void testGetG() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		Assert.assertEquals(int3, colourBase.getG(), int3/1000);
	}

	@Test
	public void testGetB() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		Assert.assertEquals(int4, colourBase.getB(), int4/1000);
	}

	@Test
	public void testGetA() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		Assert.assertEquals(int1, colourBase.getA(), int1/1000);
	}

	@Test
	public void testSameAs() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(colourBase);
		
		colourBase.setA(colourBase.getA() - 1);
		colourBase.setA(colourBase.getA() + 1);
		
		System.out.println(colourBase == colourBase2);
		Assert.assertTrue(colourBase.sameAs(colourBase2));
	}

	@Test
	public void testIsSameRGB() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase((int) (Math.random()*255), int2,
				int3, int4);
		Assert.assertTrue(colourBase.isSameRGB(colourBase2));
	}

	@Test
	public void testIsSameAlpha() {
		int int1 = (int) (Math.random() * 255);
		int int2 = (int) (Math.random() * 255);
		int int3 = (int) (Math.random() * 255);
		int int4 = (int) (Math.random() * 255);
		
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(int1, (int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255));
		Assert.assertTrue(colourBase.isSameAlpha(colourBase2));
	}

	@Test
	public void testGetBitsArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBitsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIntARGB() {
		fail("Not yet implemented");
	}

}
