package primitives;

public class Vector implements Comparable<Vector> {
    private Point3D _head;

    // ***************** Constructors ********************** //
    public Vector() {
        _head = new Point3D();
    }
    public Vector(Point3D head) {
        _head = new Point3D(head);
    }
    public Vector(Vector vector) {
        _head = new Point3D(vector._head);
    }
    public Vector(double xHead, double yHead, double zHead) {
        _head = new Point3D(xHead, yHead, zHead);
    }
    public Vector(Point3D p1, Point3D p2) {
        _head = new Point3D(p2);
        _head.getX().subtract(p1.getX());
        _head.getY().subtract(p1.getY());
        _head.getZ().subtract(p1.getZ());
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getHead() {
        return new Point3D(_head);
    }
    public void setHead(Point3D head) {
        _head = new Point3D(head);
    }

    // ***************** Utilities ******************** //
    public int compareTo(Vector other) {
        return _head.compareTo(other._head);
    }
    public String toString() {
        return "v"+_head.toString();
    }

    // ***************** Operations ******************** //
    public void add (Vector vector) {
        _head.add(vector);
    }

    public void subtract (Vector vector) {
        _head.subtract(vector);
    }

    public void scale(double scalingFactor) {
        double x = _head.getX().getCoordinate()*scalingFactor;
        double y = _head.getY().getCoordinate()*scalingFactor;
        double z = _head.getZ().getCoordinate()*scalingFactor;
        _head = new Point3D(x, y, z);
    }

    public Vector crossProduct(Vector vector) {
        double x = 	_head.getY().getCoordinate()*vector._head.getZ().getCoordinate() -
                _head.getZ().getCoordinate()*vector._head.getY().getCoordinate();
        double y = 	_head.getZ().getCoordinate()*vector._head.getX().getCoordinate() -
                _head.getX().getCoordinate()*vector._head.getZ().getCoordinate();
        double z = 	_head.getX().getCoordinate()*vector._head.getY().getCoordinate() -
                _head.getY().getCoordinate()*vector._head.getX().getCoordinate();

        return new Vector(x, y, z);
    }

    public double length() {
        return Math.sqrt(Math.pow(_head.getX().getCoordinate(), 2) +
                Math.pow(_head.getY().getCoordinate(), 2) +
                Math.pow(_head.getZ().getCoordinate(), 2));
    }

    public void normalize() {
        double length = this.length();
        if (length == 0)
            throw new ArithmeticException();
        double x = _head.getX().getCoordinate()/length;
        double y = _head.getY().getCoordinate()/length;
        double z = _head.getZ().getCoordinate()/length;
        _head = new Point3D(x, y, z);
    } // Throws exception if length = 0

    public double dotProduct(Vector vector) {
        return (_head.getX().getCoordinate() * vector._head.getX().getCoordinate() +
                _head.getY().getCoordinate() * vector._head.getY().getCoordinate() +
                _head.getZ().getCoordinate() * vector._head.getZ().getCoordinate());
    }
}