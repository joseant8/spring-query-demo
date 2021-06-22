package com.example.service;

import com.example.model.Movimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MovimientoService {
	
	public List<Movimiento> obtenerMovimientosDeTarjeta(Long idTarjeta);
	
	public List<Movimiento> obtenerMovimientosDeCuenta(Long idCuenta);

	public List<Movimiento> obtenerMovimientosDeUsuario(Long idUsuario);
	
	public List<Movimiento> obtenerMovimientoFechaCuenta(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientosFechaUsuario(Long idCuenta,LocalDate fechaInit, LocalDate fechaFin);

	public Movimiento crearMovimiento(Movimiento movimientoNuevo) throws Exception;

	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFecha(Long idCuenta);
}
