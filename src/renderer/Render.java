package main.renderer;

import main.elements.LightSource;
import main.geometries.FlatGeometry;
import main.geometries.Geometry;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import main.scenes.Scene;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Render {

	private Scene _scene;
	private main.renderer.ImageWriter _imageWriter;
	
	private final int RECURSION_LEVEL = 3;
	
	// ***************** Constructors ********************** // 
	
	public Render(main.renderer.ImageWriter imageWriter, Scene scene){
		_imageWriter = new main.renderer.ImageWriter(imageWriter);
		_scene = new Scene (scene);
	}
	
	// ***************** Operations ******************** // 
	
	public void renderImage(){
	
		for (int i = 0; i < _imageWriter.getHeight(); i++){
			for (int j = 0; j < _imageWriter.getWidth(); j++){
				
				Ray ray = _scene.getCamera().constructRayThroughPixel(
						  _imageWriter.getNx(), _imageWriter.getNy(), j, i, 
						  _scene.getScreenDistance(),
						  _imageWriter.getWidth(), _imageWriter.getHeight());
				
				Entry<Geometry, Point3D> entry = findClosesntIntersection(ray);
				
				if (entry == null){
					_imageWriter.writePixel(j, i, _scene.getBackground());
				} else {
					_imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray));
				}
			}
		}
	}
	
	private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray) {
		
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
		
		if (intersectionPoints.size() == 0)
			return null;
		
		Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
		Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
		return entry;
		
	}

	public void printGrid(int interval){
		
		int height = _imageWriter.getHeight();
		int width = _imageWriter.getWidth();
		
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				
				if (i % interval == 0 || j % interval == 0)
					_imageWriter.writePixel(j, i, 255, 255, 255);
				
			}
		}
	}

	public void writeToImage(){
		_imageWriter.writeToimage();
	}
	
	private Color calcColor(Geometry geometry, Point3D point, Ray ray){
		return calcColor(geometry, point, ray, 0);
	}
	
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {
	
		
		if (level == RECURSION_LEVEL){
			return new Color(0, 0, 0);
		}
		
		Color ambientLight = _scene.getAmbientLight().getIntensity();
		Color emissionLight = geometry.getEmmission();
		
		Color inherentColors = addColors(ambientLight, emissionLight);
		
		Iterator<LightSource> lights = _scene.getLightsIterator();

		Color lightReflected = new Color(0, 0, 0);
		
		while (lights.hasNext()){
			
			LightSource light = lights.next();
	
			if (!occluded(light, point, geometry)){
			
				Color lightIntensity = light.getIntensity(point);
				
				
				Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
													   geometry.getNormal(point),
													   light.getL(point),
													   lightIntensity);
				
				
				Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),
													   new Vector(point, _scene.getCamera().getP0()),
													   geometry.getNormal(point),
													   light.getL(point),
						   							   geometry.getShininess(),
						   							   lightIntensity);
				
				lightReflected = addColors(lightDiffuse, lightSpecular);
			}
		}
		
		Color I0 = addColors(inherentColors, lightReflected);
		
		
		//**// Recursive calls
		
		// Recursive call for a reflected ray
		Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
		Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
		Color reflected = new Color(0, 0, 0);
		if (reflectedEntry != null){
			reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
			double kr = geometry.getMaterial().getKr();
			reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
		}
		
		// Recursive call for a refracted ray
		Ray refractedRay = constructRefractedRay(geometry, point, inRay);
		Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
		Color refracted = new Color(0, 0, 0);
		if (refractedEntry != null){
			refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
			double kt = geometry.getMaterial().getKt();
			refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
		}
		
		
		//**// End of recursive calls
		
		Color envColors = addColors(reflected, refracted);
		
		Color finalColor = addColors(envColors, I0);
		
		return finalColor;
	}

	private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) {
		
		Vector normal = geometry.getNormal(point);
		normal.scale(-2);
		point.add(normal);
		
		if (geometry instanceof FlatGeometry){
			return new Ray (point, inRay.getDirection());
		} else {
			// Here, Snell's law can be implemented.
			// The refraction index of both materials had to be derived
			return new Ray (point, inRay.getDirection());
		}
		
	}

	private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {
		
		Vector l = inRay.getDirection();
		l.normalize();

		normal.scale(-2 * l.dotProduct(normal));
		l.add(normal);
		
		Vector R = new Vector(l);
		R.normalize();
		
		point.add(normal);
		
		Ray reflectedRay = new Ray(point, R);
		
		return reflectedRay;
	}

	private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
		
		Vector lightDirection = light.getL(point);
		lightDirection.scale(-1);
		lightDirection.normalize();
		
		Point3D geometryPoint = new Point3D(point);
		Vector epsVector = new Vector(geometry.getNormal(point));
		epsVector.scale(2);
		geometryPoint.add(epsVector);
		
		Ray lightRay = new Ray(geometryPoint, lightDirection); 
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
		
		// Flat geometry cannot self intersect 
		if (geometry instanceof FlatGeometry){
			intersectionPoints.remove(geometry);
		}

		for (Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
			if (entry.getKey().getMaterial().getKt() == 0)
				return true;
		
		return false;
		
	}

	private Color calcSpecularComp(double ks, Vector v, Vector normal, 
								   Vector l, double shininess, Color lightIntensity) {
		
		v.normalize();
		normal.normalize();
		l.normalize();
		
		normal.scale(-2 * l.dotProduct(normal));
		l.add(normal);
		Vector R = new Vector(l);
		R.normalize();
		
		double k = 0;
		
		if (v.dotProduct(R) > 0) // prevents glowing at edges
			k = ks * Math.pow(v.dotProduct(R), shininess);
		
		return new Color ((int)(lightIntensity.getRed()  * k),
				  		  (int)(lightIntensity.getGreen() * k),
				  		  (int)(lightIntensity.getBlue()   * k));
	}

	private Color calcDiffusiveComp(double kd, Vector normal, 
									Vector l, Color lightIntensity) {
		
		normal.normalize();
		l.normalize();
		
		double k = Math.abs(kd * normal.dotProduct(l));
		
		return new Color ((int)(lightIntensity.getRed() * k),
						  (int)(lightIntensity.getGreen() * k),
						  (int)(lightIntensity.getBlue() * k));
	}

	private Map<Geometry, Point3D> getClosestPoint(
			Map<Geometry, List<Point3D>> intersectionPoints) {
		
		double distance = Double.MAX_VALUE;
		Point3D P0 = _scene.getCamera().getP0();
		Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
		
		
		for (Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet()){
			for (Point3D point : entry.getValue()){
				double pointDistance = P0.distance(point);
				if (pointDistance < distance){
					minDistancePoint.clear();
					minDistancePoint.put(entry.getKey(), new Point3D(point));
					distance = pointDistance;
				}
			}
		}
		
		return minDistancePoint;
		
	}

	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		
		Iterator<Geometry> geometries =  _scene.getGeometriesIterator();
		
		Map<Geometry, List<Point3D>> intersectionPoints = new 
				HashMap<Geometry, List<Point3D>>();
		
		while (geometries.hasNext()){
			Geometry geometry = geometries.next();
			List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
			
			if (!geometryIntersectionPoints.isEmpty())
				intersectionPoints.put(geometry, geometryIntersectionPoints);
		
		}
		
		return intersectionPoints;
	}
	
	private Color addColors(Color a, Color b){
		
		int R = a.getRed() + b.getRed();
		if (R > 255) R = 255;
		
		int G = a.getGreen() + b.getGreen();
		if (G > 255) G = 255;
		
		int B = a.getBlue() + b.getBlue();
		if (B > 255) B = 255;
		
		Color I = new Color (R, G, B);
		
		return I;
		
	}

}
