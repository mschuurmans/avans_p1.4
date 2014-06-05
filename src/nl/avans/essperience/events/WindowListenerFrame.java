package nl.avans.essperience.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import nl.avans.essperience.utils.Utils;

public class WindowListenerFrame implements WindowListener
{
	@Override
	public void windowOpened(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowIconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		System.out.println("enabling auto input.");
		Utils.enableAutoPress();
	}
	
	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) 
	{
		
	}
}
