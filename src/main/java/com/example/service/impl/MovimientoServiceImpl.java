package com.example.service.impl;

import com.example.model.Cuenta;
import com.example.model.Movimiento;
import com.example.model.TipoMovimiento;
import com.example.repository.*;
import com.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class MovimientoServiceImpl implements MovimientoService {
	@Autowired
	MovimientoRepository movimientoRepository;
	
	@Autowired
	CuentaRepository cuentaRepository;

	//@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeTarjeta(Long idTarjeta) {
		// Obtenermos todos los movimientos de una tarjeta
		return movimientoRepository.obtenerMovimientosDeTarjeta(idTarjeta);
	}

	//@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeCuenta(Long idCuenta) {
		// Obtenemos todos los movimientos de una cuenta
		return movimientoRepository.obtenerMovimientosDeCuenta(idCuenta);
	}

	@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeUsuario(Long idUsuario) {
		// Obtenemos todos los movimientos de un usuario (es decir, los movimientos de todas sus cuentas)
		List<Movimiento> movimientos = new ArrayList<>();
		List<Cuenta> cuentas = cuentaRepository.obtenerCuentasByUsuarioId(idUsuario);
		for(Cuenta c: cuentas){
			movimientos.addAll(c.getMovimientos());
		}
		return movimientos;
	}

	@Override
	public List<Movimiento> obtenerMovimientosDeUsuarioV2(Long idUsuario) {
		return movimientoRepository.obtenerMovimientosDeUsuarioV2(idUsuario);
	}

	@Override
	public List<Movimiento> obtenerMovimientosDeUsuarioV3(Long idUsuario) {
		return movimientoRepository.obtenerMovimientosDeUsuarioV3(idUsuario);
	}

	@Override
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaDESC(Long idCuenta) {
		return movimientoRepository.obtenerMovimientosDeCuentaOrdenadosFechaDESC(idCuenta);
	}

	@Override
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenados(Long idCuenta, Sort sort) {
		return movimientoRepository.obtenerMovimientosDeCuentaOrdenados(idCuenta, sort);
	}

	@Override
	public Page<Movimiento> obtenerMovimientosPagina(Pageable pageable) {
		return movimientoRepository.findAll(pageable);
	}

	@Override
	public Page<Movimiento> obtenerMovimientosDeCuentaOrdenadosFechaPagina(Long idCuenta, Pageable pageable) {
		return movimientoRepository.obtenerMovimientosDeCuentaOrdenadosFechaPagina(idCuenta, pageable);
	}

	@Override
	public List<Movimiento> obtenerMovimientosDeCuentaByFecha(Long idCuenta, String fechaInit, String fechaFin) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateInit = formatter.parse(fechaInit);
			Date datefin = formatter.parse(fechaFin);
			return movimientoRepository.obtenerMovimientosDeCuentaByFecha(idCuenta, dateInit , datefin);
		} catch (ParseException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta, LocalDate fechaInit, LocalDate fechaFin) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateInit = null;
		Date datefin = null;
		try {
			dateInit = formatter.parse(fechaInit.toString());
			datefin = formatter.parse(fechaFin.toString());
		} catch (ParseException e) {
		}
		return movimientoRepository.obtenerMovimientosDeTarjetaByFecha(idTarjeta, dateInit , datefin);

	}
	
	private static int ponerDiasFechaFinMes(Date fecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	@Transactional
	@Override
	public Movimiento crearMovimiento(Movimiento movimientoNuevo) throws Exception {
		try {
			Optional<Cuenta> cuenta;
			Cuenta cuentaObtenida = null;

				// Obtenemos la cuenta de la base de datos que se va a realizar el movimeinto
				cuenta = cuentaRepository.findById(movimientoNuevo.getCuenta().getId());				

			if(cuenta.isPresent()) {
				cuentaObtenida = cuenta.get();				
			}
			
			// Calculamos el nuevo saldo actual en el movimiento

			if(TipoMovimiento.INGRESO.equals(movimientoNuevo.getTipo())) {
				movimientoNuevo.setSaldoActual(cuentaObtenida.getSaldo() + movimientoNuevo.getCantidad());				
			}else {
				movimientoNuevo.setSaldoActual(cuentaObtenida.getSaldo() - movimientoNuevo.getCantidad());
			}

			
			// Almacenamos el saldo actual en la cuenta
			cuentaObtenida.setSaldo(movimientoNuevo.getSaldoActual());
			// Guardamos la cuenta actualizada en la base de datos
			Cuenta cuentaGuardada = cuentaRepository.save(cuentaObtenida);
			// Guardamos el movmiento en la base de datos
			movimientoNuevo.setCuenta(cuentaGuardada);
			return movimientoRepository.save(movimientoNuevo);
			
		}catch(Exception e) {
			// En caso de fallar se devuelve un null
			throw new Exception();
		}
	}
}
