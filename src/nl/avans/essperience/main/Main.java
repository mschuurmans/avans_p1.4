package nl.avans.essperience.main;

import java.awt.Dimension;

import nl.avans.essperience.controllers.GameHandler;

public class Main 
{
	public static GameHandler GAME;
	public static Dimension DIMENSION = new Dimension(1280, 700);
	public static void main(String[] args)
	{
		GAME = new GameHandler(DIMENSION);
		if(GAME.getLoadingModel() != null)
			GAME.getLoadingModel().startLoading();
		
		GAME.reset();
	}
}

