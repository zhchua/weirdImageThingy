package main.collections;

import main.collections.base.PixListBase;
import main.elementInfo.Coord;
import main.imageElements.Pix;

public class PixList extends PixListBase {

	public PixList() {
	}

	public PixList(PixListBase pixListBase) {
		super(pixListBase);
	}

	
	/** Gets Pix with the given Coord.
	 * Returns null if it doesn't exist.
	 * 
	 * @param coord
	 * @return
	 */
	public Pix get(Coord coord){
		for(int pixInx = 0; pixInx < this.size(); pixInx++){
			if(this.get(pixInx).getCoord().equals(coord)){
				return this.get(pixInx);
			}
		}
		return null;
	}
	
	/** Gets Pix with the given x and y coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Pix get(int x, int y){
		Coord getCoord = new Coord(x,y);
		return get(getCoord);
	}

}
