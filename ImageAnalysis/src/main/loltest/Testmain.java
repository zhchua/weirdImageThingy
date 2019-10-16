package loltest;

import java.io.File;
import java.io.IOException;

public class Testmain {
	
	public static void main(String[] args) throws IOException{
		Colour clr1 = new Colour(10,10,10);
		Colour clr2 = new Colour(clr1);
		Colour clr3 = clr1;
		
		System.out.println("Original: clr1:" + clr1.getR());
		System.out.println("Original: clr2:" + clr2.getR());
		System.out.println("Original: clr3:" + clr3.getR());
		
		change(clr1);
		System.out.println("After: clr1:" + clr1.getR());
		System.out.println("After: clr2:" + clr2.getR());
		//System.out.println("Is clr1 == clr2 now: " + clr1.sameAs(clr2));
		System.out.println("After clr3:" + clr3.getR());
	}
	
	public static void change(Colour clr2){
		System.out.println("Set clr1 to 20,20,20");
		clr2.setRGB(20, 20, 20);
	}
}

