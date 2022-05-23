package com.pruebatecnica.tareasservices.respository;

import com.pruebatecnica.tareasservices.entity.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioRepositoryImp implements UsuarioRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Object create(Object o) {
        entityManager.merge(o);
        return o;
    }

    @Override
    public Boolean checkUsuario(Usuario usuario) {
        String query = "FROM Usuario WHERE dni = '" + usuario.getDni() + "' OR username = '" + usuario.getUsername() + "'";
        List<Usuario> result = (List<Usuario>) entityManager.createQuery(query)
                .getResultList();
        if (result.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    @Transactional
    public Usuario getUsuarioByUsername(String username) {
        String query = "FROM Usuario WHERE username = '" + username + "'";
        List<Usuario> usuario = entityManager.createQuery(query).getResultList();
        if (usuario.isEmpty()) {
            return null;
        } else {
            return usuario.get(0);
        }
    }


    @Override
    @Transactional
    public Integer getIdUsuarioByUsername(String username) {
        String query = "FROM Usuario WHERE username = '"+username+"'";
         List<Usuario> usuario = entityManager.createQuery(query).getResultList();

        if(usuario.isEmpty()){
          return 0;
        }else {
            return usuario.get(0).getId_usuario();
        }

    }

}
