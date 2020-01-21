package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public enum TowerType {
	
	duzGulle(new Texture[]{LoadTexture("res/" + "tower1" + ".png", "PNG")}, ShotType.duzGulle, 30, 1000, 3, 15),
	buzGulle(new Texture[]{LoadTexture("res/" + "icetower" + ".png", "PNG")}, ShotType.buzGulle, 30, 1000, 3, 20);
	
	Texture[] textures;
	ShotType shotType;
	int damage, range, cost;
	float firingSpeed;
	
	TowerType(Texture[] textures, ShotType shotType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.shotType = shotType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}

}
