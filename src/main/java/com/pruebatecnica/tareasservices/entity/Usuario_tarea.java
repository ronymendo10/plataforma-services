package com.pruebatecnica.tareasservices.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuario_tarea")
public class Usuario_tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario_tarea;
    private Integer id_usuario;
    private Integer id_tarea;

}
