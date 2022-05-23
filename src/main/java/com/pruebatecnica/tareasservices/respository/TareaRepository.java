package com.pruebatecnica.tareasservices.respository;

import com.pruebatecnica.tareasservices.entity.Tarea;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TareaRepository {


    Object create(Object o);


    @Transactional
    List<Tarea> getTareas();

    @Transactional
    String getSequence();

    @Transactional
    Tarea getTareaByCodigo(String codigo);

    @Transactional
    Integer getIdTareaByCodigo(String username);

    @Transactional
    Boolean usuarioTareaEstaAsignado(Integer id);

    @Transactional
    void terminarTarea(String codigo);
}
