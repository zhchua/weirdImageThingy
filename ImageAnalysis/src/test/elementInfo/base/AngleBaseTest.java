package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import main.elementInfo.base.AngleBase;

public class AngleBaseTest {

	@Test
	public void testAngleBaseFloat() {
		float flangle = (float) Math.random();
		AngleBase angleBase = new AngleBase(flangle);
		Assert.assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}
	
	@Test
	public void testAngleBaseDouble() {
		double flangle = Math.random();
		AngleBase angleBase = new AngleBase(flangle);
		Assert.assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testAngleBaseAngleBase() {
		float flangle = (float) Math.random();
		AngleBase angleBase = new AngleBase(flangle);
		AngleBase angleBase2 = new AngleBase(angleBase);
		angleBase.setValue((float) Math.random());
		Assert.assertNotEquals(angleBase.getValue(), angleBase2.getValue());
	}

	@Test
	public void testGetValue() {
		float flangle = (float) Math.random();
		AngleBase angleBase = new AngleBase(flangle);
		Assert.assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testSetValue() {
		double flangle = Math.random();
		AngleBase angleBase = new AngleBase(Math.random());
		angleBase.setValue(flangle);
		Assert.assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testInRadians() {
		AngleBase angleBase = new AngleBase(180);
		Assert.assertEquals((float) Math.PI, angleBase.inRadians()
				, (float) Math.PI/1000);
	}

	@Test
	public void testSameAs() {
		double flangle = Math.random();
		AngleBase angleBase = new AngleBase(flangle);
		AngleBase angleBase2 = new AngleBase(flangle - 1);
		angleBase2.setValue(angleBase2.getValue()+1);
		Assert.assertTrue(angleBase.sameAs(angleBase2));
	}
}
