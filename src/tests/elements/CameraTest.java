package tests;

import org.junit.Test;
import main.primitives.Point3D;
import main.primitives.Vector;
import main.primitives.Ray;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



public class CameraTest {
	
	@Test
	public void testRaysConstruction(){
		
		final int WIDTH  = 3;
		final int HEIGHT = 3;
		
		Point3D[][] screen = new main.primitives.Point3D[HEIGHT][WIDTH];
		
		main.elements.Camera camera = new main.elements.Camera(new Point3D(0.0 ,0.0 ,0.0),
								   new Vector (0.0, 1.0, 0.0),
								   new Vector (0.0, 0.0, -1.0));
		
		System.out.println("Camera:\n" + camera);
		
		for (int i = 0; i < HEIGHT; i++){
			for (int j = 0; j < WIDTH; j++){
				
				Ray ray = camera.constructRayThroughPixel(
						WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
				
				screen[i][j] = ray.getPOO();
				
				System.out.print(screen[i][j]);
				System.out.println(ray.getDirection());
				
				// Checking z-coordinate
				assertTrue(Double.compare(screen[i][j].getZ().getCoordinate(), -1.0) == 0);
				
				// Checking all options
				double x = screen[i][j].getX().getCoordinate();
				double y = screen[i][j].getX().getCoordinate();
				
				if (Double.compare(x, 3) == 0 || 
					Double.compare(x, 0) == 0 ||
					Double.compare(x, -3) == 0){
						if (Double.compare(y, 3) == 0 || 
							Double.compare(y, 0) == 0 ||
							Double.compare(y, -3) == 0){
								assertTrue(true);
						} else {
							fail("Wrong y coordinate");
						}
				} else {
					fail("Wrong x coordinate");
				}
				
			}
			System.out.println("--");
		}
		
	}

}
