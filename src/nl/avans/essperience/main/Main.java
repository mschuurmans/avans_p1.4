package nl.avans.essperience.main;

import javax.swing.JFrame;

import nl.avans.essperience.controllers.GameHandler;

public class Main 
{
	public static JFrame GAME;
	public static void main(String[] args)
	{
		GAME = new GameHandler();
	}
}

