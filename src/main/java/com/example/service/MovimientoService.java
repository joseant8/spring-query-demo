package com.example.service;

import com.example.model.Movimiento;
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
	 * @return
	 */
	public List<Movimiento> obtenerMovimientosDeUsuario(Long idUsuario);

	/**
	 * Obtiene todos los movimientos realizados por el usuario indicado (V2 con JPQL)
	 * @param idUsuario
	 * @return
	 */
	public List<Movimiento> obtenerMovimientosDeUsuarioV2(Long idUsuario);
	
	public List<Movimiento> obtenerMovimientoFechaCuenta(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientosFechaUsuario(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public Movimiento crearMovimiento(Movimiento movimientoNuevo) throws Exception;

	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFecha(Long idCuenta);
}
