package nl.avans.essperience.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import nl.avans.essperience.events.ButtonPressedEventListener;

public class KeyboardController implements KeyListener
{
	private ButtonPressedEventListener _listener = null;
	
	private boolean _debug = true;
	private long[] _pressed = new long[255];
	private long[] _released = new long[255];
	public KeyboardController()
	{
		Arrays.fill(_pressed, 0);
		Arrays.fill(_released, 0);
	}
	
	public void addButtonPressedEventListener(ButtonPressedEventListener listener)
	{
		this._listener = listener;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
			if((System.currentTimeMillis() - _pressed[arg0.getKeyCode()]) < 150)
				return;
			
			_pressed[arg0.getKeyCode()] = System.currentTimeMillis();
			
			if(_listener != null)
				_listener.keyboardButtonPressed(arg0.getKeyCode());
			
			if(_debug)
				System.out.println("button pressed : " + arg0.getKeyCode() + " : " + arg0.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		if((System.currentTimeMillis() - _released[arg0.getKeyCode()]) < 150)
			return;
		
		_released[arg0.getKeyCode()] = System.currentTimeMillis();
		
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
