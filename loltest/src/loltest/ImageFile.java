package loltest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	
	public void loadFile(){
		this.file = new File(this.pathname);
	}
	
	public void saveFile() throws IOException{
		try {
			this.file.createNewFile();
		} catch(Exception e){
			System.out.println("Unable to create new file");
		}
	}
	
	public void generateWidthHeight(){
		setWidth(this.image.getWidth());
		setHeight(this.image.getHeight()); 
	}
	
	public void readFileToImage() throws IOException{
		this.image = ImageIO.read(this.file);
	}
	
	public void generatePixForImageObj(int colour, int x, int y){
		float a = (float) ((colour>>24) & 0xff);
		float r = (float) ((colour>>16) & 0xff);
		float g = (float) ((colour>>8) & 0xff);
		float b = (float) (colour & 0xff);
	
		imageObj.setPixIntoPixArray(new Pix( new Colour(a,r,g,b), new Coord(x,y)));
	}
	
	public void generateImageObjDetailsFromImage(){
		for(int checkX = 0; checkX < this.getWidth(); checkX++){
			for(int checkY = 0; checkY < this.getHeight(); checkY++){
				generatePixForImageObj(image.getRGB(checkX, checkY), checkX, checkY);
			}
		}
	}
	
	public void generateImageObj(){
		if(file == null){
			System.out.println("File does not Exist");
			return;
		}
		else this.imageObj = new ImageObj(this.width, this.height);
		generateImageObjDetailsFromImage();
	}
	
	// sets imagefile pixel by pixel using info from imageobj pix
	public void setImageFilePixByImageObjPix(Pix pix){
		int rgb = ((int) pix.getColour().getA() <<24) | 
				((int) pix.getColour().getR()<<16) | 
				((int) pix.getColour().getG()<<8) | (int) pix.getColour().getB();
		this.image.setRGB(pix.getCoord().getX(), pix.getCoord().getY(), rgb);
	}
	
	// populates a new imagefile with data from imageobj
	public void generateImageFromImageObj(ImageObj imageObj){
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for(int pixInx = 0; pixInx < imageObj.getPixArray().size(); pixInx++){
			setImageFilePixByImageObjPix(imageObj.getPixArray().get(pixInx));
		}
	}
	
}
