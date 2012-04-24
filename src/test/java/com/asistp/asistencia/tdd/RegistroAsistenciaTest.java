package com.asistp.asistencia.tdd;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.asistp.asistencia.utils.Conversiones;
import com.asistp.domain.Assistance;
import com.asistp.domain.Worker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class RegistroAsistenciaTest {

	private Assistance objAssistance;
	private GregorianCalendar dateAssistance;
	private List<Assistance> assistances;
	
	
	@Before
	public void setUp() throws Exception {
		
		objAssistance = new Assistance();
		dateAssistance = new GregorianCalendar();
		
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
		
		assertTrue(hora == 8 && minutos == 45);
		
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
			
		assertTrue(hora == 8 && minutos == 45);
	}
	
	//Registrar correctamente una asistencia temprana del usuario juan.sabastizagal, Hora 8:45
	@Test
	public void registerAssistanceEarly() {
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour("juan.sabastizagal","08:45");
		objAssistance.register();
				
		assertTrue(objAssistance.getEarly());
	}
	
	//Registrar correctamente una asistencia tardia del usuario edward.rojas, Hora 9:25
	@Test
	public void registerAssistanceLatest() {
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour("edward.rojas","09:25");
		objAssistance.register();
		
		assertTrue(!objAssistance.getEarly() );		
	}
	
	//Registro 2 personas, debo tener 2 personas en BD/memoria
	@Test
	public void assistanceInTheDayMustRegisterCorrectlyWithTwoRegisters(){
		
		List<Assistance> listAssistance;
		
		Assistance asistenciaEarly =   getObjectTemplateAssistanceByUserAndHour("juan.sabastizagal","08:45");
		Assistance asistenciaLatest=   getObjectTemplateAssistanceByUserAndHour("edward.rojas","09:25");
		
		asistenciaEarly.register();
		asistenciaLatest.register();
		
		listAssistance = asistenciaLatest.findAllAssistances();
		
		assertTrue(listAssistance.size() == 2 );
	}
	
	//Registro 3 personas, debo tener 3 personas en BD/memoria
	@Test
	public void assistanceInTheDayMustRegisterCorrectlyWithThreeRegisters(){
			
		List<Assistance> listAssistance;
			
		Assistance asistenciaEarly =   getObjectTemplateAssistanceByUserAndHour("juan.sabastizagal","08:45");
		Assistance asistenciaLatest=   getObjectTemplateAssistanceByUserAndHour("edward.rojas","09:25");
		Assistance asistenciaLatestNumberTwo=   getObjectTemplateAssistanceByUserAndHour("martin.sabastizagal","09:55");
			
		asistenciaEarly.register();
		asistenciaLatest.register();
		asistenciaLatestNumberTwo.register();
			
		listAssistance = asistenciaLatestNumberTwo.findAllAssistances();
			
		assertTrue(listAssistance.size() == 3 );
	}
	
	public Assistance getObjectTemplateAssistanceByUserAndHour(String user,String hora){
		
		int hourOfDay = Integer.parseInt(  hora.split(":")[0] );
		int minute = Integer.parseInt(  hora.split(":")[1] );
		
		GregorianCalendar fechaAsistencia = new GregorianCalendar();
		fechaAsistencia.set(fechaAsistencia.get(Calendar.YEAR) ,
							fechaAsistencia.get(Calendar.MONTH), 
							fechaAsistencia.get(Calendar.DAY_OF_MONTH),
							hourOfDay, minute);
		
		Assistance objAssistance = new Assistance();
		Worker objUsuario = new Worker();
		objUsuario.setLogin("edward.rojas");
		objAssistance.setWorker(objUsuario);
		objAssistance.setDateAssistance(fechaAsistencia);
		
		return objAssistance;
	}
	

}
