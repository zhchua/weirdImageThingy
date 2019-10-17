package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Coord;
import test.elementInfo.base.CoordBaseTest;

public class CoordTest{

	public Coord cd;
	
	@Before
	public void setUp() throws Exception {
		
		cd = new Coord(1,1);
	}

	@Test
	public void testCoordIntInt() {
		Assert.assertTrue( "whats this for", cd.getX() == 1 && cd.getY() == 1);
	}

	@Test
	public void testCoordCoordBase() {
		Coord ncd = new Coord(cd);
		Assert.assertTrue(null, cd.sameAs(ncd) && cd!=ncd);
	}

	@Test
	public void testIsLeftOf() {
		Coord lcd = new Coord(0,1);
		Assert.assertTrue(lcd.isLeftOf(cd));
	}

	@Test
	public void testIsRightOf() {
		Coord rcd = new Coord(2,1);
		Assert.assertTrue(rcd.isRightOf(cd));
	}

	@Test
	public void testIsAbove() {
		Coord ucd = new Coord(1,0);
		Assert.assertTrue(ucd.isAbove(cd));
	}

	@Test
	public void testIsBelow() {
		Coord dcd = new Coord(1,2);
		Assert.assertTrue(dcd.isBelow(cd));
	}

	@Test
	public void testGetYDispTo() {
		Coord ycd = new Coord( 420, 69);
		Assert.assertEquals(68, cd.getYDispTo(ycd));
	}

	@Test
	public void testGetXDispTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetDistanceTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAngleTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsApproxAngleTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsAdjacentTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsImageEdge() {
		fail("Not yet implemented"); // TODO
	}

}
