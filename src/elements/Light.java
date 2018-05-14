package main.elements;

import java.awt.Color;

public abstract class Light {

	protected Color _color;
	
	public Light()              { _color = null;  }
	public Light (Color color)  { _color = color; }
	
	public Color getIntensity() { return _color;  }
	
}
