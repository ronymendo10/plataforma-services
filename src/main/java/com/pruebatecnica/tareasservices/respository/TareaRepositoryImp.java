package com.pruebatecnica.tareasservices.respository;

import com.pruebatecnica.tareasservices.entity.Tarea;
import com.pruebatecnica.tareasservices.entity.Usuario_tarea;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TareaRepositoryImp implements TareaRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Object create(Object o) {
        entityManager.merge(o);
        return o;
    }


    @Override
    @Transactional
    public List<Tarea> getTareas() {
        String query = "FROM Tarea";
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    @Transactional
    public String getSequence() {
        String query = "(SELECT fn_NEXTVALUE('SEQ_ID'));";
        BigInteger aux = (BigInteger) entityManager.createNativeQuery(query).getResultList().get(0);
        String query2 = "(SELECT LPAD("+aux+",4,'0'));";
        String cod = (String) entityManager.createNativeQuery(query2).getResultList().get(0);
        return cod;
    }


    @Override
    @Transactional
    public Tarea getTareaByCodigo(String codigo) {
        String query = "FROM Tarea WHERE codigo_tarea = '"+codigo+"'";
        List<Tarea> tarea = entityManager.createQuery(query).getResultList();
        if(tarea.isEmpty()){
            return null;
        }else {
            return tarea.get(0);
        }
    }

    @Override
    @Transactional
    public Integer getIdTareaByCodigo(String codigo) {
        String query = "FROM Tarea WHERE codigo_tarea = '"+codigo+"'";
        List<Tarea> tarea = entityManager.createQuery(query).getResultList();
        if(tarea.isEmpty()){
            return 0;
        }else {
            return tarea.get(0).getId_tarea();
        }
    }

    @Override
    @Transactional
    public Boolean usuarioTareaEstaAsignado(Integer id) {
        String query = "FROM Usuario_tarea WHERE id_usuario = '"+id+"'";
        List<Usuario_tarea> usuario_tarea = entityManager.createQuery(query).getResultList();
        if(usuario_tarea.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void terminarTarea(String codigo) {
        Query q = entityManager.createNativeQuery("update tarea set estado = 'TERMINADO',  fecha_actualizacion =  "+ new Date() +"  where codigo_tarea = '"+codigo+"' ");
       q.executeUpdate();
    }

}
