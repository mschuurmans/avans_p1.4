package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerFinishedEventListener;
import nl.avans.essperience.models.FlappyBirdModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.FlappyBirdScreen;

public class FlappyBirdController extends GameController
{
	private FlappyBirdModel _model;
	private FlappyBirdScreen _view;
	private boolean _debug = true;
	
	public FlappyBirdController(FlappyBirdModel model, FlappyBirdScreen view)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				switch(key)
				{
					case KeyA:
						_view.flap();
						break;
				}
				
				if(_debug)
					System.out.println("FlappyBirdController : key has been pressed");
			}
			
			public void WiimotionGForceMovement()
			{

				_view.flap();
			}
		});
		
		_view.addViewToControllerEventListener(new ViewToControllerFinishedEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean succes)
			{
				callFinishedListener(succes);
			}
		});
		
		InputController.Instance().setMotionDetecting(true);
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
}
