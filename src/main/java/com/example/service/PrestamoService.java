package com.example.service;

import com.example.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrestamoService {

    /**
     * Obtiene todos los préstamos del usuario indicado.
     * @param usuario_id id del usuario
     * @return Lista de préstamos.
     */
    public List<Prestamo> obtenerPrestamosDeUsuario(Long usuario_id);


    /**
     * Obtiene todos los préstamos de las cuentas del usuario indicado.
     * @param usuario_id id del usuario
     * @return Lista de préstamos.
     */
    public List<Prestamo> obtenerPrestamosCuentasDeUsuario(Long usuario_id);


    /**
     * Crea un nuevo préstamo en la base de datos.
     * @param prestamo
     * @return El préstamo creado.
     */
    public Prestamo crearPrestamo(Prestamo prestamo);


    /**
     * Configura un préstamo. Es decir, establece la cuota, número de cuotas, etc.
     * @param prestamo
     * @return El préstamo configurado.
     */
    public Prestamo configurarPrestamo(Prestamo prestamo);


    /**
     * Calcula la cantidad del préstamo más los intereses. Es decir, la cantidad total del préstamo a devolver.
     * @param prestamo
     * @return cantidad del préstamo con los intereses
     */
    public Double cantidadTotalConIntereses(Prestamo prestamo);
}
