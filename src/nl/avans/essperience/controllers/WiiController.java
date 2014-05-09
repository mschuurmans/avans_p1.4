package nl.avans.essperience.controllers;

import nl.avans.essperience.events.ButtonPressedEventListener;
import nl.avans.essperience.utils.Enums.GameKeys;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;
import wiiusej.wiiusejevents.wiiuseapievents.WiiUseApiEvent;

/**
 * Class that handles the Wiimote logic.
 * @author michiel
 *
 */
public class WiiController implements WiimoteListener
{
	private Wiimote[] _wiimotes;
	private ButtonPressedEventListener _listener = null;
	public static final int MAX_TRIES = 3;

	private boolean _debug = true;
	public WiiController(int numbers)
	{
		if(_debug)
			System.out.println("Searching for WiiRemotes.");
		Wiimote[] wiimotes = new Wiimote[0];
		boolean keepSearching = true;
		int times = 0;
		while(keepSearching)
		{
			wiimotes = WiiUseApiManager.getWiimotes(numbers, true);
			if(_debug)
				System.out.println("Found: " + wiimotes.length + " wiimotes.");
			if(wiimotes.length > 0 || times >= MAX_TRIES)
				keepSearching = false;
			
			times++;
		}
		int count = 1;
		for(Wiimote wim : wiimotes)
		{
			boolean l1 = true;
			boolean l2 = false;
			boolean l3 = false;
			boolean l4 = false;
			
			if(count >1) l2 = true;
			if(count >2) l3 = true;
			if(count > 3) l4 = true;
			
			wim.setLeds(l1, l2, l3, l4);
			wim.addWiiMoteEventListeners(this);
			wim.activateSmoothing();
			count++;
		}
		
		_wiimotes = wiimotes;			
	}
	
	public void activateMotionSensor()
	{
		for(Wiimote wim : _wiimotes)
		{
			wim.activateMotionSensing();
		}
	}
	
	public void deactivateMotionSensor()
	{
		for(Wiimote wim : _wiimotes)
		{
			wim.deactivateMotionSensing();
		}
	}
	
	public void addButtonPressedListener(ButtonPressedEventListener listener)
	{
		_listener = listener;
	}
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e) 
	{
		//if(_debug)
			//System.out.println("WiiController : Button event called");
		if(e.isButtonAJustPressed())
		{
			if(_listener != null)
				_listener.wiimoteButtonPressed(GameKeys.WiiA);
		}
		else if(e.isButtonBJustPressed())
		{
			if(_listener != null)
				_listener.wiimoteButtonPressed(GameKeys.WiiB);
		}
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpansionEvent(ExpansionEvent e) 
	{
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIrEvent(IREvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent e) 
	{		
		if(e.getGforce().getY() > 2.5f)
		{
			if(_debug)
				System.out.println("moving detected.");
			
			if(_listener != null)
				_listener.wiimoteMotionGForceAcceleration();
		}
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusEvent(StatusEvent e)
	{
		
	}

}
