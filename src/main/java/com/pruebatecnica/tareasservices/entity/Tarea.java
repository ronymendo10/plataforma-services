package com.pruebatecnica.tareasservices.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Table(name = "tarea")
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tarea;
    private String codigo_tarea;
    private String nombre;
    private String descripcion;
    private Date fecha_creacion;
    private Date fecha_asignacion;
    private Date fecha_entrega;
    private Date fecha_actualizacion;
    private String estado;
    @ManyToMany
    @JoinTable(
            name="usuario_tarea",
            joinColumns = {@JoinColumn(name="id_tarea")},
            inverseJoinColumns = {@JoinColumn(name="id_usuario")}
    )
    private List<Usuario> usuarios = new ArrayList<>();

}
