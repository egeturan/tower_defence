package data;

public class ShotBall extends Shot {

	public ShotBall(ShotType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	@Override
	public void damage() {
		super.damage();
	}

}
