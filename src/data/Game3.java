package data;

import static helpers.Artist.LoadTexture;
import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.WIDTH;
import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.HEIGHT;

import helpers.StateManager;
import helpers.StateManager.GameState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import UI.UI.Menu;

public class Game3 {
	
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UI gameUI;
	private Menu towerPickerMenu, backMenu;
	private Texture menuBackground;
	private Enemy[] enemyTypes;

	public Game3(TileGrid grid) {
		this.grid = grid;
		enemyTypes = new Enemy[3];
		enemyTypes[0] = new Eleman(2, 0, grid);
		enemyTypes[1] = new Robot(2, 0, grid);
		enemyTypes[2] = new Bat(2, 0, grid);
		waveManager = new WaveManager(enemyTypes, 2, 1);// enemy wave
		player = new Player(grid, waveManager);
		player.setup();
		this.menuBackground = LoadTexture("res/" + "menu_background2" + ".png", "PNG");

		setupUI();
	}
	
	private void setupUI() {
		gameUI = new UI();
		gameUI.createMenu("TowerPicker", 30, 1000, 100, 100, 2, 0);
		towerPickerMenu = gameUI.getMenu("TowerPicker");
		towerPickerMenu.quickAdd("DuzGulle", "tower1");
		towerPickerMenu.quickAdd("BuzGulle", "icetower");	
		
	}
	//This part the console of the demonstrating the player info
	private void updateUI() {
		gameUI.draw();
		gameUI.drawString(230, 1000, "Score: " + Player.Score);
		gameUI.drawString(430, 1000, "Lives: " + Player.Lives);
		gameUI.drawString(630, 1000, "Crystal: " + Player.Crystal);
		gameUI.drawString(830, 1000, "Wave " + waveManager.getWaveNumber());
		gameUI.addButton("Back", "back2", 1170, 1000);
		
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (towerPickerMenu.isButtonClicked("DuzGulle"))
					player.pickTower(new TowerGreek(TowerType.duzGulle, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if (towerPickerMenu.isButtonClicked("BuzGulle"))
					player.pickTower(new TowerIce(TowerType.buzGulle, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if (gameUI.isButtonClicked("Back"))
					StateManager.setState(GameState.MAINMENU);
			}
		}
	}
	
	 public void update() {
		DrawQuadTex(menuBackground, 0, 960, 2048, 128);
		grid.draw();
		if (!player.checkLives())
			StateManager.setState(GameState.LOSE);
		if(player.isWin() && waveManager.getCurrentWave().isCompleted() == true)
		{
			StateManager.setState(GameState.WIN);
		}
			
		waveManager.update();
		player.update();
		updateUI();
	}
}