package com.posicion.backend.apirest.models.entity;

import java.util.ArrayList;
import java.util.List;

public class Satellites {
	
	private List<Satelites>  satellites;
	
	public List<Satelites> getSatellites() {
		
		satellites = new ArrayList<Satelites>();
	  
		return satellites;
	}

	public void setSatellites(List<Satelites> satellites) {
		this.satellites = satellites;
	}
	
	
}
