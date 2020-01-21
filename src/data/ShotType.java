package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public enum ShotType {
	
	duzGulle(LoadTexture("res/" + "bullet" + ".png", "PNG"), 5, 500),
	buzGulle(LoadTexture("res/" + "projectileIceball" + ".png", "PNG"), 2 , 200);
	
	Texture texture;
	int damage;
	float speed;
	
	ShotType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}

}
