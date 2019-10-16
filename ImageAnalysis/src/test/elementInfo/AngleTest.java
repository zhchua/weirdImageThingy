package test.elementInfo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.log4j.Logger;

import main.elementInfo.Angle;
import test.elementInfo.base.AngleBaseTest;

public class AngleTest extends AngleBaseTest {
	
	private final Logger log = Logger.getLogger("AngleTest");

	@Before
	public void setUp() throws Exception {
		log.debug("new");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAngleAngle() {
		
	}

	@Test
	public void testAngleFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUnitXComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYPerUnitX() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDown() {
		fail("Not yet implemented");
	}

	@Test
	public void testRerangeAngle() {
		fail("Not yet implemented");
	}

}
