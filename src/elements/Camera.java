/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import primitives.*;
/**
 * @author Amichay
 * @author yehuda
 */
public class Camera 
{
    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    // ***************** Constructors ********************** //
    public Camera()
    {
        _P0 = new Point3D();
        _vUp = new Vector();
        _vTo = new Vector();
        _vRight = new Vector();
    }
    public Camera (Camera camera)
    {
        _P0 = camera.getP0();
        _vUp = camera.get_vUp();
        _vTo = camera.get_vTo();
        _vRight = camera.get_vRight();
    }
    public Camera (Point3D P0, Vector vUp, Vector vTo)
    {
        setP0(P0);
        set_vUp(vUp);
        set_vTo(vTo);
        _vRight = _vUp.crossProduct(_vTo);
        
       _vRight.normalize();
    }
    //public Camera (Map<String, String> attributes);
    // ***************** Getters/Setters ********************** //
    public Vector get_vUp()
    {
        return new Vector(_vUp);
    }
    public void set_vUp(Vector vUp)
    {
        _vUp = new Vector(vUp);
        _vUp.normalize();
    }
    public Vector get_vTo()
    {
        return new Vector(_vTo);
    }
    public void set_vTo(Vector vTo)
    {
        _vTo = new Vector(vTo);
        _vTo.normalize();
    }
    public Point3D getP0()
    {
        return new Point3D(_P0);
    }
    public void setP0(Point3D P0)
    {
        _P0 = new Point3D(P0);
    }
    public Vector get_vRight()
    {
        return new Vector(_vRight);
    }
    // ***************** Administration ********************** //
    @Override
    public String toString()
    {
        return String.format("from: %s \n vUp: %s vTo: %s vRight: %s ",_P0.toString(),_vUp.toString(),
                _vTo.toString(), _vRight.toString());
    }
    // ***************** Operations ******************** //
    //Returns the rays that exit from the camera and pass through each pixel on the screen
    public Ray constructRayThroughPixel (int Nx, int Ny,double x, double y,double screenDist,
            double screenWidth,double screenHeight)
    {
        Point3D _pc = new Point3D(_P0);
        Vector tmp = new Vector(_vTo);
        tmp.scale(screenDist);
        _pc.add(tmp);
        double Rx = screenWidth/Nx;
        double Ry = screenHeight/Ny;
        
        Vector tmp1 = new Vector(_vRight);
        Vector tmp2 = new Vector(_vUp);
        tmp1.scale((x- (Nx/2.0))*Rx +(Rx/2));
        tmp2.scale((y- (Ny/2.0))*Ry +(Ry/2));
        tmp1.subtract(tmp2);
        _pc.add(tmp1);
        
        return new Ray(_pc, new Vector(_P0,_pc));
    }
}
