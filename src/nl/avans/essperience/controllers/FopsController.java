package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ModelToControllerEventListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.FopsScreen;
import wiiusej.wiiusejevents.physicalevents.IREvent;

public class FopsController extends GameController
{
	private boolean _debug = false;
	private FopsModel _model;
	private FopsScreen _view;
	private double _cursorX;
	private double _cursorY;
	private final int IRXCORRECTION = Main.GAME.getWidth()/360;
	private final int IRYCORRECTION = Main.GAME.getWidth()/410;
	
	public FopsController(FopsModel model, FopsScreen view)
	{
		_model = model;
		_view = view;
		
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			@Override
			public void keyPressed(GameKeys key)
			{
				if (key.getID() == 1)
				{
					if (key == GameKeys.WiiB)
					{
						if (_model.getBullets() > 0)
						{
							AssetManager.Instance().playSound("Fops/gun shot.wav");
							_model.bulletFired();
							_model.checkHits();
						}
						else
						{
							AssetManager.Instance().playSound("Fops/gun empty.wav");	
						}
					}
				}
				if (key == GameKeys.KeySpacebar)
				{
					callFinishedListener(false);
				}
			}
			
			@Override
			public void wiimoteIREvent(IREvent e)
			{
				if (e.getWiimoteId() == 1)
				{
					_cursorX = e.getX()*IRXCORRECTION;
					_cursorY = e.getY()*IRYCORRECTION;
					_model.updateCursorPosition(_cursorX, _cursorY);
					if (_debug)
						System.out.println("xpos and ypos changed. x: " + _cursorX + " y: " + _cursorY);
					//ir event of the wiimote
				}
			}
		});
		
		_model.addModelToControllerEventListener(new ModelToControllerEventListener()
		{
			@Override
			public void timesUpEvent()
			{
				_view.stopTimer();
				callFinishedListener(false);
			}
			
			public void gameFinished(boolean succeed)
			{
				_view.stopTimer();
				callFinishedListener(succeed);
			}
		});
		
		InputController.Instance().setMotionDetecting(false);
		InputController.Instance().setIRTracking(true);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}

}
