package com.asistp.asistencia.tdd;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.asistp.asistencia.utils.Conversiones;
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
	
	//Parsear correctamente las horas a gregorian Calendars, hora ejemplo 8:45
	@Test
	public void parserHourToGregorianCalendarWithValorEightAndFourtyFive(){
		String horaText="8:45";
		GregorianCalendar gcHora = new GregorianCalendar();
		try {
			gcHora = Conversiones.parserHoraFromStringToGregorianCalendar(horaText);
		} catch (ParseException e) {
			assertFalse(true);
		}
		int hora=gcHora.get(Calendar.HOUR_OF_DAY);
		int minutos=gcHora.get(Calendar.MINUTE);
		
		if (hora == 8 && minutos == 45 ){
			assertTrue(true);
		}else{
			assertFalse(true);
		}
	}
	
	//Parsear correctamente las horas a gregorian Calendars, hora ejemplo 8:45
	@Test
	public void parserHourToGregorianCalendarWithValorNineAndTwentyFive(){
		String horaText="8:45";
		GregorianCalendar gcHora = new GregorianCalendar();
		try {
			gcHora = Conversiones.parserHoraFromStringToGregorianCalendar(horaText);
		} catch (ParseException e) {
			assertFalse(true);
		}
		int hora=gcHora.get(Calendar.HOUR_OF_DAY);
		int minutos=gcHora.get(Calendar.MINUTE);
			
		if (hora == 8 && minutos == 45 ){
			assertTrue(true);
		}else{
			assertFalse(true);
		}
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
