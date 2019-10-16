package Collections;

import Collections.Base.PixListBase;
import ImageObjects.Pix;
import InfoObjects.Coord;

public class PixList extends PixListBase {

	public PixList() {
		// TODO Auto-generated constructor stub
	}

	public PixList(PixListBase pixListBase) {
		super(pixListBase);
		// TODO Auto-generated constructor stub
	}

	
	/** Gets Pix with the given Coord.
	 * Returns null if it doesn't exist.
	 * 
	 * @param coord
	 * @return
	 */
	public Pix get(Coord coord){
		for(int pixInx = 0; pixInx < this.size(); pixInx++){
			if(this.get(pixInx).getCoord() == coord){
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
