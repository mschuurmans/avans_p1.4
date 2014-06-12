package nl.avans.essperience.controllers;

import nl.avans.essperience.main.Main;

public class LightController 
{
	public static int LED_STATUS;
	private boolean _running = false;
	
	private static LightController _instance = null;
	
	public static LightController Instance()
	{
		if(_instance == null)
			_instance = new LightController();
		
		return _instance;
	}
	private SerialController obj = new SerialController();
    
	public  synchronized void writeData(char data) {
       obj.write(data);
    }
	
    public void stop()
    {
    	_running = false;
    }
    
	public void start()
	{
		_running = true;
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				System.out.println("Lightcontroller");
				try
                {
                        while(_running)
                        {
	    					double timeRemaining = Main.GAME.getGameModel().getTimeRemaining();
	    					int c = 0;
	    					c = (int) timeRemaining;
	    					System.out.println("Status" + c);
	                        char ch = (char)c;
	                        writeData(ch);
                        }
                }
                catch(Exception e){}
				
				try
				{
					Thread.sleep(100);// runs 10x a second.
				}
				catch(Exception e){}
			}
		});
		t.start();
	}
}
