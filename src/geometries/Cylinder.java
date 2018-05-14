package main.geometries;

import java.util.List;

import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

public class Cylinder extends RadialGeometry {

	private Point3D _axisPoint;
	private Vector _axisDirection;
	
	// ***************** Constructors ********************** // 
	
	public Cylinder(){
		super(0.0);
		_axisPoint = new Point3D();
		_axisDirection = new Vector();
	}
	
	public Cylinder(Cylinder cylinder){
		super(cylinder._radius);
		_axisPoint = cylinder.getAxisPoint();
		_axisDirection = cylinder.getAxisDirection();
	}
	
	public Cylinder(double radius, Point3D axisPoint, Vector axisDirection){
		super(radius);
		_axisPoint = new Point3D(axisPoint);
		_axisDirection = new Vector(axisDirection);
	}
	
	// ***************** Getters/Setters ********************** //
	
	public Point3D getAxisPoint()    { return new Point3D(_axisPoint);	  }
	public Vector getAxisDirection() { return new Vector(_axisDirection); }
	
	public void setAxisPoint(Point3D axisPoint)        { this._axisPoint = new Point3D(axisPoint); }
	public void setAxisDirection(Vector axisDirection) { this._axisDirection = new Vector(axisDirection); }


	// ***************** Operations ******************** //
	
	@Override
	public List<Point3D> FindIntersections(Ray ray) {
		// To be implemented 
		return null;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// To be implemented
		return null;
	}
	
}
