package loltest;

public class TestMethods {
	
	public static void main(String[] args){
		
		int a = 1;
		int b = a;
		
		a = a + 1;
		
		System.out.println(a==b);
		
		TestObj x = new TestObj(1); 
		TestObj y = x;
		
		x.val = x.val + 1;
		
		System.out.println(x==y);
		System.out.println(x.val == y.val);
		
	}
	
}
