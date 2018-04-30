package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

    private Point3D _axisPoint;
    private Vector _axisDirection;

    // ***************** Constructors ********************** //
    public Cylinder() {
        super();
        _axisPoint = new Point3D();
        _axisDirection = new Vector();
    }
    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection) {
        super(radius);
        _axisPoint = new Point3D(axisPoint);
        _axisDirection = new Vector(axisDirection);
    }
    public Cylinder(Cylinder cylinder) {
        super(cylinder._radius);
        _axisPoint = new Point3D(cylinder._axisPoint);
        _axisDirection = new Vector(cylinder._axisDirection);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getAxisPoint() {
        return _axisPoint;
    }
    public Vector getAxisDirection() {
        return _axisDirection;
    }
    public void setAxisPoint(Point3D axisPoint) {
        _axisPoint = new Point3D(axisPoint);
    }
    public void setAxisDirection(Vector axisDirection) {
        _axisDirection = new Vector(axisDirection);
    }

    // ***************** Operations ******************** //
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector getNormal(Point3D point) {
        // TODO Auto-generated method stub
        return null;
    }
}
