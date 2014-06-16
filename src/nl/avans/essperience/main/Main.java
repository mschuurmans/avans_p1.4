package nl.avans.essperience.main;

import java.awt.Dimension;

import nl.avans.essperience.controllers.GameHandler;
import nl.avans.essperience.controllers.LightController;
//import nl.avans.essperience.controllers.SerialControllerOLD;

public class Main 
{
	public static GameHandler GAME;
	public static Dimension DIMENSION = new Dimension(1600, 900);

	public static String COM = "COM11";
	public static void main(String[] args)
	{
		System.out.println("Started");
		
		GAME = new GameHandler(DIMENSION);
		if(GAME.getLoadingModel() != null)
			GAME.getLoadingModel().startLoading();
		
		GAME.reset();
	}
}

