package com.posicion.backend.apirest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posicion.backend.apirest.models.entity.CoordenadaSatelites;
import com.posicion.backend.apirest.models.entity.Respuesta;
import com.posicion.backend.apirest.models.entity.Satelites;
import com.posicion.backend.apirest.models.entity.Satellites;
import com.posicion.backend.apirest.models.service.ISateliteService;

@RestController
//@RequestMapping("/topsecret")
public class PosicionRestController {

	Logger logger = LoggerFactory.getLogger(PosicionRestController.class);
	 
	@Autowired
	ISateliteService  sateliteService;
	
	@GetMapping("/listSatellites")
	public List<CoordenadaSatelites> listSatelites(){
		return sateliteService.listCoordSatelites();
	}
	
	//logger.info("mirar el satelite: "+satelites.getName());
	@GetMapping("/topsecret_split/")
    	public ResponseEntity<Respuesta>  sateliteByName(@RequestParam(value = "name") String name ){
		
		 //logger.info("variable "+name);
		 Respuesta respuesta = new  Respuesta();
		 double[] posicion   = new double[2];
		 posicion[0] = 100.0;
		 posicion[1] = 200.0;
		 respuesta.setPosicion(posicion);
		 respuesta.setMessage("ok");
		 return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/topsecret_split", method = RequestMethod.POST,
			 consumes= {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Respuesta> topSecret_split(@RequestParam(value = "name") String name, @RequestBody Satelites satelites ) {
		
		logger.info(Double.toString(satelites.getDistance()));
		
		// se obtiene los datos del reuquest para el satelite a informar
		
		double distance1 =  0.0, distance2 =  0.0, distance3 =  0.0;
		String[] mensaje1 = null, mensaje2 = null, mensaje3 = null;
		
		distance1 = satelites.getDistance();
		
		mensaje1 = satelites.getMessage();
				
		//se ejecuta el metodo getLocation para conocer las coordenas de la nave
		CoordenadaSatelites coord = sateliteService.getLocation(distance1, distance2, distance3);
		
		//se ejecuta el servicio getMessage para conocer los mensajes recibidos por las naves
		String mensajeRespuesta = sateliteService.getMessage1(mensaje1);
		
		// se arma la respuesta la cual se envia como salida 
		Respuesta respuesta = new  Respuesta();
		double[] posicion   = new double[2];
		posicion[0] = coord.getX();
		posicion[1] = coord.getY();
		respuesta.setPosicion(posicion);
		respuesta.setMessage(mensajeRespuesta);
		
		 return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
			
	
	@RequestMapping(value = "/topsecret", method = RequestMethod.POST,
			 consumes= {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Respuesta> topSecret(@RequestBody Satelites satelites ) {
		
		double distance1 =  0.0, distance2 =  0.0, distance3 =  0.0;
		String[] mensaje1 = null, mensaje2 = null, mensaje3 = null;
		
		//se capturan los datos de las distancias y el mensaje enviado en payload del servicio.
		if (satelites.getSatellites().size() > 0) {
		  for (Satelites sat: satelites.getSatellites()) {
		    	logger.info("Satelites "+sat.getName());
				 switch (sat.getName()) 
		    	 {
		    	 case "kenobi":
		    		//coordenada 1
		    		 distance1 = sat.getDistance();
		    		 mensaje1 =  sat.getMessage();
		    		
		    	 case "skywalker":
		    		//coordenada 2
		    		 distance2 = sat.getDistance();
		    		 mensaje2 =  sat.getMessage();
		    	 case "sato":  
		           //coordenada 3
		    		 distance3 = sat.getDistance();
		    		 mensaje3 =  sat.getMessage();
		       }
		 }
		}
		
		//se ejecuta el metodo getLocation para conocer las coordenas de la nave
		CoordenadaSatelites coord = sateliteService.getLocation(distance1, distance2, distance3);
		
		//se ejecuta el servicio getMessage para conocer los mensajes recibidos por las naves
		String mensajeRespuesta = sateliteService.getMessage(mensaje1, mensaje2, mensaje3);
		 
		// se arma la respuesta la cual se envia como salida 
		 Respuesta respuesta = new  Respuesta();
		 double[] posicion   = new double[2];
		 posicion[0] = coord.getX();
		 posicion[1] = coord.getY();
		 respuesta.setPosicion(posicion);
		 respuesta.setMessage(mensajeRespuesta);
		 
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

}
