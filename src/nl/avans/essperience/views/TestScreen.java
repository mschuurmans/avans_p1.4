package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.TestModel;

public class TestScreen extends GameScreen {

	public TestScreen(TestModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		System.out.println("drawing test screen");
	}

}
