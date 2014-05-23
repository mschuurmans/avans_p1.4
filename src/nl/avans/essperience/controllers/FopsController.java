package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.FopsScreen;
import wiiusej.wiiusejevents.physicalevents.IREvent;

public class FopsController extends GameController
{
	private FopsModel _model;
	private FopsScreen _view;
	
	public FopsController(FopsModel model, FopsScreen view)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			@Override
			public void keyPressed(GameKeys key)
			{
				
			}
			
			@Override
			public void wiimoteIREvent(IREvent e)
			{
				//ir event of the wiimote
			}
		});
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
}
