package com.example.service.impl;

import com.example.model.Cuenta;
import com.example.repository.CuentaRepository;
import com.example.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	CuentaRepository cuentaRepository;
	

	@Override
	public Cuenta obtenerCuentaById(Long idCuenta) {
		return cuentaRepository.findById(idCuenta).orElseThrow(()
				-> new EntityNotFoundException("No se ha encontrado la cuenta con id: "+idCuenta));
	}

	@Override
	public List<Cuenta> obtenerTodasCuentasByUsuarioId(Long idUsuario) {
		return cuentaRepository.obtenerCuentasByUsuarioId(idUsuario);
	}

	@Override
	public List<Cuenta> obtenerTodasCuentasByUsuarioIdV2(Long idUsuario) {
		return cuentaRepository.obtenerCuentasByUsuarioIdV2(idUsuario);
	}

	@Override
	public Cuenta crearCuenta(Cuenta cuenta) {
		if(cuentaRepository.existsByIban(cuenta.getIban())){
			return cuenta;
		}
		return cuentaRepository.save(cuenta);
	}

}
