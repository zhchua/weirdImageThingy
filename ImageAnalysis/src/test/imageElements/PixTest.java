package test.imageElements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Colour;
import main.elementInfo.Coord;
import main.imageElements.Pix;

public class PixTest {

	private Colour col;
	private Coord cod;
	private Pix px;
	
	@Before
	public void setUp() throws Exception {
		col = new Colour(1,2,3,4);
		cod = new Coord(5,6);
		px = new Pix(col, cod);
	}

	@Test
	public void testPixColourCoord() {
		assertTrue(px.getColour() == col && px.getCoord() == cod);
	}

	@Test
	public void testPixPix() {
		Pix npx = new Pix(px);
		assertTrue(npx != px 
				&& npx.getColour().sameAs(col) && npx.getCoord().sameAs(cod));
	}

	@Test
	public void testIsSameColourAs() {
		Pix npx = new Pix(new Colour(1,2,3,4), new Coord(9,9));
		assertTrue(px.isSameColourAs(npx));
	}

	@Test
	public void testIsSameAlphaAs() {
		Pix npx = new Pix(new Colour(1,22,33,44), new Coord(98,96));
		assertTrue(px.isSameAlphaAs(npx));
	}

	@Test
	public void testIsSameARGB() {
		Pix npx = new Pix(new Colour(1,2,3,4), new Coord(99,99));
		assertTrue(px.isSameColourAs(npx));
	}

	@Test
	public void testIsAdjacentTo() {
		Pix npx = new Pix(new Colour(1,2,3,4), new Coord(6,5));
		assertTrue(px.isSameColourAs(npx));
	}

	@Test
	public void testIsImageEdge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testShouldbeSameShapeAs() {
		Pix npx = new Pix(new Colour(1,2,3,4), new Coord(6,5));
		assertTrue(px.shouldbeSameShapeAs(npx));
	}

	@Test
	public void testIsAtCoord() {
		assertTrue(px.isAtCoord( new Coord(5,6)));
	}

	@Test
	public void testIsInShape() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetShape() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsInShapes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsShapeEdge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsAdjacentToSameColourPix() {
		fail("Not yet implemented"); // TODO
	}

}
