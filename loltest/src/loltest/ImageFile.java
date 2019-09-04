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
	
	/** Create ImageFile from file at given pathname.
	 * 
	 * @param pathName
	 * @throws IOException 
	 */
	public ImageFile(String pathName) throws IOException{
		this.pathname = pathName;
		this.file = new File(this.pathname);
		this.image = ImageIO.read(this.file);
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		generateImageObj();
	}
	
	/** Create ImageFile from given ImageObj.
	 * 
	 * @param imageObj
	 */
	public ImageFile(ImageObj imageObj){
		this.imageObj = imageObj;
		this.width = imageObj.getWidth();
		this.height = imageObj.getHeight();
	}
	
	private void _____GETTERS_AND_SETTERS_____(){}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void setImageObj(ImageObj imageObj){
		this.imageObj = imageObj;
	}
	
	/**	Generates data for a single pix using data from BufferedImage pixel.
	 * 
	 * @param colour	integer colour (NOT COLOUR CLASS!) in ARGB form.
	 * @param x
	 * @param y
	 */
	private void generatePixForImageObj(int colour, int x, int y){
		float a = (float) ((colour>>24) & 0xff);
		float r = (float) ((colour>>16) & 0xff);
		float g = (float) ((colour>>8) & 0xff);
		float b = (float) (colour & 0xff);
	
		imageObj.getPixList().add(new Pix( new Colour(a,r,g,b), new Coord(x,y)));
	}
	
	/** Traverses image object to populate imageObj, pixelwise.
	 * 
	 */
	private void generateImageObjDetailsFromImage(){
		for(int checkX = 0; checkX < this.getWidth(); checkX++){
			for(int checkY = 0; checkY < this.getHeight(); checkY++){
				generatePixForImageObj(image.getRGB(checkX, checkY), checkX, checkY);
			}
		}
	}
	
	/** Creates empty ImageObj and calls generateImageObjDetailsFromImage
	 * to generate the ImageObj's data from Image.
	 * 
	 */
	private void generateImageObj(){
		if(file == null){
			System.out.println("File does not Exist");
			return;
		}
		else this.imageObj = new ImageObj(this.width, this.height);
		generateImageObjDetailsFromImage();
	}
	
	public ImageObj getImageObj(){
		if(this.imageObj == null){
			generateImageObj();
		}
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
	
	public void setFile(File file){
		this.file = file;
	}
	
	public File getFile(){
		return this.file;
	}
	
	/** Sets a pixel of the Image object attribute of this ImageFile 
	 * using the colour and coord from the given Pix
	 * 
	 * @param pix
	 */
	private void setImagePixByImageObjPix(Pix pix){
/*		
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), rgb);*/
		
		int argb = pix.getColour().getIntARGB();
		
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), argb);
	}
	
	/** Generates Image for this.image using the ImageObj object attribute of this
	 * ImageFile.
	 * 
	 * @param imageObj
	 */
	private void generateImageFromImageObj(){
		if(this.imageObj != null){
			this.image = new BufferedImage(imageObj.getWidth(), imageObj.getHeight()
					, BufferedImage.TYPE_INT_ARGB);
			for(int pixInx = 0; pixInx < imageObj.getPixList().size(); pixInx++){
				setImagePixByImageObjPix(imageObj.getPixList().get(pixInx));
			}
		}
	}
	
	/** Gets Image object attribute of this ImageFile. 
	 * If this.image does not exist, will attempt to generate new Image from
	 * ImageObj.
	 * 
	 * @return
	 */
	public BufferedImage getImage(){
		if(this.image == null){
			generateImageFromImageObj();
		}
		return this.image;
	}
	
	private void __________METHODS__________(){}
	
	/**	Saves this ImageFile to an actual file on the local system,
	 *  at the given path, with the given name at the end of said path. 
	 *  
	 *  If pathName parameter is null or "", will save to this.pathName instead.
	 *  
	 * <pre> Example: "D:\\Image\\Output.png" </pre> 
	 * saves to Output.png in directory D:\Image.
	 * 
	 * @throws IOException
	 */
	public void saveFile(String pathName) throws IOException{
		if(pathName == null || pathName == ""){
			if(this.pathname != null){
				File file = new File(this.pathname);
				ImageIO.write(this.getImage(), "png", file);
			}
			else{
				System.out.println("No valid Path+Name specified, "
						+ "unable to save.");
			}
		}
		else{
		    File file = new File(pathName);
		    ImageIO.write(this.getImage(), "png", file);
		}
	}
}
