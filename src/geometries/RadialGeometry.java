package main.geometries;

public abstract class RadialGeometry extends Geometry {

	protected double _radius;
	
	public RadialGeometry()              { this._radius = 0.0;    }
	public RadialGeometry(double radius) { this._radius = radius; }
	
	public double getRadius()              { return _radius;	    }
	public void   setRadius(double radius) { this._radius = radius; }
	
}
