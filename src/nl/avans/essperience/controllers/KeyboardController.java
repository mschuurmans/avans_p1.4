package nl.avans.essperience.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nl.avans.essperience.events.ButtonPressedEventListener;

public class KeyboardController implements KeyListener
{
	private ButtonPressedEventListener _listener = null;
	
	public void addButtonPressedEventListener(ButtonPressedEventListener listener)
	{
		this._listener = listener;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
			if(_listener != null)
				_listener.keyboardButtonPressed(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		
	}

}
