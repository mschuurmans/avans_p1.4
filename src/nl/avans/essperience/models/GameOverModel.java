package nl.avans.essperience.models;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.Utils;

public class GameOverModel extends GameModel {

	public GameOverModel()
	{
		int score = Main.GAME.getScore();
		String name = Main.GAME.getPlayerName();
		
		Utils.addHighScore(name, score);
	}
	
}
