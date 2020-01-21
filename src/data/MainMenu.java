package data;

import helpers.StateManager;
import helpers.StateManager.GameState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import static helpers.Artist.*;

public class MainMenu {
	
	private Texture background,menuname;
	private UI menuUI;
	
	public MainMenu() {
		background = LoadTexture("res/" + "wallpaper2" + ".png", "PNG");
		menuUI = new UI();
		menuUI.addButton("Play", "play", WIDTH / 2 - 310, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Quit", "quit", WIDTH / 2 - 190, (int) (HEIGHT * 0.65f));
		this.menuname = LoadTexture("res/" + "menuname" + ".png", "PNG");
	}
	
	//Check if a button is clicked by the user, and if so do an action
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Play")) {
					StateManager.setState(GameState.MULTIMENU);
			}
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}
	
	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 1024);
		DrawQuadTex(menuname, 0, 960, 2048, 128);
		menuUI.draw();
		updateButtons();
	}

}
