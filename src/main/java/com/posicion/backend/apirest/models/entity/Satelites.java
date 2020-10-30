package com.posicion.backend.apirest.models.entity;

import java.util.List;
import java.util.ArrayList;


public class Satelites {
	
	private String name;
	private String[] message;
	private double distance;
	
	private List<Satelites> satellites = new ArrayList<Satelites>() ; 
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public List<Satelites> getSatellites() {
		return satellites;
	}
	public void setSatellites(List<Satelites> satellites) {
		this.satellites = satellites;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	
}
