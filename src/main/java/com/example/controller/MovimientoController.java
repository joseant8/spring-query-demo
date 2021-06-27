package com.example.controller;


import com.example.model.Movimiento;
import com.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	/**
	 * Obtenemos todos los movimientos de un usuario (V3 con JPQL)
	 * @param idUsuario
	 * @return Lista de movimientos del usuario
	 */
	@GetMapping("/usuario/v3/{idUsuario}")
	public List<Movimiento> obtenerTodosLosMovimientosPorUsuarioV3(@PathVariable Long idUsuario){
		return movimientoService.obtenerMovimientosDeUsuarioV3(idUsuario);
	}

	/**
	 * Obtenemos todos los movimientos de una cuenta ordenados por fecha (JPQL)
	 * @param idCuenta
	 * @return Lista de movimientos de la cuenta
	 */
	@GetMapping("/cuenta/orden/fecha/desc/{idCuenta}")
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaDESC(@PathVariable Long idCuenta){
		return movimientoService.obtenerMovimientosDeCuentaOrdenadosFechaDESC(idCuenta);
	}

	/**
	 * Obtenemos todos los movimientos de una cuenta ordenados (JPQL)
	 * @param idCuenta
	 * @return Lista de movimientos de la cuenta
	 */
	@GetMapping("/cuenta/orden/{idCuenta}")
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenados(@PathVariable Long idCuenta, @RequestParam(name="fecha", required = false) String fecha){
		if(fecha != null){
			if(fecha.equals("ASC")){
				return movimientoService.obtenerMovimientosDeCuentaOrdenados(idCuenta, Sort.by(Sort.Direction.ASC, "fecha"));
			}else{
				return movimientoService.obtenerMovimientosDeCuentaOrdenados(idCuenta, Sort.by(Sort.Direction.DESC, "fecha"));
			}
		}
		// orden por defecto en caso de que no se indique nada
		return movimientoService.obtenerMovimientosDeCuentaOrdenados(idCuenta, Sort.by(Sort.Direction.ASC, "id"));
	}

	// --------------------------------------------
	// Obtener datos Paginados
	// --------------------------------------------


	/**
	 * Obtenemos todos los movimientos paginados
	 * @return página de movimientos
	 */
	@GetMapping("/pagina")
	public Page<Movimiento> obtenerMovimientosPagina(){

		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		int pageNumber = 1;  // La primera página es la número 0
		int pageSize = 3;
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);

		return movimientoService.obtenerMovimientosPagina(pageable);
	}

	/**
	 * Obtenemos los movimientos de una cuenta paginados y ordenados por fecha desc
	 * @param idCuenta
	 * @return página de movimientos
	 */
	@GetMapping("/cuenta/pagina/{idCuenta}")
	public Page<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaPagina(@PathVariable Long idCuenta){

		Sort sort = Sort.by(Sort.Direction.DESC, "fecha");
		int pageNumber = 1;
		int pageSize = 3;
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);

		return movimientoService.obtenerMovimientosDeCuentaOrdenadosFechaPagina(idCuenta, pageable);
	}


	// --------------------------------------------
	// Crear
	// --------------------------------------------

	/**
	 * Método para guardar nuevo movimiento en la BD
	 * @param movimientoNuevo
	 * @return devuelve el nuevo movimiento creado
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
