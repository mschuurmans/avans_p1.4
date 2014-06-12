package nl.avans.essperience.main;

import java.awt.Dimension;

import nl.avans.essperience.controllers.GameHandler;
import nl.avans.essperience.controllers.LightController;
//import nl.avans.essperience.controllers.SerialControllerOLD;

public class Main 
{
	public static GameHandler GAME;
	public static Dimension DIMENSION = new Dimension(1920, 1080);

	public static String COM = "COM7";
	public static void main(String[] args)
	{
		System.out.println("Started");
		
		GAME = new GameHandler(DIMENSION);
		if(GAME.getLoadingModel() != null)
			GAME.getLoadingModel().startLoading();
		
		GAME.reset();
	}
}

