package com.pruebatecnica.tareasservices.controller;

import com.pruebatecnica.tareasservices.entity.Tarea;
import com.pruebatecnica.tareasservices.entity.Usuario;
import com.pruebatecnica.tareasservices.respository.TareaRepository;
import com.pruebatecnica.tareasservices.respository.TareaRepositoryImp;
import com.pruebatecnica.tareasservices.respository.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaControllerTest {


    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    TareaRepository tareaRepository = Mockito.mock(TareaRepository.class);

     @Autowired
    PlataformaController plataformaController = new PlataformaController(usuarioRepository,tareaRepository);

    @BeforeEach
    void setUp() {

        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setContraseña("string");
        usuario.setDni("string");
        usuario.setNombre("string");
        usuario.setApellido("string");
        usuario.setContraseña("string");
        usuario.setUsername("string");


        Tarea tarea = new Tarea();
        tarea.setId_tarea(1);
        tarea.setCodigo_tarea("T20224-0001");
        tarea.setEstado("SIN ASIGNAR");
        tarea.setFecha_creacion(new Date());
        tarea.setFecha_asignacion(new Date());
        tarea.setFecha_entrega(new Date());



        Mockito.when(tareaRepository.getTareaByCodigo("T20224-0001")).thenReturn(tarea);
        Mockito.when(usuarioRepository.getUsuarioByUsername("string")).thenReturn(usuario);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsuarios() {
        ResponseEntity<List<Usuario>>  respuesta;
        respuesta = plataformaController.getUsuarios();
        Assertions.assertNotEquals(respuesta, null);
    }

    @Test
    void getTareas() {
        ResponseEntity<List<Tarea>>  respuesta;
        respuesta = plataformaController.getTareas();
        Assertions.assertNotEquals(respuesta, null);
    }

    @Test
    void getUsuarioByUsername() {
        ResponseEntity<Usuario>  respuesta;
        respuesta = plataformaController.getUsuarioByUsername("string");
        Assertions.assertEquals(respuesta.getBody().getId_usuario(), 1);
    }

    @Test
    void getTareaByCodigo() {
        ResponseEntity<Tarea>  respuesta;
        respuesta = plataformaController.getTareaByCodigo("T20224-0001");
        Assertions.assertEquals(respuesta.getBody().getId_tarea(), 1);
    }


    void saveUsuario() throws UnsupportedEncodingException {
        Usuario usuario = new Usuario();
        usuario.setContraseña("string");
        usuario.setDni("string1");
        usuario.setUsername("string1");
        usuario.setNombre("string");
        usuario.setApellido("string");
        usuario.setContraseña("string");
        ResponseEntity<String>  respuesta;
        respuesta = plataformaController.saveUsuario(usuario);

        Assertions.assertEquals(respuesta,ResponseEntity.ok("Usuario creado"));
    }


    void saveTarea() throws UnsupportedEncodingException, ParseException {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setContraseña("string");
        usuario.setDni("string");
        usuario.setNombre("string");
        usuario.setApellido("string");
        usuario.setContraseña("string");
        usuario.setUsername("string");

        Tarea tarea = new Tarea();
        tarea.setId_tarea(1);
        tarea.setCodigo_tarea("T20224-0001");
        tarea.setEstado("SIN ASIGNAR");
        tarea.setFecha_creacion(new Date());
        tarea.setFecha_asignacion(new Date());
        SimpleDateFormat sdformat = new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = sdformat.parse("2022-06-22 19:51:03");
        tarea.setFecha_entrega(date1);
        tarea.getUsuarios().add(usuario);
        ResponseEntity<String>  respuesta;
        respuesta = plataformaController.saveTarea(tarea);
        Assertions.assertEquals(respuesta,ResponseEntity.ok("Tarea creada"));
    }

    @Test
    void addUsuarioTarea() {
    }

    @Test
    void testSaveUsuario() {
    }
}