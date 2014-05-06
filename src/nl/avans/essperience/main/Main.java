package nl.avans.essperience.main;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args)
	{
		MyFrame frame = new MyFrame();
	}
}

class MyFrame extends JFrame
{
	private static final long serialVersionUID = 5333337319521227340L;

	public MyFrame()
	{
		super("The Essperience");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
		//setContentPane(new MenuScreen(new GameModel()));
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);  
		setSize(800, 800);
		setVisible(true);
	}
}
