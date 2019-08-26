package loltest;

import java.io.File;
import java.io.IOException;

public class Testmain {
	
	public static void main(String[] args) throws IOException{
		printImageFileInfo();
	}

	public static void printImageFileInfo() throws IOException{
		ImageFile imgF = new ImageFile("D:\\image\\test1x1black.png");
		imgF.loadFile();
		imgF.readFileToImage();
		imgF.generateWidthHeight();
		imgF.generateImageObj();
		ImageFile imgF2 = new ImageFile("D:\\image\\savetest.png");
		imgF2.setImageObj(imgF.getImageObj());
		imgF2.generateImageFromImageObj(imgF2.getImageObj());
		imgF2.saveFile();
		
		for(int i = 0; i < imgF.getImageObj().getPixArray().size(); i++){
			System.out.println("\n");
			System.out.println(imgF.getImage().getRGB(0, 0));
			System.out.println(imgF2.getImage().getRGB(0, 0));
			System.out.println("\n");
			System.out.println(Integer.toBinaryString(imgF.getImage().getRGB(0, 0)));
			System.out.println(Integer.toBinaryString(imgF2.getImage().getRGB(0, 0)));
		
			System.out.println(imgF.getColoursAsBits(imgF.getImageObj().getPixArray().get(i)));
			System.out.println(imgF2.getColoursAsBits(imgF.getImageObj().getPixArray().get(i)));
		}

//			
//		
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getCoord().
//					getX());
//			System.out.print("    ");
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getCoord().
//					getY());
//			System.out.print("        ");
//			
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
//					getA());
//			System.out.print("    ");
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
//					getR());
//			System.out.print("    ");
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
//					getG());
//			System.out.print("    ");
//			System.out.print(imgF.getImageObj().getPixArray().get(i).getColour().
//					getB());
//			System.out.print("        ");
//			
//			System.out.print(imgF2.getImageObj().getPixArray().get(i).getColour().
//					getA());
//			System.out.print("    ");
//			System.out.print(imgF2.getImageObj().getPixArray().get(i).getColour().
//					getR());
//			System.out.print("    ");
//			System.out.print(imgF2.getImageObj().getPixArray().get(i).getColour().
//					getG());
//			System.out.print("    ");
//			System.out.print(imgF2.getImageObj().getPixArray().get(i).getColour().
//					getB());
//			System.out.print("\n");
	}
}

