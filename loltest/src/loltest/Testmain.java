package loltest;

import java.io.File;
import java.io.IOException;

public class Testmain {
	
	public static void main(String[] args) throws IOException{
		Colour clr1 = new Colour(10,10,10);
		Colour clr2 = clr1;
		
		System.out.println(clr1.getR());
		System.out.println(clr2.getR());
		
		change(clr2);
		
		System.out.println(clr1.getR());
		System.out.println(clr2.getR());
		System.out.println(clr1.sameAs(clr2));
	}
	
	public static void change(Colour clr2){
		clr2.setRGB(20, 20, 20);
	}
}

