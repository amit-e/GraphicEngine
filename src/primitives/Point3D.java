package main.primitives;

public class Point3D extends Point2D {

	private Coordinate _z;
	
	// ***************** Constructors ********************** // 
	
	public Point3D(){
		//super();
		setZ(new Coordinate());
	}
	
	public Point3D(Coordinate x, Coordinate y, Coordinate z){
		super(x, y);
		setZ(new Coordinate(z));
	}
	
	public Point3D(double x, double y, double z){
		super(new Coordinate(x), new Coordinate(y));
		setZ(new Coordinate(z));
	}
	
	public Point3D(Point3D point3D){
		super(point3D.getX(), point3D.getY());
		setZ(point3D.getZ());
	}
	
	// ***************** Getters/Setters ********************** // 
	
	public Coordinate getZ()        { return new Coordinate(_z);    }
	public void setZ(Coordinate _z) { this._z = new Coordinate(_z);	}


	// ***************** Administration  ******************** //
	//acts as equals
	public int compareTo(Point3D point3D) {
		
		if (((Point2D)this).compareTo((Point2D)point3D) == 0) {
			if (this._z.compareTo(point3D._z) == 0) {
				return 0;
			}
		}
		return 1;
	}
	
	public String toString(){
		return "(" + _x.getCoordinate() + ", " +
					 _y.getCoordinate() + ", " +
					 _z.getCoordinate() + ")";
	}

	// ***************** Operations ********************
	
	public void add(Vector vector) {
		
		this._x.add(vector.getHead().getX());
		this._y.add(vector.getHead().getY());
		this._z.add(vector.getHead().getZ());

	}

	public void subtract(Vector vector) {
		
		this._x.subtract(vector.getHead().getX());
		this._y.subtract(vector.getHead().getY());
		this._z.subtract(vector.getHead().getZ());
		
	}
	
	public double distance(Point3D point){
		return Math.sqrt(Math.pow(getX().getCoordinate() - point.getX().getCoordinate(), 2) +
						 Math.pow(getY().getCoordinate() - point.getY().getCoordinate(), 2) +
						 Math.pow(getZ().getCoordinate() - point.getZ().getCoordinate(), 2));
	}

}
