package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.elementInfo.Angle;
import test.elementInfo.base.AngleBaseTest;

public class AngleTest extends AngleBaseTest {

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
		Assert.assertTrue(ang2.sameAs(angle) && ang2!=angle);
	}

	@Test
	public void testAngleDouble() {
		Assert.assertEquals(fl1, angle.getValue(), fl1/1000);
	}

	@Test
	public void testGetUnitXComponent_Left() {
		Angle angle = new Angle(270);
		Assert.assertEquals(-1, angle.getUnitXComponent(), 1/1000);
	}
	
	@Test
	public void testGetUnitXComponent_Right() {
		Angle angle = new Angle(90);
		Assert.assertEquals(1, angle.getUnitXComponent(), 1/1000);
	}
	
	@Test
	public void testGetUnitXComponent_None() {
		Angle angle = new Angle(180);
		Assert.assertEquals(0, angle.getUnitXComponent(), 1/1000);
	}

	@Test
	public void testGetYPerUnitX_Up() {
		Angle angle = new Angle(0);
		Assert.assertEquals(-1, angle.getYPerUnitX(), 1/1000);
	}
	
	@Test
	public void testGetYPerUnitX_None() {
		Angle angle = new Angle(90);
		Assert.assertEquals(0, angle.getYPerUnitX(), 1/1000);
	}
	
	@Test
	public void testGetYPerUnitX_SlantUp() {
		Angle angle = new Angle(45);
		Assert.assertEquals(-1, angle.getYPerUnitX(), 0.0001);
	}

	@Test
	public void testGetYPerUnitX_SlantDown() {
		Angle angle = new Angle(225);
		Assert.assertEquals(1, angle.getYPerUnitX(), 0.0001);
	}
	
	@Test
	public void testIsLeft() {
		Angle angle = new Angle(180 + (180 * Math.random()));
		Assert.assertTrue(angle.isLeft());
	}

	@Test
	public void testIsRight() {
		Angle angle = new Angle(180 * Math.random());
		Assert.assertTrue(angle.isRight());
	}

	@Test
	public void testIsUp_upright() {
		Angle angle = new Angle( 90 * Math.random());
		Assert.assertTrue(angle.isUp());
	}

	@Test
	public void testIsUp_upleft() {
		Angle angle = new Angle( 90 * Math.random());
		Assert.assertTrue(angle.isUp());
	}
	
	@Test
	public void testIsDown() {
		Angle angle = new Angle( 90 + (Math.random() * 180));
		Assert.assertTrue(angle.isDown());
	}

	@Test
	public void testRerangeAngle_normal() {
		Angle angle = new Angle(133);
		Assert.assertEquals(133, angle.rerangeAngle(133), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_posover() {
		Angle angle = new Angle(1337);
		Assert.assertEquals(257, angle.rerangeAngle(1337), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_neg() {
		Angle angle = new Angle(-69);
		Assert.assertEquals(291, angle.rerangeAngle(-69), 0.00001);
	}
	
	@Test
	public void testRerangeAngle_negover() {
		Angle angle = new Angle(-420);
		Assert.assertEquals(300, angle.rerangeAngle(-420), 0.00001);
	}

}
