package com.example.controller;


import com.example.model.Movimiento;
import com.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;


	// --------------------------------------------
	// Obtener datos - consultas select
	// --------------------------------------------
	
	/**
	 * Obtiene todos los movimientos de una tarjeta
	 * @param idTarjeta
	 * @return lista de movimientos de la tarjeta
	 */
	@GetMapping("/tarjeta/{idTarjeta}")
	public List<Movimiento> obtenerTodosLosMovimientosPorTarjeta(@PathVariable Long idTarjeta){
		return movimientoService.obtenerMovimientosDeTarjeta(idTarjeta);
	}
	
	/**
	 * Obtenemos todos los movimientos de una cuenta
	 * @param idCuenta
	 * @return Lista de movimientos de la cuenta
	 */
	@GetMapping("/cuenta/{idCuenta}")
	public List<Movimiento> obtenerTodosLosMovimientosPorCuenta(@PathVariable Long idCuenta){
		return movimientoService.obtenerMovimientosDeCuenta(idCuenta);
	}

	/**
	 * Obtenemos todos los movimientos de un usuario
	 * @param idUsuario
	 * @return Lista de movimientos del usuario
	 */
	@GetMapping("/usuario/{idUsuario}")
	public List<Movimiento> obtenerTodosLosMovimientosPorUsuario(@PathVariable Long idUsuario){
		return movimientoService.obtenerMovimientosDeUsuario(idUsuario);
	}

	/**
	 * Obtenemos todos los movimientos de un usuario (V2 con JPQL)
	 * @param idUsuario
	 * @return Lista de movimientos del usuario
	 */
	@GetMapping("/usuario/v2/{idUsuario}")
	public List<Movimiento> obtenerTodosLosMovimientosPorUsuarioV2(@PathVariable Long idUsuario){
		return movimientoService.obtenerMovimientosDeUsuarioV2(idUsuario);
	}


	// --------------------------------------------
	// Crear
	// --------------------------------------------

	/**
	 * Metodo para guardad nuevo movimiento en la base de datos
	 * @param movimientoNuevo movimiento que se quiere almacenar en la base de datos
	 * @return devuelve el objeto guardado en la base de datos de tipo Movmiento
	 */
	@PostMapping
	public ResponseEntity<Movimiento> crearMovimiento(@RequestBody Movimiento movimientoNuevo) {
		try {
			Movimiento movimientoGuardado = movimientoService.crearMovimiento(movimientoNuevo);
			if(movimientoGuardado != null && movimientoGuardado.getId()!=null) {
				return ResponseEntity.ok().body(movimientoGuardado);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
