package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.elementInfo.base.CoordBase;

public class CoordBaseTest {
	
	private int in1;
	private int in2;
	
	private CoordBase cb1;
	
	@Before
	public void setUp() throws Exception {
		in1 = (int) (Math.random()*10000);
		in2 = (int) (Math.random()*10000);
		cb1 = new CoordBase(in1, in2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCoordBaseIntInt() {
		assertTrue(cb1.getX() == in1 && cb1.getY() == in2);
	}

	@Test
	public void testCoordBaseCoordBase() {
		CoordBase cb2 = new CoordBase(cb1);
		assertTrue(cb1.sameAs(cb2));
	}

	@Test
	public void testGetX() {
		assertEquals(in1, cb1.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(in2, cb1.getY());
	}

	@Test
	public void testSetX() {
		cb1.setX(1337);
		assertEquals(1337, cb1.getX());
	}

	@Test
	public void testSetY() {
		cb1.setY(42069);
		assertEquals(42069, cb1.getY());
	}

	@Test
	public void testSetCoord() {
		cb1.setCoord(42069, 1337);
		assertTrue(cb1.getX() == 42069 && cb1.getY() == 1337);
	}

	@Test
	public void testSameAs() {
		CoordBase cb2 = new CoordBase(cb1);
		cb1.setX(cb1.getX()-1);
		cb1.setX(cb1.getX()+1);
		assertTrue(cb1.sameAs(cb2));
	}

}
