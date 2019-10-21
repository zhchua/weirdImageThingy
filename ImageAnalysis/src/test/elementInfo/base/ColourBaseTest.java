package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.elementInfo.base.ColourBase;
import main.exceptions.NullConstructorException;
import test.utility.BaseTestObject;

public class ColourBaseTest extends BaseTestObject {
	private double db1;
	private double db2;
	private double db3;
	private double db4;
	
	private int int1;
	private int int2;
	private int int3;
	private int int4;
	
	private ColourBase cb;
	
	@Before
	public void setUp() throws Exception {
		db1 =  Math.random() * 255;
		db2 =  Math.random() * 255;
		db3 =  Math.random() * 255;
		db4 =  Math.random() * 255;
		int1 = (int) (Math.random() * 255);
		int2 = (int) (Math.random() * 255);
		int3 = (int) (Math.random() * 255);
		int4 = (int) (Math.random() * 255);
		
		cb = new ColourBase(db1,db2,db3,db4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructARGB_Double() {
		assertTrue(cb.getA() == db1 && cb.getR() == db2
				&& cb.getG() == db3 && cb.getB() == db4);
	}

	@Test
	public void testConstructARGB_Int() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		assertTrue(colourBase.getA() == int1 && colourBase.getR() == int2
				&& colourBase.getG() == int3 && colourBase.getB() == int4);
	}

	@Test
	public void testConstructRGB_Double() {
		ColourBase colourBase = new ColourBase(db1, db2, db3);
		assertTrue(colourBase.getA() == 0 && colourBase.getR() == db1
				&& colourBase.getG() == db2 && colourBase.getB() == db3);
	}

	@Test
	public void testConstructRGB_Int() {
		ColourBase colourBase = new ColourBase(int1, int2, int3);
		assertTrue(colourBase.getA() == 0 && colourBase.getR() == int1
				&& colourBase.getG() == int2 && colourBase.getB() == int3);
	}
	
	@Test
	public void testConstruct() {
		ColourBase colourBase2 = new ColourBase(cb);
		assertTrue(flEq(cb.getA(), colourBase2.getA()));
	}

	@Test
	public void testConstruct_Negative() {
		ColourBase colourBase = new ColourBase(db1, db2, db3, db4);
		ColourBase colourBase2 = new ColourBase(colourBase);
		colourBase.setA((int) (Math.random() * 255));
		assertNotEquals(colourBase.getA(), colourBase2.getA());
	}
	
	@SuppressWarnings("unused")
	@Test( expected = NullConstructorException.class)
	public void testConstruct_Null() {
		ColourBase colourBase = null;
		ColourBase colourBase2 = new ColourBase(colourBase);
	}

	@Test
	public void testSetR() {
		assertTrue(flEq(db2, cb.getR()));
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
		colourBase.setRGB(db1, db2, db3);
		
		assertTrue(colourBase.getR() == db1 && colourBase.getG() == db2
				&& colourBase.getB() == db3);
	}

	@Test
	public void testSetARGB() {
		ColourBase colourBase = new ColourBase(int1, int2, int3, int4);
		colourBase.setARGB(db1, db2, db3, db4);
		
		assertTrue(colourBase.getR() == db2 && colourBase.getG() == db3
				&& colourBase.getB() == db4 && colourBase.getA() == db1);
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
