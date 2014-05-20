package nl.avans.essperience.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nl.avans.essperience.events.ButtonPressedEventListener;

public class KeyboardController implements KeyListener
{
	private ButtonPressedEventListener _listener = null;
	
	private boolean _debug = true;
	
	public void addButtonPressedEventListener(ButtonPressedEventListener listener)
	{
		this._listener = listener;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
			if(_listener != null)
				_listener.keyboardButtonPressed(arg0.getKeyCode());
			
			if(_debug)
				System.out.println("button pressed : " + arg0.getKeyCode() + " : " + arg0.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		if(_listener != null)
			_listener.keyboardButtonReleased(arg0.getKeyCode());
		
		if(_debug)
			System.out.println("button Released : " + arg0.getKeyCode() + " : " + arg0.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		
	}

}
