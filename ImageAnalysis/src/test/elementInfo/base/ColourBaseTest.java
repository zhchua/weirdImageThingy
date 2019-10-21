package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.elementInfo.base.ColourBase;

public class ColourBaseTest {
	private double fl1;
	private double fl2;
	private double fl3;
	private double fl4;
	
	private int int1;
	private int int2;
	private int int3;
	private int int4;
	
	@Before
	public void setUp() throws Exception {
		fl1 =  Math.random() * 255;
		fl2 =  Math.random() * 255;
		fl3 =  Math.random() * 255;
		fl4 =  Math.random() * 255;
		int1 = (int) (Math.random() * 255);
		int2 = (int) (Math.random() * 255);
		int3 = (int) (Math.random() * 255);
		int4 = (int) (Math.random() * 255);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testColourBaseFloatFloatFloatFloat() {
		ColourBase colourBase = new ColourBase(fl1, fl2, fl3, fl4);
		assertTrue(colourBase.getA() == fl1 && colourBase.getR() == fl2
				&& colourBase.getG() == fl3 && colourBase.getB() == fl4);
	}

	@Test
	public void testColourBaseIntIntIntInt() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		assertTrue(colourBase.getA() == int1 && colourBase.getR() == int2
				&& colourBase.getG() == int3 && colourBase.getB() == int4);
	}

	@Test
	public void testColourBaseFloatFloatFloat() {
		ColourBase colourBase = new ColourBase(fl1, fl2, fl3);
		assertTrue(colourBase.getA() == 0 && colourBase.getR() == fl1
				&& colourBase.getG() == fl2 && colourBase.getB() == fl3);
	}

	@Test
	public void testColourBaseIntIntInt() {
		ColourBase colourBase = new ColourBase(int1, int2, int3);
		assertTrue(colourBase.getA() == 0 && colourBase.getR() == int1
				&& colourBase.getG() == int2 && colourBase.getB() == int3);
	}

	@Test
	public void testColourBaseColourBase() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(colourBase);
		colourBase.setA((int) (Math.random() * 255));
		System.out.println(colourBase.getA());
		System.out.println(colourBase2.getA());
		assertNotEquals(colourBase.getA(), colourBase2.getA());
	}

	@Test
	public void testSetR() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setR(int4 - 1);
		assertEquals(int4-1, colourBase.getR(), (int4-1)/1000);
	}

	@Test
	public void testSetG() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setG(int4 - 1);
		assertEquals(int4-1, colourBase.getG(), (int4-1)/1000);
	}

	@Test
	public void testSetB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setB(int4 - 1);
		assertEquals(int4-1, colourBase.getB(), (int4-1)/1000);
	}

	@Test
	public void testSetA() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setA(int4 - 1);
		assertEquals(int4-1, colourBase.getA(), (int4-1)/1000);
	}

	@Test
	public void testSetRGB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setRGB(fl1, fl2, fl3);
		
		assertTrue(colourBase.getR() == fl1 && colourBase.getG() == fl2
				&& colourBase.getB() == fl3);
	}

	@Test
	public void testSetARGB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setARGB(fl1, fl2, fl3, fl4);
		
		assertTrue(colourBase.getR() == fl2 && colourBase.getG() == fl3
				&& colourBase.getB() == fl4 && colourBase.getA() == fl1);
	}

	@Test
	public void testGetR() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		assertEquals(int2, colourBase.getR(), int2/1000);
	}

	@Test
	public void testGetG() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		assertEquals(int3, colourBase.getG(), int3/1000);
	}

	@Test
	public void testGetB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		assertEquals(int4, colourBase.getB(), int4/1000);
	}

	@Test
	public void testGetA() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		
		assertEquals(int1, colourBase.getA(), int1/1000);
	}

	@Test
	public void testSameAs() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(colourBase);
		
		colourBase.setA(colourBase.getA() - 1);
		colourBase.setA(colourBase.getA() + 1);
		
		System.out.println(colourBase == colourBase2);
		assertTrue(colourBase.sameAs(colourBase2));
	}

	@Test
	public void testIsSameRGB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase((int) (Math.random()*255), int2,
				int3, int4);
		assertTrue(colourBase.isSameRGB(colourBase2));
	}

	@Test
	public void testIsSameAlpha() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		ColourBase colourBase2 = new ColourBase(int1, (int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255));
		assertTrue(colourBase.isSameAlpha(colourBase2));
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
