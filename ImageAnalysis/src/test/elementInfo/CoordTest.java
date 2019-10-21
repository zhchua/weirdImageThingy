package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Angle;
import main.elementInfo.Coord;

public class CoordTest{

	public Coord cd;
	
	@Before
	public void setUp() throws Exception {
		
		cd = new Coord(1,1);
	}

	@Test
	public void testCoordIntInt() {
		assertTrue( "whats this for", cd.getX() == 1 && cd.getY() == 1);
	}

	@Test
	public void testCoordCoordBase() {
		Coord ncd = new Coord(cd);
		assertTrue(null, cd.sameAs(ncd) && cd!=ncd);
	}

	@Test
	public void testIsLeftOf() {
		Coord lcd = new Coord(0,1);
		assertTrue(lcd.isLeftOf(cd));
	}

	@Test
	public void testIsRightOf() {
		Coord rcd = new Coord(2,1);
		assertTrue(rcd.isRightOf(cd));
	}

	@Test
	public void testIsAbove() {
		Coord ucd = new Coord(1,0);
		assertTrue(ucd.isAbove(cd));
	}

	@Test
	public void testIsBelow() {
		Coord dcd = new Coord(1,2);
		assertTrue(dcd.isBelow(cd));
	}

	@Test
	public void testGetYDispTo() {
		Coord ycd = new Coord( 420, 69);
		assertEquals(68, cd.getYDispTo(ycd));
	}

	@Test
	public void testGetXDispTo() {
		Coord xcd = new Coord(1337, 42069);
		assertEquals(1336, cd.getXDispTo(xcd));
	}

	@Test
	public void testGetDistanceTo() {
		Coord xycd = new Coord(11,11);
		assertEquals(14.1421356237309, cd.getDistanceBetween(xycd), 0.0001);
	}
	
	@Test
	public void testGetAngleTo() {
		Coord acd = new Coord(0,0);
		assertEquals(315, cd.getAngleTo(acd).getValue(), 0.0001);
	}

	@Test
	public void testIsApproxAngleTo() {
		Coord acd = new Coord(1,0);
		// 2,0 45
		assertTrue(cd.isApproxAngleTo(acd, new Angle(0)));
	}

	@Test
	public void testIsAdjacentTo() {
		Coord adjcd = new Coord(2,1);
		assertTrue(cd.isAdjacentTo(adjcd));
	}

	@Test
	public void testIsImageEdge() {
		fail("Not yet implemented"); // TODO
	}

}
