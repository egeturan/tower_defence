package data;

public class Eleman extends Enemy {

	public Eleman(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTextureGif("eleman");
	}

}
