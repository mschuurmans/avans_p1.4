package nl.avans.essperience.controllers;

import nl.avans.essperience.events.ModelToControllerEventListener;
import nl.avans.essperience.models.LoadingModel;
import nl.avans.essperience.views.LoadingScreen;

public class LoadingController extends GameController
{
	LoadingModel _model;
	LoadingScreen _view;
	
	public LoadingController(LoadingModel model, LoadingScreen view)
	{
		_model = model;
		_view = view;
		_model.addModelToControllerEventListener(new ModelToControllerEventListener()
		{
			public void gameFinished(boolean succes)
			{
				_finishedListener.microGameFinishedEvent(succes);
			}
		});
	}
}
