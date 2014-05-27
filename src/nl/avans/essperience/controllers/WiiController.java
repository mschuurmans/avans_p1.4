package nl.avans.essperience.controllers;

import nl.avans.essperience.events.ButtonPressedEventListener;
import nl.avans.essperience.utils.Enums.GameKeys;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
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
	private static int _currentWiiMoteID = 0;

	private boolean _debug = true;
	public WiiController(int numbers)
	{
		if(_debug)
			System.out.println("Searching for WiiRemotes.");
		Wiimote[] wiimotes = new Wiimote[0];
		boolean keepSearching = true;
		int times = 1;
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
			wim.activateMotionSensing();
			count++;
		}
		System.out.println(wiimotes.length);
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

	public void activateIRTracking()
	{
		for(Wiimote wim : _wiimotes)
		{
			wim.activateIRTRacking();
		}
	}
	
	public void deactivateIRTracking()
	{
		for(Wiimote wim : _wiimotes)
		{
			wim.deactivateIRTRacking();
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
		//	System.out.println("WiiController : Button event called");
		_currentWiiMoteID = e.getWiimoteId();
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
		if(e instanceof NunchukEvent)
		{
			NunchukEvent nunchuck = (NunchukEvent)e;
			
			NunchukButtonsEvent buttons = nunchuck.getButtonsEvent();
			JoystickEvent joy = nunchuck.getNunchukJoystickEvent();
			MotionSensingEvent mot = nunchuck.getNunchukMotionSensingEvent();
			
			if(joy.getAngle() < 85 || joy.getAngle() > 95)
				System.out.println(joy.getAngle());
			
			MotionSensingEvent evt = nunchuck.getNunchukMotionSensingEvent();
			System.out.println(evt.getGforce());
			if(evt.getGforce().getY() > 0.4f)
			{
				if(_debug)
					System.out.println("nunchuck moved");
				
				if(_listener != null)
					_listener.wiimoteMotionGForceAcceleration();
			}
		}
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
	public void onIrEvent(IREvent e) 
	{
		if(_listener != null)
			_listener.wiimoteIREvent(e);
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent e) 
	{		
		//System.out.println(e.getGforce().getY());
		if(e.getGforce().getY() > 2.5f)
		{
			//if(_debug)
			//	System.out.println("moving detected.");
			
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
	
	public static int getID()
	{
		return _currentWiiMoteID;
	}

}
