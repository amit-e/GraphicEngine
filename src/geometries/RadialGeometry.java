package geometries;

public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    // ***************** Constructors ********************** //
    public RadialGeometry() {
    }
    public RadialGeometry(double radius) {
        _radius = radius;
    }

    // ***************** Getters/Setters ********************** //
    public double getRadius() {
        return _radius;
    }
    public void setRadius(double radius) {
        _radius = radius;
    }
}
