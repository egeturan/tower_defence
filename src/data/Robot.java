package data;

public class Robot extends Enemy {

	public Robot(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTextureGif("eleman2");
		this.setSpeed(80);
	}

}
