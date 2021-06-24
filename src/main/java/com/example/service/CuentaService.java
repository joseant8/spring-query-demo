package com.example.service;

import com.example.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CuentaService {


	// ---------------------------------
	// Consultas select
	// ---------------------------------

	/**
	 * Obtener una cuenta según el id
	 * @param idCuenta id de la cuenta
	 * @return Cuenta
	 */
	Cuenta obtenerCuentaById(Long idCuenta);

	/**
	 * Obtener las cuentas del usuario según su id
	 * @param idUsuario id del usuario
	 * @return Lista de cuentas
	 */
	List<Cuenta> obtenerTodasCuentasByUsuarioId(Long idUsuario);


	/**
	 * Obtener las cuentas del usuario según su id
	 * @param idUsuario id del usuario
	 * @return Lista de cuentas
	 */
	List<Cuenta> obtenerTodasCuentasByUsuarioIdV2(Long idUsuario);


	// ---------------------------------
	// Crear
	// ---------------------------------

	/**
	 * Crea una nueva cuenta
	 * @param cuenta cuenta que queremos crear
	 * @return La cuenta creada
	 */
	Cuenta crearCuenta(Cuenta cuenta);

}
