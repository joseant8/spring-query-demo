package com.example.repository;

import com.example.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
	
	@Query("SELECT t From Tarjeta t WHERE t.numero = :numeroTarjeta")
	Tarjeta obtenerTarjetaByNumeroTarjeta(@Param("numeroTarjeta") Long numeroTarjeta);
	
	@Query("SELECT t From Tarjeta t WHERE t.cuenta.id = :idCuenta")
	List<Tarjeta> obtenerTarjetasDeCuenta(@Param("idCuenta") Long idCuenta);

	@Query("SELECT t FROM Usuario u JOIN u.cuentas c JOIN c.tarjetas t WHERE u.id=:idUsuario")
	List<Tarjeta> obtenerTarjetasDeUsuarioV2(@Param("idUsuario") Long idUsuario);

	@Query("SELECT t FROM Tarjeta t JOIN t.cuenta c JOIN c.usuarios u WHERE u.id=:idUsuario")
	List<Tarjeta> obtenerTarjetasDeUsuarioV3(@Param("idUsuario") Long idUsuario);

}
