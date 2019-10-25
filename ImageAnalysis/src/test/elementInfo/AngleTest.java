package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.elementInfo.Angle;
import test.elementInfo.base.AngleBaseTest;
import test.utility.BaseTestObject;

public class AngleTest extends BaseTestObject{

	double flNeg;
	double fl1;
	double flOver;
	Angle angle;
	
	@Before
	public void setUp() throws Exception {
		flNeg = Math.random() * (-3600);
		fl1 = Math.random() * 360;
		flOver = Math.random() * 360 + 360;
		angle = new Angle(fl1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAngleAngle() {
		Angle ang2 = new Angle(angle);
		assertTrue(ang2.equals(angle) && ang2!=angle);
	}

	@Test
	public void testAngleDouble() {
		assertEquals(fl1, angle.getValue(), fl1/1000);
	}

	@Test
	public void testGetUnitXComponent_Left() {
		Angle angle = new Angle(270);
		assertEquals(-1, angle.getUnitXComponent(), 1/1000);
	}
	
	@Test
	public void testGetUnitXComponent_Right() {
		Angle angle = new Angle(90);
		assertEquals(1, angle.getUnitXComponent(), 1/1000);
	}
	
	@Test
	public void testGetUnitXComponent_None() {
		Angle angle = new Angle(180);
		assertEquals(0, angle.getUnitXComponent(), 1/1000);
	}

	@Test
	public void testGetYPerUnitX_Up() {
		Angle angle = new Angle(0);
		assertEquals(-1, angle.getYPerUnitX(), 1/1000);
	}
	
	@Test
	public void testGetYPerUnitX_None() {
		Angle angle = new Angle(90);
		assertEquals(0, angle.getYPerUnitX(), 1/1000);
	}
	
	@Test
	public void testGetYPerUnitX_SlantUp() {
		Angle angle = new Angle(45);
		assertEquals(-1, angle.getYPerUnitX(), 0.0001);
	}

	@Test
	public void testGetYPerUnitX_SlantDown() {
		Angle angle = new Angle(225);
		assertEquals(1, angle.getYPerUnitX(), 0.0001);
	}
	
	@Test
	public void testIsLeft() {
		Angle angle = new Angle(180 + (180 * Math.random()));
		assertTrue(angle.isLeft());
	}

	@Test
	public void testIsRight() {
		Angle angle = new Angle(180 * Math.random());
		assertTrue(angle.isRight());
	}

	@Test
	public void testIsUp_upright() {
		Angle angle = new Angle( 90 * Math.random());
		assertTrue(angle.isUp());
	}

	@Test
	public void testIsUp_upleft() {
		Angle angle = new Angle( 90 * Math.random());
		assertTrue(angle.isUp());
	}
	
	@Test
	public void testIsDown() {
		Angle angle = new Angle( 90 + (Math.random() * 180));
		assertTrue(angle.isDown());
	}

	@Test
	public void testRerangeAngle_normal() {
		Angle angle = new Angle(133);
		assertEquals(133, angle.rerangeAngle(133), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_posover() {
		Angle angle = new Angle(1337);
		assertEquals(257, angle.rerangeAngle(1337), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_neg() {
		Angle angle = new Angle(-69);
		assertEquals(291, angle.rerangeAngle(-69), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_negover() {
		Angle angle = new Angle(-420);
		assertEquals(300, angle.rerangeAngle(-420), 0.00001);
	}

}
