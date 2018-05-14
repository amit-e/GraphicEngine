package main.scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.elements.AmbientLight;
import main.elements.Camera;
import main.elements.LightSource;
import main.geometries.Geometry;

public class Scene {

	private Color _background;
	private AmbientLight _ambientLight;
	private List<Geometry> _geometries = new ArrayList<Geometry>();
	private Camera _camera;
	private double _screenDistance;
	private List<LightSource> _lights = new ArrayList<LightSource>();
	
	private String _sceneName = "scene";
	
	// ***************** Constructors ********************** // 
	
	public Scene(){
		_background = new Color(0, 0, 0);
		_ambientLight = new AmbientLight();
		setCamera(new Camera());
		_screenDistance = 100;
	}
	
	public Scene (Scene scene){
		_background = scene.getBackground();
		_ambientLight = scene.getAmbientLight();
		_geometries = scene._geometries;
		_lights = scene._lights;
		_camera = scene.getCamera();
		_screenDistance = scene._screenDistance;
	}
	
	public Scene(AmbientLight aLight, Color background, 
			Camera camera, double screenDistance){	
		
		_background = background;
		_ambientLight = new AmbientLight(aLight);
		setCamera(new Camera(camera));
		_screenDistance = screenDistance;
	}

	// ***************** Getters/Setters ********************** //
	
	public Color        getBackground()     { return _background;                     }
	public AmbientLight getAmbientLight()   { return new AmbientLight(_ambientLight); }
	public Camera       getCamera()         { return new Camera(_camera);         	  }
	public String       getSceneName()      { return _sceneName;					  }
	public double       getScreenDistance() { return _screenDistance;	              }
	
	public void setBackground(Color _background)           { this._background = _background;                       }
	public void setAmbientLight(AmbientLight ambientLight) { this._ambientLight = new AmbientLight(_ambientLight); }
	public void setCamera(Camera camera)                   { this._camera = new Camera(camera);                    }
	public void setSceneName(String sceneNAme)             { this._sceneName = sceneNAme;						   }
	public void setScreenDistance(double screenDistance)   { this._screenDistance = screenDistance;          	   }
	
	// ***************** Operations ******************** // 
	
	public void addGeometry(Geometry geometry){
		_geometries.add(geometry);
	}
	
	public Iterator<Geometry> getGeometriesIterator(){
		return _geometries.iterator();
	}
	
	public void addLight(LightSource light){
		_lights.add(light);
	}
	
	public Iterator<LightSource> getLightsIterator(){
		return _lights.iterator();
	}
	
}
