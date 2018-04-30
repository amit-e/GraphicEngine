/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 *
 * @author Amichay
 */
public class Material 
{
    private double _Kd; // Diffusion attenuation coefficient
    private double _Ks; // Specular attenuation coefficient
    private double _Kr; // Reflection coefficient (1 for mirror)
    private double _Kt; // Refraction coefficient (1 for transparent)
    private double _n; // Refraction index
    // ***************** Constructors ********************** //
    public Material()
    {
        _Kd = 1;
        _Ks = 1;
        _Kr = 0;
        _Kt = 0;
        _n = 1;
    }
    public Material(Material material)
    {
        _Kd = material.getKd();
        _Ks = material.getKs();
        _Kr = material.getKr();
        _Kt = material.getKt();
        _n = material.getN();
    }
    // ***************** Getters/Setters ********************** //
    public double getKd()
    {
        return new Double(_Kd);
    }
    public double getKs()
    {
        return new Double(_Ks);
    }
    public double getKr()
    {
        return new Double(_Kr);
    }
    public double getKt()
    {
        return new Double(_Kt);
    }
    public double getN()
    {
        return new Double(_n);
    }
    public void setKd(double Kd)
    {
        _Kd = new Double(Kd);
    }
    public void setKs(double Ks)
    {
        _Ks = new Double(Ks);
    }
    public void setKr(double Kr)
    {
        _Kr = new Double(Kr);
    }
    public void setKt(double Kt)
    {
        _Kt = new Double(Kt);
    }
    public void setN (double n)
    {
        _n = new Double(n);
    }

}
