package primitives;

public class Ray implements Comparable<Ray> {
    // Point of origin
    private Point3D _POO;
    // Ray direction
    private Vector _direction;

    // ***************** Constructors ********************** //
    public Ray() {
        _POO = new Point3D();
        _direction = new Vector();
    }
    public Ray(Point3D POO, Vector direction) {
        _POO = new Point3D(POO);
        _direction = new Vector(direction);
    }
    public Ray(Ray ray) {
        _POO = new Point3D(ray._POO);
        _direction = new Vector(ray._direction);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getPOO() {
        return _POO;
    }
    public Vector getDirection() {
        return _direction;
    }
    public void setPOO(Point3D POO) {
        _POO = new Point3D(POO);
    }
    public void setDirection(Vector direction) {
        _direction = new Vector(direction);
    }

    // ***************** Utilities ******************** //
    @Override
    public int compareTo(Ray other) {
        if ((this._POO).compareTo(other._POO) == 0)
            if ((this._direction).compareTo(other._direction) == 0)
                return 0;
        return 1;
    }
    @Override
    public String toString() {
        return "POO: "+ _POO + " Direction: " + _direction;
    }
}