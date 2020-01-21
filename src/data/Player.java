package data;

import helpers.Clock;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	private Tower tempTower;
	public static int Crystal, Lives, Score;
	
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		Crystal = 0;
		Lives = 0;
		Score = 0;
	}
	
	//Initialize Crystal and Lives values for Player
	public void setup() {
		Crystal = 200;
		Lives = 10;
		Score = 0;
	}
	
	public boolean checkLives() {
		if (Lives == 0)
			return false;
		return true;
	}
	
	public boolean isWin() { 
		if(waveManager.getWaveNumber() == 5)
			return true;
		return false;
	}
	
	//Check if player can afford tower, if so: charge player tower cost
	public static boolean modifyCrystal(int amount) {
		if (Crystal + amount >= 0) {
			Crystal += amount;
			System.out.println(Crystal);
			return true;
		}
		System.out.println(Crystal);
		return false;
	}
	
	public static void modifyScore(int amount) {
		Score += amount;
	}
	
	public static void modifyLives(int amount) {
		Lives += amount;
	}
	
	public void update() {
		
		//Update holding tower
		if (holdingTower) {
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		
		//Update all towers in the game
		for (Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}
		
		//Handle Mouse Input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeTower();
		}
		
		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			System.out.println("Right clicked");
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);
		
		//Handle Keyboard Input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(-0.2f);
			}
		}
	}
	
	private void placeTower() {
		Tile currentTile = getMouseTile();
		if (holdingTower)
			if (!currentTile.getOccupied() && modifyCrystal(-tempTower.getCost())) {
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
	}
	
	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}
	
	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
	
}