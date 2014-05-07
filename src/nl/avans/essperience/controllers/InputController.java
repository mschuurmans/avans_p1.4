package nl.avans.essperience.controllers;

import nl.avans.essperience.events.ButtonPressedEventListener;
import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.utils.Utils;

public class InputController 
{
	private InputTriggerdEventListener _listener = null;
	private KeyboardController _keyboardListener = null;
	
	public InputController()
	{
		_keyboardListener = new KeyboardController();
		_keyboardListener.addButtonPressedEventListener(new ButtonPressedEventListener()
		{	
			@Override
			public void wiimoteButtonPressed(int code) 
			{
				//not necceseray
			}
			
			@Override
			public void keyboardButtonPressed(int code) 
			{
				if(_listener != null)
					_listener.keyPressed(Utils.getFromKeyboardCode(code));
			}
		});
	}
	
	public void addInputTriggeredEventListener(InputTriggerdEventListener listener)
	{
		this._listener = listener;
	}
}
