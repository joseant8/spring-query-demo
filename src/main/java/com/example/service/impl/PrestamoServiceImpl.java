package com.example.service.impl;

import com.example.model.Prestamo;
import com.example.repository.PrestamoRepository;
import com.example.service.MovimientoService;
import com.example.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private static final Double INTERES = 15.0;
    private static final Integer NUM_CUOTAS = 10;

    @Autowired
    PrestamoRepository prestamoRepositorio;

    @Autowired
    MovimientoService movimientoService;

    @Override
    public List<Prestamo> obtenerPrestamosUsuario(Long usuario_id) {
        return prestamoRepositorio.obtenerPrestamosUsuario(usuario_id);
    }

    @Override
    public Prestamo crearPrestamo(Prestamo prestamo){

        Prestamo prestamoCreado = prestamoRepositorio.save(prestamo);
        //PrestamoUtil prestamoUtil = new PrestamoUtil(prestamoCreado, movimientoService);
        //Thread prestamoThread=new Thread(prestamoUtil);
        //prestamoThread.start();

        return prestamoCreado;
    }

    @Override
    public Prestamo configurarPrestamo(Prestamo prestamo) {
        prestamo.setInteres(INTERES);
        prestamo.setNumeroCuotas(NUM_CUOTAS);
        prestamo.setCuota(cantidadTotalConIntereses(prestamo) / prestamo.getNumeroCuotas());
        return prestamo;
    }

    @Override
    public Double cantidadTotalConIntereses(Prestamo prestamo){
        Double intereses =  prestamo.getInteres() * 0.01 * prestamo.getCantidad();
        return prestamo.getCantidad() + intereses;
    }
}
