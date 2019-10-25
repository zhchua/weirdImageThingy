package test.elementInfo.base;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementInfo.base.AngleBase;
import main.exceptions.NullConstructorException;

public class AngleBaseTest {
	
	private double flangle;
	private AngleBase angleBase;
	
	@Before
	public void setUp() throws Exception{
		flangle = Math.random();
		angleBase = new AngleBase(flangle);
	}
	
	
	@Test
	public void testAngleBase_Double() {
		assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testAngleBase_DeepCopy() {
		AngleBase angleBase2 = new AngleBase(angleBase);
		angleBase.setValue(Math.random());
		assertNotEquals(angleBase.getValue(), angleBase2.getValue());
	}
	
	@Test
	public void testAngleBase_DeepCopy_eqsgn() {
		AngleBase angleBase2 = new AngleBase(angleBase);
		assertFalse(angleBase == angleBase2);
	}
	
	@Test
	public void testAngleBase_DeepCopy_eq() {
		AngleBase angleBase2 = new AngleBase(angleBase);
		assertTrue(angleBase.equals(angleBase2));
	}

	@SuppressWarnings("unused")
	@Test(expected = NullConstructorException.class)
	public void testAngleBase_DeepCopy_Null() {
		AngleBase angleBase2 = null;
		AngleBase angleBase3 = new AngleBase(angleBase2);
	}
	
	@Test
	public void testGetValue() {
		assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testSetValue() {
		AngleBase angleBase = new AngleBase(Math.random());
		angleBase.setValue(flangle);
		assertEquals(flangle, angleBase.getValue(), flangle/1000);
	}

	@Test
	public void testInRadians() {
		AngleBase angleBase = new AngleBase(180);
		assertEquals(Math.PI, angleBase.inRadians()
				, Math.PI/1000);
	}

	@Test
	public void testSameAs() {
		AngleBase angleBase2 = new AngleBase(flangle - 1);
		angleBase2.setValue(angleBase2.getValue()+1);
		assertTrue(angleBase.equals(angleBase2));
	}
	
	@Test
	public void testSameAs_Negative() {
		AngleBase angleBase2 = new AngleBase(flangle - 1);
		assertFalse(angleBase.equals(angleBase2));
	}
	
	@Test
	public void testSameAs_Null() {
		AngleBase angleBase2 = null;
		assertFalse(angleBase.equals(angleBase2));
	}
}
