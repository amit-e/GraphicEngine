package main.elements;

import java.awt.Color;

import main.primitives.Point3D;
import main.primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

	private Vector _direction;
	
	public DirectionalLight(Color color, Vector direction){
		super (color);
		_direction = new Vector(direction);
	}
	
	public Color  getIntensity(Point3D point)   { return getIntensity();         }
	public Vector getDirection()                { return new Vector(_direction); }
	public Vector getL(Point3D point)           { return getDirection();         }
	
	public void setDirection(Vector _direction) { this._direction = _direction;	 }



}
