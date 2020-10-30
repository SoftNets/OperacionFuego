package com.posicion.backend.apirest.models.entity;

import java.util.ArrayList;
import java.util.List;

/*Se crea esta clase para tener un pojo para las coordenadas de los satelites
 * Se crea una lista con el nombre del satelite y sus coordenadas
 * */
public class CoordenadaSatelites {
	
	   private double x = 0.00;
	   private double y = 0.00;
	   private String satelite = "";
	   
	   public double getX() {
	        return x;
	    }

	    public void setX(double x) {
	        this.x = x;
	    }
	    
	    public double getY() {
	        return y;
	    }

	    public void setY(double y) {
	        this.y = y;
	    }
	    
	    public String getSatelite() {
			return satelite;
		}

		public void setSatelite(String satelite) {
			this.satelite = satelite;
		}

}
