package nl.avans.essperience.controllers;

import nl.avans.essperience.main.Main;

public class LightController 
{
	public static int LED_STATUS;
	
	public void start()
	{
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				while(true)
				{
					double timeRemaining = Main.GAME.getGameModel().getTimeRemaining();
					//sent value
					try
					{
						Thread.sleep(100);// runs 10x a second.
					}
					catch(Exception e){}
				}
			}
			
		});
		t.start();
	}
}
