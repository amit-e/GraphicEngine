package main.geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

public class Sphere extends RadialGeometry{

	private Point3D _center;

	// ***************** Constructors ********************** // 	
	
	public Sphere(){
		super(0.0);
		_center = new Point3D();
	}
	
	public Sphere (Sphere sphere){
		super(sphere._radius);
		_center = sphere.getCenter();
	}
	
	public Sphere(double radius, Point3D center){
		super(radius);
		_center = new Point3D(center);
	}
	
	public Sphere(Map<String, String> attributes){
		
		String[] centerPoints = attributes
				.get("center" ).split("\\s+");
		
		_center = new Point3D(Double.valueOf(centerPoints[0]), 
									 Double.valueOf(centerPoints[1]), 
									 Double.valueOf(centerPoints[2]));
		
		_radius = Double.valueOf(attributes.get("radius"));
	}
	
	// ***************** Getters/Setters ********************** //
	
	public Point3D getCenter()            { return new Point3D(_center);         }
	public void setCenter(Point3D center) { this._center = new Point3D(_center); }

	// ***************** Operations ******************** //
	
	@Override
	public List<Point3D> FindIntersections(Ray ray) {
		
		List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);
		
		Vector L = new Vector(ray.getPOO(), this.getCenter());
		double tm = L.dotProduct(ray.getDirection());
		double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));
		
		if (d > this.getRadius())
			return intersectionPoints;
		
		double th = Math.sqrt(Math.pow(this.getRadius(), 2) - Math.pow(d, 2));
		
		double t1 = tm - th;
		double t2 = tm + th;
		
		if (t1 >= 0){
			Vector V = ray.getDirection();
			V.scale(t1);
			Point3D P1 = ray.getPOO();
			P1.add(V);
			intersectionPoints.add(P1);
		}
		
		if (t2 >= 0){
			Vector V = ray.getDirection();
			V.scale(t2);
			Point3D P2 = ray.getPOO();
			P2.add(V);
			intersectionPoints.add(P2);
		}
		
		return intersectionPoints;
		
	}

	@Override
	public Vector getNormal(Point3D point) {
		
		Vector N = new Vector (_center, point);
		N.normalize();
		return N;
		
	}
}
