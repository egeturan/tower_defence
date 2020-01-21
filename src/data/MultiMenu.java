package data;

import helpers.StateManager;
import helpers.StateManager.GameState;

import data.MultiGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import static helpers.Artist.*;

public class MultiMenu {
	
	private Texture background;
	private UI menuUI;
	
    public MultiMenu() {
		background = LoadTexture("res/" + "wallpaper2" + ".png", "PNG");
		menuUI = new UI();
		menuUI.addButton("Single", "singlePlayer", 20, (int) (HEIGHT * 0.75f));
		menuUI.addButton("MultiPlayer", "multi", 720, (int) (HEIGHT * 0.75f));
	}
	
	//Check if a button is clicked by the user, and if so do an action
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Single")) {
				StateManager.setState(GameState.GAME);
			}
			if (menuUI.isButtonClicked("MultiPlayer")) {
				StateManager.setState(GameState.MULTIGAME);
				MultiGame.startCom();
			}
		}
	}
	
	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
		updateButtons();
	}

}
