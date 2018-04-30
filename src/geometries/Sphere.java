package geometries;

import java.util.List;

import primitives.*;

public class Sphere extends RadialGeometry {
    private Point3D _center;

    // ***************** Constructors ********************** //
    public Sphere() {
        _center = new Point3D();
    }
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = new Point3D(center);
    }
    public Sphere (Sphere sphere) {
        super(sphere._radius);
        _center = new Point3D(sphere._center);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getCenter() {
        return _center;
    }
    public void setCenter(Point3D center) {
        _center = new Point3D(center);
    }
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
    public Vector getNormal(Point3D point) {
        // TODO Auto-generated method stub
        return null;
    }
}
