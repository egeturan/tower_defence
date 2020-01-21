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

public class Win {
	private Texture background, winpage;
	private UI winUI;

	public Win() {
		background = LoadTexture("res/" + "wallpaper2" + ".png", "PNG");
		winUI = new UI();
		winUI.addButton("Next", "nextlevel", 420, 400 );
		winUI.addButton("Back", "back2", 660, 650 );
		this.winpage = LoadTexture("res/" + "winpage" + ".png", "PNG");
	}
	
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (winUI.isButtonClicked("Back"))
				StateManager.setState(GameState.MAINMENU);
			if (winUI.isButtonClicked("Next"))
				StateManager.setState(GameState.GAME2);//GAME2
		}
	}
	
	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 1024);
		DrawQuadTex(winpage, 0, 960, 2560, 128);
		winUI.draw();
		updateButtons();
		
	}
}
