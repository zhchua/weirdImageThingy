package test.imageElements.base;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Colour;
import main.elementInfo.Coord;
import main.imageElements.base.PixBase;

public class PixBaseTest {

	private Colour col = new Colour(1,2,3,4);
	private Coord cod = new Coord(5,6);
	
	private PixBase pb;
	
	@Before
	public void setUp() throws Exception {
		pb = new PixBase(col,cod);
		
	}

	@Test
	public void testPixBaseColourCoord() {
		assertTrue(pb.getColour() == col && pb.getCoord() == cod);
	}

	@Test
	public void testPixBasePixBase() {
		PixBase pb2 = new PixBase(pb);
		assertTrue(pb2.getColour() != col && pb2.getCoord() != cod
				&& pb2.getColour().sameAs(col) && pb2.getCoord().sameAs(cod));
	}

	@Test
	public void testGetColour() {
		assertEquals(col, pb.getColour());
	}

	@Test
	public void testSetColour() {
		Colour ncol = new Colour(255,254,253,252);
		pb.setColour(ncol);
		assertEquals(ncol, pb.getColour());
	}

	@Test
	public void testGetCoord() {
		assertEquals(cod, pb.getCoord());
	}

	@Test
	public void testSetCoord() {
		Coord ncod = new Coord(9,8);
		pb.setCoord(ncod);
		assertEquals(ncod, pb.getCoord());
	}

	@Test
	public void testSameAs() {
		PixBase pb2 = new PixBase(pb);
		assertTrue(pb.sameAs(pb2) && pb != pb2);
	}

}
