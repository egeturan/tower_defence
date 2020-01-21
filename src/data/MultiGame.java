package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.LoadTexture;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import UI.UI.Menu;
import communication.NewServer;
import communication.Server;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class MultiGame {
	
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UI gameUI;
	private Menu towerPickerMenu;
	private Texture menuBackground;
	private Enemy[] enemyTypes;

	public MultiGame(TileGrid grid) {
		this.grid = grid;
		enemyTypes = new Enemy[3];
		enemyTypes[0] = new Eleman(2, 0, grid);
		enemyTypes[1] = new Robot(2, 0, grid);
		enemyTypes[2] = new Bat(2, 0, grid);
		waveManager = new WaveManager(enemyTypes, 2, 20);// enemy wave
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
	
	public static void startCom() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Server clientObject = new Server();
				clientObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				clientObject.startRunning();
				
				Thread t2 = new Thread(new Runnable() {
					@Override
					public void run() {
						NewServer scoreNetwork;
						scoreNetwork = new NewServer();
						scoreNetwork.startRunning();
					}
				});
				t2.start();
			}
		});
		t1.start();
	}

	
	public void update() {
		DrawQuadTex(menuBackground, 0, 960, 2560, 128);
		grid.draw();
		if (!player.checkLives()) {
			
			/*try {
				t2.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			StateManager.setState(GameState.LOSE);
		}
		waveManager.update();
		player.update();
		updateUI();
	}
}