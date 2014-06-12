package nl.avans.essperience.main;

import java.awt.Dimension;

import nl.avans.essperience.controllers.GameHandler;
import nl.avans.essperience.controllers.SerialController;

public class Main 
{
	public static GameHandler GAME;
	public static Dimension DIMENSION = new Dimension(1280, 700);
	public static String COM = "/dev/tty0";
	public static void main(String[] args)
	{
		final SerialController sCon = new SerialController();
		sCon.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1500);
				sCon.writeData("102");} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
		
		GAME = new GameHandler(DIMENSION);
		if(GAME.getLoadingModel() != null)
			GAME.getLoadingModel().startLoading();
		
		GAME.reset();
	}
}

