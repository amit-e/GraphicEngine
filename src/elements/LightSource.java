package main.elements;

import java.awt.Color;

import main.primitives.Point3D;
import main.primitives.Vector;

public interface LightSource{
	
	public abstract Color getIntensity(Point3D point);
	public abstract Vector getL(Point3D point); // light to point vector
	
}
