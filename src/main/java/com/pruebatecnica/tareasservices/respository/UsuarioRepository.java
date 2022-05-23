package com.pruebatecnica.tareasservices.respository;

import com.pruebatecnica.tareasservices.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository {


    Object create(Object o);

    Boolean checkUsuario(Usuario usuario);

    @Transactional
    List<Usuario> getUsuarios();

    @Transactional
    Usuario getUsuarioByUsername(String username);


    @Transactional
    Integer getIdUsuarioByUsername(String username);


}
