package loltest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

public class ImageFile {

	private File file;
	private String pathname;
	private BufferedImage image;
	private ImageObj imageObj;
	private int width;
	private int height;
	
	public ImageFile(String name){
		setName(name);
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return this.image;
	}
	
	public void setImageObj(ImageObj imageObj){
		this.imageObj = imageObj;
	}
	
	public ImageObj getImageObj(){
		return this.imageObj;
	}
	
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void setName(String pathname){
		this.pathname = pathname;
	}
	
	public String getName(){
		return this.pathname;
	}
	
	/**	Loads file attribute from pathname.
	 * 
	 */
	public void loadFile(){
		this.file = new File(this.pathname);
	}
	
	/**	Saves file. Requires an existing image attribute.
	 * 
	 * @throws IOException
	 */
	public void saveFile() throws IOException{
	       this.file = new File(pathname);
	       ImageIO.write(this.image, "png", this.file);
	}
	
	/** Reads existing image attribute to set width and height.
	 * 
	 */
	public void generateWidthHeight(){
		setWidth(this.image.getWidth());
		setHeight(this.image.getHeight()); 
	}
	
	/**	Reads known File attribute into image attribute.
	 * 
	 * @throws IOException
	 */
	public void readFileToImage() throws IOException{
		this.image = ImageIO.read(this.file);
	}
	
	/**	Generates data for a single pix using data from BufferedImage pixel.
	 * 
	 * @param colour	integer colour (NOT COLOUR CLASS!) in ARGB form.
	 * @param x
	 * @param y
	 */
	public void generatePixForImageObj(int colour, int x, int y){
		float a = (float) ((colour>>24) & 0xff);
		float r = (float) ((colour>>16) & 0xff);
		float g = (float) ((colour>>8) & 0xff);
		float b = (float) (colour & 0xff);
	
		imageObj.addPix(new Pix( new Colour(a,r,g,b), new Coord(x,y)));
	}
	
	/** Traverses image object to populate imageObj, pixelwise.
	 * 
	 */
	public void generateImageObjDetailsFromImage(){
		for(int checkX = 0; checkX < this.getWidth(); checkX++){
			for(int checkY = 0; checkY < this.getHeight(); checkY++){
				generatePixForImageObj(image.getRGB(checkX, checkY), checkX, checkY);
			}
		}
	}
	
	/** Void function to create new imageObj 
	 * and generate the imageObj's data from image.
	 * 
	 */
	public void generateImageObj(){
		if(file == null){
			System.out.println("File does not Exist");
			return;
		}
		else this.imageObj = new ImageObj(this.width, this.height);
		generateImageObjDetailsFromImage();
	}
	
	/** Returns the integer of n to the power of p. Integers only.
	 * 
	 * @param n
	 * @param p
	 * @return
	 */
	public int pow(int n, int p){
		// cant believe i needed to write this
		if(p == 0){
			return 1;
		}
		if(p == 1){
			return n;
		}
		int prod = n;
		for(int i = 2; i <= p; i++){
			prod = prod*n;
		}
		return prod;
	}
	
	/** Get an ArrayList representing the bit pattern of an integer
	 * 
	 * @param num
	 * @return
	 */
	public ArrayList<Integer> getPower(Pix pix){
		ArrayList<Integer> bits = new ArrayList<Integer>();
		int col = 0;
		int[] colours = {(int) pix.getColour().getA()
				,(int) pix.getColour().getR()
				,(int) pix.getColour().getG()
				,(int) pix.getColour().getB()};
		
		// 128, 64, 32, 16, 8, 4, 2, 1
		for(int clrInx = 0 ; clrInx < colours.length; clrInx++){
			col = colours[clrInx];
			for( int i = 7; i >= 0 ; i--){
				if(col / pow(2,i) >= 1){
					bits.add(1);
					col = col - pow(2,i);
				}
				else bits.add(0);
			}
		}
		return bits;
	}
	
	/** Get an ArrayList representing the bit pattern of the 32-bit integer colours
	 * 
	 * @param a
	 * @return
	 */
	public ArrayList<Integer> getColoursAsBits(Pix pix){
		// ffs this is a dumb idea
		return getPower(pix);
	}
	
	/** Sets a pixel of imageFile using info (colour, coord) from a pix of imageObj
	 * 
	 * @param pix
	 */
	public void setImageFilePixByImageObjPix(Pix pix){
/*		
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), rgb);*/
		
		StringBuilder argbStrBld = new StringBuilder();
		for(int i = 0; i < getPower(pix).size(); i++){
			argbStrBld.append(getPower(pix).get(i));
		}
		String argbStr = argbStrBld.toString();
		//int argb = Integer.parseInt(argbStr, 2);
		int argb = (int) Long.parseLong(argbStr, 2);
		//(int)Long.parseLong(s, 2)
		
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), argb);

	}
	
	/** Populates a new imageFile's pixels with pixel data from imageObj
	 * 
	 * @param imageObj
	 */
	public void generateImageFromImageObj(ImageObj imageObj){
		this.image = new BufferedImage(imageObj.getWidth(), imageObj.getHeight()
				, BufferedImage.TYPE_INT_ARGB);
		for(int pixInx = 0; pixInx < imageObj.getPixArray().size(); pixInx++){
			setImageFilePixByImageObjPix(imageObj.getPixArray().get(pixInx));
		}
	}
	
}
