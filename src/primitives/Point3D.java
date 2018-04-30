package primitives;

public class Point3D extends Point2D implements Comparable<Point3D> {
    protected Coordinate _z;

    // ***************** Constructors ********************** //
    public Point3D() {
        _z = new Coordinate(0);
    }
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        _z = z;
    }
    public Point3D(double x, double y, double z) {
        super(x, y);
        _z = new Coordinate(z);
    }
    public Point3D(Point3D point3D) {
        super(point3D._x, point3D._y);
        _z = new Coordinate(point3D._z);
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getZ() {
        return _z;
    }
    public void setZ(Coordinate z) {
        _z = z;
    }

    // ***************** Utilities ******************** //
    @Override
    public int compareTo(Point3D other) {
        if (_x.compareTo(other._x) == 0  && _y.compareTo(other._y) == 0 && _z.compareTo(other._z) == 0)
            return 0;
        else if (_x.compareTo(other._x) != 0)
            return _x.compareTo(other._x);
        else if (_y.compareTo(other._y) != 0)
            return _y.compareTo(other._y);
        else
            return _z.compareTo(other._z);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", _x.toString(), _y.toString(), _z.toString());
    }

    // ***************** Operations ******************** //
    public void add(Vector vector) {
        _x.add(vector.getHead()._x);
        _y.add(vector.getHead()._y);
        _z.add(vector.getHead()._z);
    }
    public void subtract(Vector vector) {
        _x.subtract(vector.getHead()._x);
        _y.subtract(vector.getHead()._y);
        _z.subtract(vector.getHead()._z);
    }
    public double distance(Point3D point) {
        double xDiff = this._x.getCoordinate() - point._x.getCoordinate();
        double yDiff = this._y.getCoordinate() - point._y.getCoordinate();
        double zDiff = this._z.getCoordinate() - point._z.getCoordinate();

        return Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
    }
}

