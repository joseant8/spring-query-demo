package com.example.service.impl;

import com.example.model.Cuenta;
import com.example.model.Tarjeta;
import com.example.repository.CuentaRepository;
import com.example.repository.TarjetaRepository;
import com.example.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {
	
	@Autowired
	TarjetaRepository tarjetaRepository;

	@Autowired
	CuentaRepository cuentaRepository;


	@Transactional
	@Override
	public List<Tarjeta> obtenerTarjetasDeCuenta(Long cuentaId) {
		
		return tarjetaRepository.obtenerTarjetasDeCuenta(cuentaId);
	}

	@Transactional
	@Override
	public List<Tarjeta> obtenerTarjetasDeUsuario(Long usuarioId) {
		List<Cuenta> cuentas = cuentaRepository.obtenerCuentasByUsuarioId(usuarioId);
		List<Tarjeta> tarjetas = new ArrayList<>();
		for(Cuenta c: cuentas){
			tarjetas.addAll(c.getTarjetas());
		}
		return tarjetas;
	}

	@Override
	public List<Tarjeta> obtenerTarjetasDeUsuarioV2(Long usuarioId) {
		return tarjetaRepository.obtenerTarjetasDeUsuarioV2(usuarioId);
	}

	@Override
	public List<Tarjeta> obtenerTarjetasDeUsuarioV3(Long usuarioId) {
		return tarjetaRepository.obtenerTarjetasDeUsuarioV3(usuarioId);
	}

	@Transactional
	@Override
	public Tarjeta obtenerTarjetaByNumeroTarjeta(Long numeroTarjeta) {
		return tarjetaRepository.obtenerTarjetaByNumeroTarjeta(numeroTarjeta);
	}

	@Transactional
	@Override
	public Tarjeta obtenerTarjetaById(Long idTarjeta) throws EntityNotFoundException{
		return tarjetaRepository.findById(idTarjeta).orElseThrow(()
                -> new EntityNotFoundException("No se ha encontrado tarjeta con numero: "+idTarjeta));
	}

	@Transactional
	@Override
	public Tarjeta crearTarjeta(Tarjeta tarjetaNueva) {
		return tarjetaRepository.save(tarjetaNueva);
	}

}
