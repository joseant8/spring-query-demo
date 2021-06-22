package com.example.service;

import com.example.model.Tarjeta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TarjetaService {
	
	public Tarjeta crearTarjeta(Tarjeta tarjetaNueva);
	
	public List<Tarjeta> obtenerTarjetasByCuenta(Long cuentaId);

	public List<Tarjeta> obtenerTarjetasByUsuario(Long usuarioId);

	public Tarjeta obtenerTarjetaByNumeroTarjeta(Long numeroTarjeta);
	
	public Tarjeta obtenerTarjetaById(Long idTarjeta);
	
}
