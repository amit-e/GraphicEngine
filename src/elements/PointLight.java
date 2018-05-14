package main.elements;

import java.awt.Color;

import main.primitives.Point3D;
import main.primitives.Vector;;

public class PointLight extends Light  implements LightSource{

	Point3D _position;
	double _Kc, _Kl, _Kq;
	
	public PointLight(Color color, Point3D position, 
					  double kc, double kl, double kq) {
		super(color);
		_Kc = kc;
		_Kl = kl;
		_Kq = kq;
		_position = new Point3D(position);
		
	}

	@Override
	public Color getIntensity(Point3D point) {
		
		int r = _color.getRed();
		int g = _color.getGreen();
		int b = _color.getBlue();
		
		double d = _position.distance(point);
		
		double k = 1/(_Kc + _Kl*d + _Kq*Math.pow(d, 2));
		
		if (k > 1) k = 1;
		
		return new Color((int)(r * k),
				 		 (int)(g * k),
				 		 (int)(b * k)); 
	}

	@Override
	public Vector getL(Point3D point) { return new Vector (_position, point); }

}
