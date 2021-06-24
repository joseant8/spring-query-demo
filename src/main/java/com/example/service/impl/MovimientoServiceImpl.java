package com.example.service.impl;

import com.example.model.Cuenta;
import com.example.model.Movimiento;
import com.example.model.TipoMovimiento;
import com.example.model.Usuario;
import com.example.repository.*;
import com.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
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
	CategoriaRepository categoriaRepository;
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@Autowired
	TarjetaRepository tarjetaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeTarjeta(Long idTarjeta) {
		// Obtenermos todos los movimientos con el metodo del repositorio
		return movimientoRepository.obtenerMovimientosDeTarjeta(idTarjeta);
	}

	@Transactional
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
	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta, LocalDate fechaInit, LocalDate fechaFin) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateInit = null;
		Date datefin = null;
		try {
			dateInit = formatter.parse(fechaInit.toString());
			datefin = formatter.parse(fechaFin.toString());
		} catch (ParseException e) {
		} 
		return movimientoRepository.obtenerMovimientosDeTarjetaFechas(idTarjeta, dateInit , datefin);
		
	}
	
	@Override
	public List<Movimiento> obtenerMovimientoFechaCuenta(Long idCuenta, LocalDate fechaInit, LocalDate fechaFin) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateInit = null;
		Date datefin = null;
		try {
			dateInit = formatter.parse(fechaInit.toString());
			datefin = formatter.parse(fechaFin.toString());
		} catch (ParseException e) {
		} 
		
		return movimientoRepository.obtenerMovimientosDeCuentaFechas(idCuenta, dateInit , datefin);
	}

	@Override
	@Transactional
	public List<Movimiento> obtenerMovimientosFechaUsuario(Long idUsuario, LocalDate fechaInit, LocalDate fechaFin) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		List<Movimiento> movimientosFiltroFecha = new ArrayList<>();
		if(usuario.isPresent()){
			for(Cuenta c: usuario.get().getCuentas()){
				movimientosFiltroFecha.addAll(obtenerMovimientoFechaCuenta(c.getId(), fechaInit, fechaFin));
			}
		}
		return movimientosFiltroFecha;
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
			Optional<Cuenta> cuenta = null;
			Cuenta cuentaObtenida = null;

				// Obtenemos la cuenta de la base de datos que se va a realizar el movimeinto
				cuenta = cuentaRepository.findById(movimientoNuevo.getCuenta().getId());				

			if(cuenta != null) {
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

	@Override
	public List<Movimiento> obtenerMovimientosDeCuentaOrdenadosFecha(Long idCuenta) {
		return movimientoRepository.obtenerMovimientosDeCuentaOrdenadosCuenta(idCuenta);

	}



}
