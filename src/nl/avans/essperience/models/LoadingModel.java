package nl.avans.essperience.models;

import java.util.List;

import nl.avans.essperience.controllers.InputController;
import nl.avans.essperience.entities.Score;
import nl.avans.essperience.events.StatusUpdateListener;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class LoadingModel extends GameModel
{
	private static String _status;
	private StatusUpdateListener _listener = null;
	public boolean LOADING = true;
	public void addStatusUpdateListener(StatusUpdateListener list)
	{
		_listener = list;
	}
	
	public void startLoading()
	{
		LOADING = true;
		// TODO Auto-generated method stub
		_status = "Loading assets...";
		System.out.println(_status);
		
		sendStatus();
		AssetManager.Instance();
		_status = "Retrieving highscores...";
		
		sendStatus();
		System.out.println(_status);
		List<Score> scores = Utils.getTopScores(5);
		Main.GAME.setScores(scores);
		_status = "Searching for Wiimotes...";
		sendStatus();
		System.out.println(_status);
		InputController.Instance();
		
		_status = "Starting background workers...";
		sendStatus();
		Utils.startBackgroundWorder();
		try
		{
			Thread.sleep(1000);
		}catch(Exception e){}
		LOADING = false;
			
	}
	
	public void sendStatus()
	{
		if(_listener != null)
			_listener.statusUpdated(_status);
	}
	
	
}
