package utility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BaseObjectTest {

	private TestObject to;
	private TestObject2 to2;
	private TestSubObject tso;
	private TestOutObject too;
	
	@Before
	public void setUp() throws Exception {
		to = new TestObject();
		to2 = new TestObject2();
		tso = new TestSubObject(to);
		too = new TestOutObject();
	}

	@Test
	public void testHashCode() {
		TestObject to_2 = new TestObject();
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertEquals(to.hashCode(), to_2.hashCode());
	}
	
	@Test
	public void testHashCode_alteredInt() {
		TestObject to_2 = new TestObject(11);
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertNotEquals(to.hashCode(), to_2.hashCode());
	}

	@Test
	public void testBaseObject() {
		TestObject to_2 = new TestObject();
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertTrue(to.equals(to_2));
	}

	@Test
	public void testBaseObjectObject() {
		TestObject to_2 = new TestObject(to);
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		System.out.println("Deepcopy constr shdbeFalse: " + (to==to_2));
		assertTrue(to.equals(to_2));
	}
	
	@Test
	public void testBaseObjectObject_alterInt() {
		TestObject to_2 = new TestObject(11);
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertFalse(to.equals(to_2));
	}
	
	@Test
	public void testBaseObjectObject_alterDbl() {
		TestObject to_2 = new TestObject(13.13);
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertFalse(to.equals(to_2));
	}
	
	@Test
	public void testBaseObjectObject_alterStr() {
		TestObject to_2 = new TestObject("quack");
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertFalse(to.equals(to_2));
	}
	
	@Test
	public void testBaseObjectObject_alterObj() {
		TestObject to_2 = new TestObject();
		to_2.setTo2(new TestObject2(17));
		System.out.println(to.hashCode() + " " + to_2.hashCode());
		assertFalse(to.equals(to_2));
	}

	@Test
	public void testFlEq() {
		fail("Not yet implemented"); // TODO
	}
}
