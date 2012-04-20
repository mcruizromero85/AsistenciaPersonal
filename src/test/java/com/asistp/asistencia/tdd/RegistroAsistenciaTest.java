package com.asistp.asistencia.tdd;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.asistp.domain.Asistencia;
import com.asistp.domain.Usuario;

public class RegistroAsistenciaTest {

	private Asistencia objAsistencia;
	private Usuario objUsuario;
	private GregorianCalendar fechaAsistencia;
	
	
	@Before
	public void setUp() throws Exception {
		
		objAsistencia = new Asistencia();
		objUsuario = new Usuario();
		fechaAsistencia = new GregorianCalendar();
	}

	//Registrar correctamente una asistencia temprana del usuario edward.rojas, Hora 8:45
	@Test
	public void registerAssistanceEarly() {
		
		fechaAsistencia.set(2012, 04, 19, 8, 45, 0);
		
		objUsuario = new Usuario();
		objUsuario.setLogin("edward.rojas");
		
		objAsistencia.setUsuario(objUsuario);
		objAsistencia.setFechaHoraAsistencia(fechaAsistencia);
		objAsistencia.registrarAsistencia();
		
		if (objAsistencia.isTemprano()){
			assertTrue(true);
		}else{
			assertFalse(true);
		}
	}
	
	//Registrar correctamente una asistencia tardia del usuario edward.rojas, Hora 9:25
	@Test
	public void registerAssistanceLatest() {
		
		fechaAsistencia.set(2012, 04, 19, 9, 25, 0);
		
		objUsuario = new Usuario();
		objUsuario.setLogin("edward.rojas");
		
		objAsistencia.setUsuario(objUsuario);
		objAsistencia.setFechaHoraAsistencia(fechaAsistencia);
		objAsistencia.registrarAsistencia();
		
		if (!objAsistencia.isTemprano()){
			assertTrue(true);
		}else{
			assertFalse(true);
		}
	}

}
