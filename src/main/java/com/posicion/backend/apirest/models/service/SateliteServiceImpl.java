package com.posicion.backend.apirest.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.posicion.backend.apirest.models.entity.CoordenadaSatelites;


/* 
 * Se implementan los servicos que se utilian para la implementar el modelo del negocio requerido
 *  */
@Service
public class SateliteServiceImpl implements ISateliteService{
	

    Logger logger = LoggerFactory.getLogger(SateliteServiceImpl.class);
    
    
	// se crea una lista para tener las coordenadas registradas de cada satelite
	@Override
	public List<CoordenadaSatelites> listCoordSatelites() {
	    
		List<CoordenadaSatelites> coordSatelites = new ArrayList<CoordenadaSatelites>() ; 
		
		CoordenadaSatelites sat1 = new CoordenadaSatelites();
		CoordenadaSatelites sat2 = new CoordenadaSatelites();
		CoordenadaSatelites sat3 = new CoordenadaSatelites();

		sat1.setSatelite("kenobi");				
		sat1.setX(-500);
		sat1.setY(-200);
		
		sat2.setSatelite("skywalker");				
		sat2.setX(100);
		sat2.setY(-100);
		
		sat3.setSatelite("sato");				
		sat3.setX(500);
		sat3.setY(100);
		
		
		coordSatelites.add(sat1);
		coordSatelites.add(sat2);
		coordSatelites.add(sat3);
		
		return coordSatelites;
		
	}	
	
	
	/*
	 * El algoritmo utilizado para realizar la triangulacion se tomo de un aporte encontrado 
	 * trilateration-method-android-java
	 * */
	@Override
	public CoordenadaSatelites  getLocation(double distance1,
			 				    double distance2,
			 				    double distance3){
		
		logger.info(Double.toString(distance1));
	    logger.info(Double.toString(distance2));
	    logger.info(Double.toString(distance3));

    //Declaracion de variables
	CoordenadaSatelites retorno = new CoordenadaSatelites();	
	double[] coordSat1   = new double[2];
    double[] coordSat2   = new double[2];
    double[] coordSat3   = new double[2];
    
    double[] ex   = new double[2];
    double[] ey   = new double[2];
    double[] p3p1 = new double[2];
    double jval  = 0;
    double temp  = 0;
    double ival  = 0;
    double p3p1i = 0;
    double triptx;
    double xval;
    double yval;
    double t1;
    double t2;
    double t3;
    double t;
    double exx;
    double d;
    double eyy;

        
     for (CoordenadaSatelites sate: listCoordSatelites()) {
      
    	 //Transformar las coordenadas en vectores de la posicion de los  satelites
    	 logger.info(sate.getSatelite());
    	 
    	 switch (sate.getSatelite()) 
	      {
	    	 case "kenobi":
	    		//coordenada 1
	    		coordSat1[0] = sate.getX();
	    		coordSat1[1] = sate.getY();
	    	 case "skywalker":
	    		//coordenada 2
	            coordSat2[0] = sate.getX();
	            coordSat2[1] = sate.getY();
	    	 case "sato":  
	           //coordenada 3
	           coordSat3[0] = sate.getX();
	           coordSat3[1] = sate.getY();
	       }
     }
    
    for (int i = 0; i < coordSat1.length; i++) {
        t1   = coordSat2[i];
        t2   = coordSat1[i];
        t    = t1 - t2;
        temp += (t*t);
    }
    d = Math.sqrt(temp);
    for (int i = 0; i < coordSat1.length; i++) {
        t1    = coordSat2[i];
        t2    = coordSat1[i];
        exx   = (t1 - t2)/(Math.sqrt(temp));
        ex[i] = exx;
    }
    for (int i = 0; i < coordSat3.length; i++) {
        t1      = coordSat3[i];
        t2      = coordSat1[i];
        t3      = t1 - t2;
        p3p1[i] = t3;
    }
    for (int i = 0; i < ex.length; i++) {
        t1 = ex[i];
        t2 = p3p1[i];
        ival += (t1*t2);
    }
    for (int  i = 0; i < coordSat3.length; i++) {
        t1 = coordSat3[i];
        t2 = coordSat1[i];
        t3 = ex[i] * ival;
        t  = t1 - t2 -t3;
        p3p1i += (t*t);
    }
    for (int i = 0; i < coordSat3.length; i++) {
        t1 = coordSat3[i];
        t2 = coordSat1[i];
        t3 = ex[i] * ival;
        eyy = (t1 - t2 - t3)/Math.sqrt(p3p1i);
        ey[i] = eyy;
    }
    for (int i = 0; i < ey.length; i++) {
        t1 = ey[i];
        t2 = p3p1[i];
        jval += (t1*t2);
    }
    xval = (Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(d, 2))/(2*d);
    yval = ((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(ival, 2) + Math.pow(jval, 2))/(2*jval)) - ((ival/jval)*xval);

    t1 = coordSat1[0];
    t2 = ex[0] * xval;
    t3 = ey[0] * yval;
    triptx = t1 + t2 + t3;
    retorno.setX(triptx);
    t1 = coordSat1[1];
    t2 = ex[1] * xval;
    t3 = ey[1] * yval;
    triptx = t1 + t2 + t3;
    retorno.setY(triptx);
    
    logger.info("Coordenada posicion X "+Double.toString(retorno.getX()));
    logger.info("Coordenada posicion y "+Double.toString(retorno.getY()));
    
    return retorno;
}

