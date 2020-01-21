package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.HEIGHT;
import static helpers.Artist.LoadTexture;
import static helpers.Artist.WIDTH;

import helpers.StateManager;
import helpers.StateManager.GameState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;

public class Lose {
	private Texture wallpaper2;
	private Texture losepage;
	private UI loseUI;
	
	public Lose() {
		wallpaper2 = LoadTexture("res/" + "wallpaper2" + ".png", "PNG");
		loseUI = new UI();
		loseUI.addButton("PlayAgain", "playagain", 420, 400);
		loseUI.addButton("Back", "back2", 660, 650);
		this.losepage = LoadTexture("res/" + "losepage" + ".png", "PNG");
	}
	
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (loseUI.isButtonClicked("Back"))
				StateManager.setState(GameState.MAINMENU);
			if(loseUI.isButtonClicked("PlayAgain"))
				StateManager.setState(GameState.GAME3);
		}
	}
	
	public void update() {
		DrawQuadTex(wallpaper2, 0, 0, 2048, 1024);
		DrawQuadTex(losepage, 0, 960, 2560, 128);
		loseUI.draw();
		updateButtons();
	}
}