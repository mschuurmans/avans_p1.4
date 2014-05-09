package nl.avans.essperience.controllers;

import java.awt.event.KeyListener;

import nl.avans.essperience.events.ButtonPressedEventListener;
import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.utils.Utils;

public class InputController 
{
	private InputTriggerdEventListener _listener = null;
	private KeyboardController _keyboardListener = null;
	private WiiController _wiiController = null;
	
	private static InputController _instance = null;
	
	public static InputController Instance()
	{
		if(_instance == null)
			_instance = new InputController();
		
		return _instance;
	}
	
	public InputController()
	{
		_keyboardListener = new KeyboardController();
		_keyboardListener.addButtonPressedEventListener(new ButtonPressedEventListener()
		{				
			@Override
			public void keyboardButtonPressed(int code) 
			{
				if(_listener != null)
					_listener.keyPressed(Utils.getFromKeyboardCode(code));
			}
		});
		_wiiController = new WiiController(2); // TODO replace magic cookies..
		_wiiController.addButtonPressedListener(new ButtonPressedEventListener()
		{
			@Override
			public void wiimoteMotionGForceAcceleration()
			{
				if(_listener != null)
					_listener.WiimotionGForceMovement();
			}
			
			@Override
			public void wiimoteButtonPressed(GameKeys key) 
			{
				if(_listener != null)
					_listener.keyPressed(key);
			}
		});
	}
	
	public void setMotionDetecting(boolean state)
	{
		if(state)
			_wiiController.activateMotionSensor();
		else
			_wiiController.deactivateMotionSensor();
	}
	
	public void addInputTriggeredEventListener(InputTriggerdEventListener listener)
	{
		this._listener = listener;
	}
	
	public KeyListener getKeyboardListener()
	{
		return this._keyboardListener;
	}
}
