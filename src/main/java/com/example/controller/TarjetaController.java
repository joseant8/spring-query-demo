package com.example.controller;

import com.example.model.Tarjeta;
import com.example.service.MovimientoService;
import com.example.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/API/tarjetas")
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private MovimientoService movimientoService;
	
	/**
	 * Crea una nueva tarjeta en la DB
	 * @param tarjetaNueva la tarjeta que queremos crear
	 * @return la tarjeta creada
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjetaNueva) throws URISyntaxException {
		// Llamamos al service para crear la tarjetas
		Tarjeta tarjeta = tarjetaService.crearTarjeta(tarjetaNueva);
		
		// Nos aseguramos de que se ha creado la nueva tarjeta
		if(tarjeta == null || tarjeta.getId() == null) {
			// En caso de no haberse creado correctamente se devolvera un Error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return ResponseEntity.ok().body(tarjeta);
	}
	
	/**
	 * Obtenemos la tarjeta a través del id pasado por parámetro
	 * @param idTarjeta id de la tarjeta
	 * @return tarjeta obtenida de la base de datos
	 */
	@GetMapping(value = "/{idTarjeta}")
	public ResponseEntity<Tarjeta> obtenerTarjetaById(@PathVariable("idTarjeta") Long idTarjeta) {
		try {
		Tarjeta tarjeta =  tarjetaService.obtenerTarjetaById(idTarjeta);
        return ResponseEntity.ok().body(tarjeta);
		}catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
