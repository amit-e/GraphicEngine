package geometries;

import primitives.*;
import java.util.List;

public class Plane extends Geometry {
    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //
    public Plane() {
        _normal = new Vector();
        _Q = new Point3D();
    }
    public Plane (Vector normal, Point3D q) {
        _normal = normal;
        _Q = q;
    }
    public Plane (Plane plane) {
        _normal = plane._normal;
        _Q = plane._Q;
    }

    // ***************** Getters/Setters ********************** //
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }
    public Point3D getQ() {
        return _Q;
    }
    public void setNormal(Vector normal) {
        _normal = new Vector(normal);
    }
    public void setQ(Point3D q) {
        _Q = new Point3D(q);
    }

    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
}