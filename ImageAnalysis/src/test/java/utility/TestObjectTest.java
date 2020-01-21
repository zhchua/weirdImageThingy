package utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class TestObjectTest {

	int[] inArr = {3, 5, 7, 11, 13, 17, 19};
	List<Integer> inLst = new ArrayList<Integer>();
	String str = "wololo";
	char chr = 'c';
	
	@Test
	public void test() {
		System.out.println(inArr.getClass().isArray());
		System.out.println(inArr.getClass().getSimpleName());
		System.out.println(inLst.getClass());
		System.out.println(str.getClass().equals(String.class));
		System.out.println(str.getClass().getSimpleName());
		try {
			System.out.println(this.getClass().getDeclaredField("chr").get(this).getClass().getSuperclass());
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fail("test()");
	}
	
	public String tmeth(){
		String str2 = this.str;
		str2 = "trololo";
		//str2 = null;
		return str2;
	}
	
	@Test
	public void test2(){
		String str2 = tmeth();
		System.out.println(str2);
		System.out.println(this.str);
		System.out.println(str2.getClass().equals(str.getClass()));
		System.out.println(inLst instanceof Collection);
		fail("test2()");
	}

}
