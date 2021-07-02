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
	 * @param idTarjeta id de la tarjeta
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeTarjeta(Long idTarjeta);

	/**
	 * Obtiene todos los movimientos realizados con la cuenta indicada
	 * @param idCuenta id de la cuenta
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeCuenta(Long idCuenta);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (es decir, de todas sus cuentas)
	 * @param idUsuario id del usuario
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeUsuario(Long idUsuario);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (V2 con JPQL)
	 * @param idUsuario id del usuario
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeUsuarioV2(Long idUsuario);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (V3 con JPQL)
	 * @param idUsuario id del usuario
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeUsuarioV3(Long idUsuario);

	/**
	 * Obtiene todos los movimientos de una cuenta ordenados por fecha DESC
	 * @param idCuenta id de la cuenta
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaDESC(Long idCuenta);

	/**
	 * Obtiene todos los movimientos de una cuenta ordenados según parámetro indicado
	 * @param idCuenta id de la cuenta
	 * @param sort ordenación
	 * @return lista de mivimientos
	 */
	List<Movimiento> obtenerMovimientosDeCuentaOrdenados(Long idCuenta, Sort sort);

	/**
	 * Obtiene todos los movimientos paginados
	 * @param pageable paginación
	 * @return página de movimientos
	 */
	Page<Movimiento> obtenerMovimientosPagina(Pageable pageable);

	/**
	 * Obtiene los movimientos de una cuenta paginados y ordenados por fecha DESC
	 * @param idCuenta id de la cuenta
	 * @param pageable paginación
	 * @return página de movimientos
	 */
	Page<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaPagina(Long idCuenta, Pageable pageable);

	/**
	 * Obtiene los movimientos de una cuenta según el rango de fechas indicado y ordenados por fecha DESC
	 * @param idCuenta id de la cuenta
	 * @param fechaInit fecha inicio
	 * @param fechaFin fecha fin
	 * @return lista de movimientos
	 */
	List<Movimiento> obtenerMovimientosDeCuentaByFecha(Long idCuenta, String fechaInit, String fechaFin);

	List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta, LocalDate fechaInit, LocalDate fechaFin);

	Movimiento crearMovimiento(Movimiento movimientoNuevo) throws Exception;
}
