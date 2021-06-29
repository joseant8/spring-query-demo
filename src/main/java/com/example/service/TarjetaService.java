package com.example.service;

import com.example.model.Tarjeta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TarjetaService {

	/**
	 * Obtiene todas las tarjetas de la cuenta indicada
	 * @param cuentaId id de la cuenta
	 * @return Lista de tarjetas
	 */
	List<Tarjeta> obtenerTarjetasDeCuenta(Long cuentaId);

	/**
	 * Obtiene todas las tarjetas del usuario indicado
	 * @param usuarioId id del ususario
	 * @return Lista de tarjetas
	 */
	List<Tarjeta> obtenerTarjetasDeUsuario(Long usuarioId);

	/**
	 * Obtiene todas las tarjetas del usuario indicado (V2 JPQL)
	 * @param usuarioId id del ususario
	 * @return Lista de tarjetas
	 */
	List<Tarjeta> obtenerTarjetasDeUsuarioV2(Long usuarioId);

	/**
	 * Obtiene todas las tarjetas del usuario indicado (V3 JPQL)
	 * @param usuarioId id del ususario
	 * @return Lista de tarjetas
	 */
	List<Tarjeta> obtenerTarjetasDeUsuarioV3(Long usuarioId);

	/**
	 * Obtiene la tarjeta según el número de tarjeta
	 * @param numeroTarjeta número de tarjeta
	 * @return Tarjeta
	 */
	Tarjeta obtenerTarjetaByNumeroTarjeta(Long numeroTarjeta);

	/**
	 * Obtiene la tarjeta según el id
	 * @param idTarjeta id de la tarjeta
	 * @return Tarjeta
	 */
	Tarjeta obtenerTarjetaById(Long idTarjeta);

	/**
	 * Crea una nueva tarjeta en la BD
	 * @param tarjetaNueva tarjeta que queremos crear
	 * @return Tarjeta creada
	 */
	Tarjeta crearTarjeta(Tarjeta tarjetaNueva);
	
}
