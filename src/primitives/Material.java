package main.primitives;

public class Material {

	private double _Kd; // Diffusion attenuation coefficient 
	private double _Ks; // Specular attenuation coefficient
	private double _Kr; // Reflection coefficient (1 for mirror)
	private double _Kt; // Refraction coefficient (1 for transparent) 
	private double _n;  // Refraction index
	
	public Material(Material material){
		this._Kd = material._Kd;
		this._Ks = material._Ks;
		this._Kr = material._Kr;
		this._Kt = material._Kt;
		this._n  = material._n;
	}
	
	public Material() {
		_Kd = 1; 
		_Ks = 1;
		_Kr = 0; 
		_Kt = 0;
		_n  = 1;
	}

	public double getKd() { return _Kd;	}
	public double getKs() { return _Ks;	}
	public double getKr() {	return _Kr;	}
	public double getKt() {	return _Kt;	}
	public double getN()  { return _n;  }
	
	public void setKd(double _Kd) { this._Kd = _Kd;	}
	public void setKs(double _Ks) {	this._Ks = _Ks;	}
	public void setKr(double _Kr) {	this._Kr = _Kr;	}
	public void setKt(double _Kt) { this._Kt = _Kt; }
	public void setN (double _n)  {	this._n = _n;	}
	
}
