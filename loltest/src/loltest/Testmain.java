package loltest;

import java.io.File;
import java.io.IOException;

public class Testmain {
	
	public static void main(String[] args) throws IOException{
		printImageFileInfo();
	}

	public static void printImageFileInfo() throws IOException{
		ImageFile imgF = new ImageFile("D:\\image\\test88white.png");
		imgF.loadFile();
		imgF.readFileToImage();
		imgF.generateWidthHeight();
		imgF.generateImageObj();
		for(int i = 0; i < imgF.getImageObj().getPixArray().size(); i++){
			
		
			System.out.print(imgF.getImageObj().getPixArray().get(i).getCoord().
					getX());
			System.out.print("    ");
			System.out.print(imgF.getImageObj().getPixArray().get(i).getCoord().
					getY());
			System.out.print("        ");
			
			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
					getA());
			System.out.print("    ");
			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
					getR());
			System.out.print("    ");
			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
					getG());
			System.out.print("    ");
			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
					getB());
			System.out.print("\n");
		}
	}
}
