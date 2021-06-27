package com.example.service;

import com.example.model.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MovimientoService {

	/**
	 * Obtiene todos los movimientos realizados con la tarjeta indicada
	 * @param idTarjeta
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeTarjeta(Long idTarjeta);

	/**
	 * Obtiene todos los movimientos realizados con la cuenta indicada
	 * @param idCuenta
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeCuenta(Long idCuenta);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (es decir, de todas sus cuentas)
	 * @param idUsuario
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeUsuario(Long idUsuario);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (V2 con JPQL)
	 * @param idUsuario
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeUsuarioV2(Long idUsuario);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (V3 con JPQL)
	 * @param idUsuario
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeUsuarioV3(Long idUsuario);

	/**
	 * Obtiene todos los movimientos de una cuenta ordenados por fecha DESC
	 * @param idCuenta
	 * @return lista de movimientos
	 */
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaDESC(Long idCuenta);

	/**
	 * Obtiene todos los movimientos de una cuenta ordenados según parámetro indicado
	 * @param idCuenta
	 * @param sort
	 * @return
	 */
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenados(Long idCuenta, Sort sort);

	/**
	 * Obtiene todos los movimientos paginados
	 * @param pageable
	 * @return
	 */
	public Page<Movimiento> obtenerMovimientosPagina(Pageable pageable);

	/**
	 * Obtiene los movimientos de una cuenta paginados y ordenados por fecha desc
	 * @param idCuenta
	 * @param pageable
	 * @return
	 */
	public Page<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaPagina(Long idCuenta, Pageable pageable);

	
	public List<Movimiento> obtenerMovimientoFechaCuenta(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientosFechaUsuario(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public Movimiento crearMovimiento(Movimiento movimientoNuevo) throws Exception;
}
