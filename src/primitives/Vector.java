package main.primitives;

public class Vector implements Comparable<Vector>{

	private Point3D _head;
	
	// ***************** Constructors ********************** // 
	
	public Vector()              { this._head = new Point3D();     }
	public Vector(Point3D head)  { this._head = new Point3D(head); }
	public Vector(Vector vector) { this._head = vector.getHead();  }

//	public Vector(Point2D pt, double zHead){
//		_head =  new Point3D(pt.getX(),pt.getY(),new Coordinate(zHead));
//	}

	public Vector(double xHead,  double yHead, double zHead){
		_head =  new Point3D(new Coordinate(xHead),
				 			 new Coordinate(yHead),
				 			 new Coordinate(zHead)); 
	}
	
	public Vector(Point3D p1, Point3D p2){
		
		this(p2.getX().getCoordinate() - p1.getX().getCoordinate(),
			 p2.getY().getCoordinate() - p1.getY().getCoordinate(),
			 p2.getZ().getCoordinate() - p1.getZ().getCoordinate());
		
	}
	
	// ***************** Getters/Setters ********************** // 
	
	public Point3D getHead()             { return new Point3D(_head);      }
	public void    setHead(Point3D head) { this._head = new Point3D(head); }
	
	// ***************** Administration  ******************** // 
	
	@Override
	public int compareTo(Vector vector) {
		return this._head.compareTo(vector._head);
	}
	
	public String toString(){
		return _head.toString();
	}
	
	// ***************** Operations ******************** // 
	
	public void add (Vector vector ){
		this._head.add(vector);
	}
	
	public void subtract (Vector vector ){
		this._head.subtract(vector);
	}
	
	public void scale(double scalingFacor){
		
		this._head.setX(new Coordinate(
				scalingFacor * _head.getX().getCoordinate()));
		
		this._head.setY(new Coordinate(
				scalingFacor * _head.getY().getCoordinate()));
		
		this._head.setZ(new Coordinate(
				scalingFacor * _head.getZ().getCoordinate()));
	}
	
	public Vector crossProduct (Vector vector){
		
		double x1 = this.getHead().getX().getCoordinate();
		double y1 = this.getHead().getY().getCoordinate();
		double z1 = this.getHead().getZ().getCoordinate();
		
		double x2 = vector.getHead().getX().getCoordinate();
		double y2 = vector.getHead().getY().getCoordinate();
		double z2 = vector.getHead().getZ().getCoordinate();
		
		return new Vector(y1 * z2 - z1 * y2,
						  z1 * x2 - x1 * z2,
						  x1 * y2 - y1 * x2);
					
	}

	public double length() {
		
		double x = this.getHead().getX().getCoordinate();
		double y = this.getHead().getY().getCoordinate();
		double z = this.getHead().getZ().getCoordinate();
		
		return Math.sqrt(Math.pow(x, 2) + 
		         		 Math.pow(y, 2) +
		         		 Math.pow(z, 2));
		
	}
	
	public void normalize() {
		
		double x = this.getHead().getX().getCoordinate();
		double y = this.getHead().getY().getCoordinate();
		double z = this.getHead().getZ().getCoordinate();
		
		double length = this.length();
		
		if (length == 0)
			throw new ArithmeticException();
		
		this.setHead(new Point3D(x/length, 
								 y/length,
								 z/length));
	}
	
	public double dotProduct(Vector vector) {
		
		double x1 = this.getHead().getX().getCoordinate();
		double y1 = this.getHead().getY().getCoordinate();
		double z1 = this.getHead().getZ().getCoordinate();
		
		double x2 = vector.getHead().getX().getCoordinate();
		double y2 = vector.getHead().getY().getCoordinate();
		double z2 = vector.getHead().getZ().getCoordinate();
		
		return x1 * x2 + y1 * y2 + z1 * z2;
		
	}


}
