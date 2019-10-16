package loltest;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class CustomFile {
	
	private Logger logger;
	
	private ImageObj imageObj;
	
	private String pathName;
	
	public CustomFile(ImageObj imageObj, CopyDepth cd){
		if(cd == CopyDepth.STRICT || cd == CopyDepth.REFERENCE){
			this.imageObj = new ImageObj(imageObj, cd);
		}
		else{
			this.imageObj = imageObj;
		}
	}
	
	public CustomFile(String pathName){
		this.pathName = pathName;
	}
	
	private void _____GETTERS_AND_SETTERS_____(){}
	
	public void setPathName(String pathName){
		this.pathName = pathName;
	}
	
	public String getPathName(){
		return this.pathName;
	}
	
	public void setImageObj(ImageObj imageObj){
		this.imageObj = imageObj;
	}

	/** Generate Pixs for ImageObj from Individual Channel Values (ICV) files.
	 * Which should be 4 files of the same name but different extensions
	 * corresponding to each colour channel, e.g. .icva, .icvr, .icvg, .icvb
	 *
	 * Content is basically CSV with 3 columns: x, y, channel value
	 *
	 * Additional channels carrying custom information can be used, but requires
	 * extends of other classes.
	 */
	private void generateFromICV(){
		
	}
	
	/** Generates Pixs for ImageObj from a Pixel Value (PixV) file.
	 * Content is CSV with 6 columns: x, y, a, r, g, b
	 * 
	 * Additional columns carrying custom information may break compatibility.
	 */
	private void generateFromPixV(){
		
	}
	
	/** Generates Pixs for Imageobj from Grid (Grd) files.
	 * Which should be 4 files of the same name but with different extensions
	 * corresponding to each colour channel, e.g. .grda, .grdr, .grdg, .grdb
	 * 
	 * Content is CSV of width-columns and height-length, with each element
	 * being channel value.
	 * 
	 * Additional channels carrying custom information can be used, but requires
	 * extends of other classes.
	 */
	private void generateFromGrid(){
		
	}
	
	/** Generates ImageObj from file at path name specified for this CustomFile.
	 * 
	 */
	private void generateImageObj(){
		if(this.pathName.contains(".icv")){
			generateFromICV();
		}
		if(this.pathName.contains(".pixv")){
			generateFromPixV();
		}
		if(this.pathName.contains(".grd")){
			generateFromGrid();
		}
	}
	
	public ImageObj getImageObj(){
		if(this.imageObj == null){
			generateImageObj();
		}
		return this.imageObj;
	}
	
	private void __________METHODS__________(){}
}
