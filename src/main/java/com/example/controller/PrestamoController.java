package com.example.controller;

import com.example.model.Prestamo;
import com.example.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/API")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    // --------------------------------------------
    // Obtener datos - consultas select
    // --------------------------------------------

    /**
     * Obtiene todos los préstamos del usuario indicado
     * @param idUsuario
     * @return lista de préstamos
     */
    @GetMapping("/prestamos/usuario/{idUsuario}")
    public List<Prestamo> obtenerTodosLosPrestamosDeUsuario(@PathVariable Long idUsuario){
        return prestamoService.obtenerPrestamosDeUsuario(idUsuario);
    }


    /**
     * Obtiene todos los préstamos del usuario indicado en base a sus cuentas. El resultado es el mismo que en el método
     * anterior, pero ejecutando una consulta más compleja (simplemente con fines didácticos).
     * @param idUsuario
     * @return lista de préstamos
     */
    @GetMapping("/prestamos/cuentas/usuario/{idUsuario}")
    public List<Prestamo> obtenerTodosLosPrestamosDeLasCuentasDeUsuario(@PathVariable Long idUsuario){
        return prestamoService.obtenerPrestamosCuentasDeUsuario(idUsuario);
    }
}
