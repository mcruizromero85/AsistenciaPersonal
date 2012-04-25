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

import com.asistp.assistance.utils.Conversiones;
import com.asistp.domain.Assistance;
import com.asistp.domain.Schedule;
import com.asistp.domain.Worker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class RegistroAsistenciaTest {

	private Assistance objAssistance;
	private GregorianCalendar dateAssistance;
	private List<Assistance> assistances;
	private Schedule schedule;
	private Worker workerEdward;
	private Worker workerMartin;
	private Worker workerJuan;
	
	@Before
	public void setUp() throws Exception {
		
		objAssistance = new Assistance();
		dateAssistance = new GregorianCalendar();
		
		schedule = new Schedule();
		schedule.setHourLate( getGregorianCalendarWithHourForTest("09:00"));
		schedule.setName("Horario Regular");
		schedule.persist();
		
		workerEdward = getObjectWorkerToTestByUserName("edward.rojas");
		workerMartin = getObjectWorkerToTestByUserName("martin.sabastizagal");
		workerJuan = getObjectWorkerToTestByUserName("juan.sabastizagal");
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
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		objAssistance.register();
				
		assertTrue(objAssistance.getEarly());
	}
	
	//Registrar correctamente una asistencia tardia del usuario edward.rojas, Hora 9:25
	@Test
	public void registerAssistanceLatest() {
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		objAssistance.register();
		
		assertTrue(!objAssistance.getEarly() );		
	}
	
	//Registro 2 personas, debo tener 2 personas en BD/memoria
	@Test
	public void assistanceInTheDayMustRegisterCorrectlyWithTwoRegisters(){
		
		List<Assistance> listAssistance;
		
		Assistance asistenciaEarly =   getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		Assistance asistenciaLatest=   getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		
		asistenciaEarly.register();
		asistenciaLatest.register();
		
		listAssistance = asistenciaLatest.findAllAssistances();
		
		assertTrue(listAssistance.size() == 2 );
	}
	
	//Registro 3 personas, debo tener 3 personas en BD/memoria
	@Test
	public void assistanceInTheDayMustRegisterCorrectlyWithThreeRegisters(){
			
		List<Assistance> listAssistance;
			
		Assistance asistenciaEarly =   getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		Assistance asistenciaLatest=   getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		Assistance asistenciaLatestNumberTwo=   getObjectTemplateAssistanceByUserAndHour(workerMartin,"09:55");
			
		asistenciaEarly.register();
		asistenciaLatest.register();
		asistenciaLatestNumberTwo.register();
			
		listAssistance = asistenciaLatestNumberTwo.findAllAssistances();
			
		assertTrue(listAssistance.size() == 3 );
	}
	
	public Assistance getObjectTemplateAssistanceByUserAndHour(Worker workerParam,String hora){
		
		
		GregorianCalendar fechaAsistencia = getGregorianCalendarWithHourForTest(hora);
		
		Assistance objAssistance = new Assistance();
			
		objAssistance.setWorker(workerParam);
		objAssistance.setDateAssistance(fechaAsistencia);
		
		return objAssistance;
	}

	private GregorianCalendar getGregorianCalendarWithHourForTest(String stringHourWithMinute) {
		
		int hourOfDay = Integer.parseInt(  stringHourWithMinute.split(":")[0] );
		int minute = Integer.parseInt(  stringHourWithMinute.split(":")[1] );
		
		GregorianCalendar gcHourWithMinute = new GregorianCalendar();
		gcHourWithMinute.set(gcHourWithMinute.get(Calendar.YEAR) ,
				gcHourWithMinute.get(Calendar.MONTH), 
				gcHourWithMinute.get(Calendar.DAY_OF_MONTH),
				hourOfDay, minute);
		
		return gcHourWithMinute;
	}
	
	private Worker getObjectWorkerToTestByUserName(String username) {
		Worker worker = new Worker();
		worker.setLogin(username);
		worker.setPassword("12345678");
		worker.setSchedule(schedule);
		worker.persist();
		return worker;
	}
}
