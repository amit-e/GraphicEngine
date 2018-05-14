package main.elements;

import java.awt.Color;

import main.primitives.Point3D;
import main.primitives.Vector;

public class SpotLight extends PointLight{

	private Vector _direction;
	
	public SpotLight(Color color, Point3D position, Vector direction,
					 double kc, double kl, double kq) {
		
		super(color, position, kc, kl, kq);
		_direction = new Vector(direction);
		_direction.normalize();
	}
	
	public Color getIntensity(Point3D point) {
		
		Color pointColor = super.getIntensity(point);
		
		Vector l = getL(point);
		l.normalize();
		
		double k = Math.abs(_direction.dotProduct(l));

		if (k > 1) k = 1; // doesn't allow light magnification
		
		return new Color((int)(pointColor.getRed()   * k),
				 		 (int)(pointColor.getGreen() * k),
				 		 (int)(pointColor.getBlue()  * k)); 
	}

}
