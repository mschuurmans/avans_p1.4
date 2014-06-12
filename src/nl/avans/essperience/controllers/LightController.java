package nl.avans.essperience.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nl.avans.essperience.main.Main;

public class LightController 
{
	public static int LED_STATUS;
	private boolean _running = false;
	private boolean _isRunning = false;
	private static LightController _instance = null;
	private Thread _t;
	private ExecutorService _serv;
	
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
	public LightController()
	{
		_serv = Executors.newFixedThreadPool(10);
	}
	
    public void stop()
    {
    	_running = false;
    	_serv.shutdownNow();
    }
    
	public void start()
	{
		_running = true;
		if(!_isRunning)
		{
			_serv.shutdownNow();
			_serv = Executors.newFixedThreadPool(1); 
			_serv.execute(new Runnable()
			{
	
				@Override
				public void run() 
				{
					System.out.println("Lightcontroller");
					try
	                {
	                        while(_running)
	                        {
	                        	_isRunning = true;
		    					int c = (int)(Main.GAME.getGameModel().getTimeRemainingAsInt() *0.6f);
		    					
		    					if (c>0)
		    					{
		    						System.out.println("Status " + c);
		    					}
		                        char ch = (char)c;
		                        writeData(ch);
		                        try
		        				{
		        					Thread.sleep(500);// runs 10x a second.
		        				}
		        				catch(Exception e){}
	                        }
	                }
	                catch(Exception e){}
					_isRunning = false;
					
				}
			});
		}
	}
}
