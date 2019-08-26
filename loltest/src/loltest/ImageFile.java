package loltest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
		imageObj.setPixIntoPixArray(new Pix( new Colour(a,r,g,b), new Coord(x,y)));
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
	public static int pow(int n, int p){
		// cant fucking believe i need to write this
		System.out.println("pow");
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
	public static ArrayList<Integer> getPower(int num){
		System.out.println("getPower");
		ArrayList<Integer> bits = new ArrayList<Integer>();
		// 128, 64, 32, 16, 8, 4, 2, 1
		for( int i = 7; i >= 0 ; i--){
			if(num / pow(2,i) >= 1){
				bits.add(1);
				num = num - pow(2,i);
			}
			else bits.add(0);
		}
		return bits;
	}
	
	/** Get an ArrayList representing the bit pattern of the Alpha Channel (8-bits)
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> getAinBitForm(int a){
		System.out.println("getAinBitForm");
		return getPower(a);
	}
	
	
	public static void main(String[] args){
		ArrayList<Integer> a = getAinBitForm(55);
		for(int i = 0; i<a.size(); i++){
			System.out.print(a.get(i));
		}
	}
	
	/** Sets a pixel of imageFile using info (colour, coord) from a pix of imageObj
	 * 
	 * @param pix
	 */
	public void setImageFilePixByImageObjPix(Pix pix){
/*		int rgb = ((int) pix.getColour().getA() <<24) | 
				((int) pix.getColour().getR()<<16) | 
				((int) pix.getColour().getG()<<8) | (int) pix.getColour().getB();*/
		int rgb = -1;
		System.out.println(Integer.toBinaryString(rgb));
		
		System.out.println(Integer.toBinaryString((int) pix.getColour().getA()));
		rgb = ((int)pix.getColour().getA() << 24) & rgb;
		System.out.println(Integer.toBinaryString(rgb));
	
		System.out.println(Integer.toBinaryString((int) pix.getColour().getR()));
		rgb = (rgb & ~(0xFF << 16)) | ((int)pix.getColour().getR() << 16);
		System.out.println(Integer.toBinaryString(rgb));
		
		System.out.println(Integer.toBinaryString((int) pix.getColour().getG()));
		rgb = (rgb & ~(0xFF << 8)) | ((int)pix.getColour().getG() << 8);
		System.out.println(Integer.toBinaryString(rgb));
		
		System.out.println(Integer.toBinaryString((int) pix.getColour().getB()));
		rgb = (rgb & ~(0xFF)) | ((int)pix.getColour().getB());
		System.out.println(Integer.toBinaryString(rgb));
		
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), rgb);
	}
	
	/** Populates a new imageFile's pixels with pixel data from imageObj
	 * 
	 * @param imageObj
	 */
	public void generateImageFromImageObj(ImageObj imageObj){
		this.image = new BufferedImage(imageObj.getWidth(), imageObj.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int pixInx = 0; pixInx < imageObj.getPixArray().size(); pixInx++){
			setImageFilePixByImageObjPix(imageObj.getPixArray().get(pixInx));
		}
	}
	
}
