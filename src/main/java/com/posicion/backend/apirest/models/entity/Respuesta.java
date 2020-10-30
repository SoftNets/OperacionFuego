package com.posicion.backend.apirest.models.entity;

public class Respuesta {

	double[] posicion   = new double[2];
	String message;
	
	public double[] getPosicion() {
		return posicion;
	}
	public void setPosicion(double[] posicion) {
		this.posicion = posicion;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
