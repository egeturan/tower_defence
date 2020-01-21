package data;

public class Bat extends Enemy {

	public Bat(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTextureGif("bat2");
	}

}