	@Override
	public String getMessage(String[] message1, String[] message2, String[] message3) {
		
		for (int i=0; i<message2.length;i++) {
			logger.info(message2[i]); 
		}
		//expresion para comparar que exista solo letras numeros y espacios en la cadena
		//String patron =   "^[A-Za-z0-9\s]+$";
		String patron =   "[a-zA-Z0-9_]*";
		//Variables
		int lengMensaje = 0;
		//String[] mensajeEntregado = null;
		
		String mensajeEntrega = "";
		  
		for (int i=0; i< message1.length; i++) {
			
		    if (!Pattern.matches(patron, message1[i])) {
		    	message1[i] = "";
		    }
		}
		
		for (int i=0; i< message2.length; i++) {
			
		    if (!Pattern.matches(patron, message2[i])) {
		    	message2[i] = "";
		    }
			
		}
		
		for (int i=0; i< message3.length; i++) {
			
		    if (!Pattern.matches(patron, message3[i])) {
		    	message3[i] = "";
		    }
			
		}
		
		// determinar el patron del desfase de los mensajes
		
		// 1:calculamos la longitud de cada mensaje
		if (message1.length > message2.length && message1.length > message3.length) {
			lengMensaje = message1.length;
		}else {
			if (message2.length > message1.length && message2.length > message3.length) {
				lengMensaje = message2.length;
			}
			else {
				lengMensaje = message3.length;
			}	
		}
		
		String mensajeEntregado[] = new String[lengMensaje];
		
		// recoremos la cadena y comparamos la poscion de las cadenas si se encuentra un coincidencia 
		// se toma como la palabra que debe ir en esa posicion
		// si se encuentran espacios se busca la cadena que no este vacia y se se toma como la palabra que debe ir en esa posicion
		
		for (int i=0; i< lengMensaje; i++) {
			
			 if (message1[i].equals("") &&  message2[i].equals("") && !message3[i].equals("") ) {
				 mensajeEntregado[i] = message3[i];
			   } else 
				  if ((!message1[i].equals("") && !message2[i].equals("")) && (message1[i].equals(message2[i]))) {
					        mensajeEntregado[i] = message1[i];
					 } else 
						 if ((!message1[i].equals("") && !message3[i].equals("")) && (message1[i].equals(message3[i]))) {
							 mensajeEntregado[i] = message1[i];
					  } 
			    	 
		    	
		      if (message1[i].equals("") &&  message3[i].equals("") && !message2[i].equals("")) {
            		
		    	  mensajeEntregado[i] = message2[i];
		      } else if ((!message2[i].equals("") && !message3[i].equals("")) && (message2[i].equals(message3[i]))){
		    	 mensajeEntregado[i] = message2[i];
		      } else 
					 if ((!message1[i].equals("") && !message2[i].equals("")) && (message1[i].equals(message2[i]))) {
						 mensajeEntregado[i] = message1[i];
				  } 
		    	 
		     
		     if (message2[i].equals("") &&  message3[i].equals("") && !message1[i].equals("")) {
            		
		    	 mensajeEntregado[i] = message1[i];
             } else  if ((!message1[i].equals("") && !message2[i].equals("")) && (message1[i].equals(message2[i]))) {
		    	 	     mensajeEntregado[i] = message1[i];
             } else 
				 if ((!message2[i].equals("") && !message3[i].equals("")) && (message2[i].equals(message3[i]))) {
					 mensajeEntregado[i] = message2[i];
			  } 
            
		     
           
		  }

		for (int i=0; i< mensajeEntregado.length; i++) {
			mensajeEntrega += mensajeEntregado[i]+" "; 
		}
		
		logger.info("Salida  "+ mensajeEntrega);
        return mensajeEntrega;
        
	}
	
	
	@Override
	public String getMessage1(String[] message) {
		
		String mensajeEntrega = null;
		int palabrasFormadas = 0;
		for (int i=0; i< message.length; i++) {
			if (message[i].equals("")) {
				palabrasFormadas++;
			}else {
			   mensajeEntrega += message[i]+" "; 
			}
		}
		// si el numero de palabras es menor a la longuitud del mensaje se envia un mensaje de error
		if (palabrasFormadas > message.length/2) {
			mensajeEntrega = "Error no se pudo decifrar bien el mensaje.";
		}
		
		logger.info("Salida  "+ mensajeEntrega);
        return mensajeEntrega;
	}

	@Override
	public CoordenadaSatelites getLocationSatelite(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
