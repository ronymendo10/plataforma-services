package com.pruebatecnica.tareasservices.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioTareaDto {

    private String codigo;
    private String username;
}

