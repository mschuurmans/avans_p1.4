package nl.avans.essperience.controllers;

import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.views.SimonGameScreen;

public class SimonGameController extends GameController
{
	private SimonGameModel _model;
	private SimonGameScreen _view;
	
	public SimonGameController(SimonGameModel model, SimonGameScreen view)
	{
		this._model = model;
		this._view = view;
		
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	
}
