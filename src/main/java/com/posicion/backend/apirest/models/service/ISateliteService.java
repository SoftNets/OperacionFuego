package com.posicion.backend.apirest.models.service;

import java.util.List;

import com.posicion.backend.apirest.models.entity.CoordenadaSatelites;

public interface ISateliteService {
	
	public List<CoordenadaSatelites> listCoordSatelites();
	public CoordenadaSatelites  getLocation(double distance1, double distance2, double distance3);
	public CoordenadaSatelites  getLocationSatelite(String name);
	public String getMessage(String[] message1, String[] message2, String[] message3);
	public String getMessage1(String[] message);

}
