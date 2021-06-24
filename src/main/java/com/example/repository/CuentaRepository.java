package com.example.repository;

import com.example.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	boolean existsByIban(String iban);

	@Query("SELECT c FROM Cuenta c JOIN c.usuarios u WHERE u.id = :idUsuario")   // JPQL
	List<Cuenta> obtenerCuentasByUsuarioId(@Param("idUsuario")Long idUsuario);

	@Query(value = "SELECT * FROM cuenta c INNER JOIN usuario_cuenta uc ON c.id=uc.cuenta_id INNER JOIN usuario u ON uc.usuario_id=u.id WHERE u.id=:idUsuario", nativeQuery = true)   // SQL
	List<Cuenta> obtenerCuentasByUsuarioIdV2(@Param("idUsuario")Long idUsuario);


}
