package com.example.controller;

import com.example.model.Cuenta;
import com.example.model.Tarjeta;
import com.example.service.CuentaService;
import com.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/API/cuentas")
public class CuentaController {
	
	@Autowired 
	private CuentaService cuentaService;

	@Autowired
	private MovimientoService movimientoService;

	/**
	 * Crear una nueva nueva cuenta
	 * @param cuenta cuenta que queremos crear y guardar en la base de datos
	 * @return Devuelve la cuenta creada
	 */
	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){

		if(cuenta.getId() == null){
			Cuenta cuentaCreada = cuentaService.crearCuenta(cuenta);
			if(cuentaCreada != null) {
				return ResponseEntity.ok().body(cuentaCreada);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Devuelve la cuenta según su id
	 * @param idCuenta id de la cuenta
	 * @return Cuenta
	 */
	@GetMapping("/{idCuenta}")
	public ResponseEntity<Cuenta> obtenerCuentaById(@PathVariable("idCuenta")Long idCuenta){
		try {
			Cuenta cuenta = cuentaService.obtenerCuentaById(idCuenta);			
			return ResponseEntity.ok().body(cuenta);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Obtenemos todas las tarjetas de la cuenta cuyo id se pasa por parámetro
	 * @param idCuenta id de la cuenta
	 * @return Lista de tarjetas asociadas a la cuenta
	 */
	@GetMapping("/tarjetas/{idCuenta}")
	public List<Tarjeta> obtenerTodasTarjetasCuenta(@PathVariable("idCuenta")Long idCuenta){
		try {
			Cuenta cuenta = cuentaService.obtenerCuentaById(idCuenta);
			return cuenta.getTarjetas();
		}catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	/**
	 * Obtiene la lista de todas las cuentas que pertenecen al usuario con el id pasado por parámetro
	 * @param idUsuario id del usuario
	 * @return Lista de cuentas del usuario
	 */
	@GetMapping("/usuario/{idUsuario}")
	public List<Cuenta> obtenerTodasCuentasByUsuarioId(@PathVariable("idUsuario") Long idUsuario){
		return cuentaService.obtenerTodasCuentasByUsuarioId(idUsuario);
	}
	
	
}
