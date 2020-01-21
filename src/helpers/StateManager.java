package helpers;

import data.Lose;
import data.Game;
import data.Game2;
import data.Game3;
import data.MainMenu;
import data.MultiGame;
import data.MultiMenu;
import data.TileGrid;
import data.Win;


public class StateManager {
	
	public static enum GameState {
		MAINMENU, GAME, MULTIGAME, MULTIMENU, GAME2, GAME3, LOSE, WIN
	}
	
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Game2 game2;
	public static Game3 game3;
	public static MultiGame multigame;
	public static MultiMenu multimenu;
	public static Lose lose;
	public static Win win;
	
	static int[][] mapX = {
			{1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2},
			{0,0,0,0,0,0,0,0,0,0,1,0,2,2,2,2,2,2,2,2},
			{2,2,2,2,2,2,2,2,1,1,1,0,0,0,0,2,2,2,2,2},
			{2,0,2,2,2,2,2,2,1,0,0,0,0,0,0,0,2,2,2,2},
			{2,0,0,2,2,2,2,2,1,0,0,0,0,0,0,0,0,2,2,2},
			{2,0,0,0,2,2,2,2,1,0,0,0,0,0,0,0,0,2,2,2},
			{2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,0,0,2,2},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,2,2},
			{2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,1,0,0,2,2},
			{2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,1,0,0,2,2},
			{2,2,2,2,2,0,0,0,2,2,2,2,2,2,2,1,1,1,0,2},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,2},
			{2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2},
			{2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			
	};
	

	  static int[][] mapY = {
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,5,5,5},
			{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,3,5,5,5},
			{5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,3,5,5,5},
			{5,5,5,5,5,4,5,5,5,3,3,3,3,3,3,3,3,5,5,5},
			{5,5,5,5,5,4,5,5,5,3,3,3,3,3,3,3,3,3,5,5},
			{5,5,5,5,5,4,5,5,5,3,3,3,3,3,3,3,3,3,5,5},
			{5,5,5,5,5,4,5,5,5,3,3,3,3,3,3,3,3,3,5,5},
			{5,5,5,5,5,4,5,5,5,5,3,3,3,3,3,3,3,3,5,5},
			{5,5,5,5,5,4,5,5,5,5,5,5,5,5,3,3,3,3,5,5},
			{5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,3,5},
			{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,3,5},
			{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,3,5},
			{5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,3,5},
			{5,5,5,5,5,5,4,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
			
	};
	
	
	static TileGrid map = new TileGrid(mapX);
	
	static TileGrid map2 = new TileGrid(mapY);
	
	
	public static void update() {
		
		switch(gameState) {
		case MAINMENU:
			if (mainMenu == null)
				mainMenu = new MainMenu();
			mainMenu.update();
			break;
		case MULTIMENU:
			if(multimenu == null)
				multimenu = new MultiMenu();
			multimenu.update();
			break;
		case GAME:
			if (game == null)
				game = new Game(map);
			game.update();
			break;
		case MULTIGAME:
			if (multigame == null)
				multigame = new MultiGame(map);
			multigame.update();
			break;
		case GAME2:
			if (game2 == null)
				game2 = new Game2(map2);
			game2.update();
			break;
		case GAME3:
			if (game3 == null)
				game3 = new Game3(map);
			game3.update();
			break;
		case LOSE:
			if (lose == null)
				lose = new Lose();
			lose.update();
			break;
		case WIN:
			if (win == null)
				win = new Win();
			win.update();
			break;
		}
	}
	
	public static void setState(GameState newState) {
		gameState = newState;
	}

}
