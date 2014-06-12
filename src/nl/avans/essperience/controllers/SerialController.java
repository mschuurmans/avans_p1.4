package nl.avans.essperience.controllers;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.OutputStream;

import javax.security.auth.login.Configuration;

import nl.avans.essperience.main.Main;

public class SerialController 
{
	private CommPortIdentifier _portID;
	private SerialPort _serialPort = null;
	private OutputStream _outputstream = null;
	
	public SerialController()
	{
		System.out.println("going to created.");
		try 
		{
			_portID = CommPortIdentifier.getPortIdentifier(Main.COM);
		
			_serialPort = (SerialPort)_portID.open("BLUETOOTHWRITER", 2000);
		}
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Created");
	}
	
	public void write(char c)
	{
		try
		{
			OutputStream out = _serialPort.getOutputStream();
			out.write(c);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
