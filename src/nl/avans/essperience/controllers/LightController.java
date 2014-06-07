package nl.avans.essperience.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import nl.avans.essperience.main.Main;

public class LightController 
{
	public static int LED_STATUS;
	public static BufferedReader input;
    public static OutputStream output;
    
    public static synchronized void writeData(String data) {
        System.out.println("Sent: " + data);
        try {
                output.write(data.getBytes());
        } catch (Exception e) {
                System.out.println("could not write to port");
        }
    }
	
	public void start()
	{
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				System.out.println("Lightcontroller");
				try
                {
                        SerialController obj = new SerialController();
    					double timeRemaining = Main.GAME.getGameModel().getTimeRemaining();
    					int c = 0;
    					c = (int) timeRemaining;
    					System.out.println("Status" + c);
                        obj.initialize();
                        input = SerialController.input;
                        output = SerialController.output;
                        InputStreamReader Ir = new InputStreamReader(System.in);
                        BufferedReader Br = new BufferedReader(Ir);
                        System.out.print("Enter your choice: ");
                        c = Integer.parseInt(Br.readLine());

                        System.out.println((char)c);
                        System.out.println(c);

                        //writeData("" + (char)c);
                        writeData("" + (char)101);

                        obj.close();

                }
                catch(Exception e){}
				
				try
				{
					Thread.sleep(100);// runs 10x a second.
				}
				catch(Exception e){}
			}
		});
		t.start();
	}
}
