package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerGreek extends Tower {
	
	public TowerGreek(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
	}
	
	@Override
	public void shoot(Enemy target) {
		super.shots.add(new ShotBall(super.type.shotType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.shotType.damage);
	}
}