package nl.avans.essperience.controllers;

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
					// send comm..
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
