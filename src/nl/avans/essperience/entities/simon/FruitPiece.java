package nl.avans.essperience.entities.simon;

import java.awt.Image;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Shape;

public class FruitPiece extends Body
{
	Image _image;
	
	public FruitPiece(String name, Shape shape, float m)
	{
		super(name, shape, m);
		
		switch(name)
		{
		case "banana":
			
			break;
		case "orange":
		
			break;
		case "apple":
		
			break;
		case "pear":
		
			break;
		}

	}

}
