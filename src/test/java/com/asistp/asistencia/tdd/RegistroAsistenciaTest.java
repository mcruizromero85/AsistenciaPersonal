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

import com.asistp.assistance.utils.Constantes;
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
		schedule.setHourLimit(9);
		schedule.setMinuteHourLimit(0);
		schedule.setName("Horario Regular");
		schedule.persist();
		
		workerEdward = getObjectWorkerToTestByUserName("edward.rojas.test");
		workerMartin = getObjectWorkerToTestByUserName("martin.sabastizagal.test");
		workerJuan = getObjectWorkerToTestByUserName("juan.sabastizagal.test");
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
	
	//Parsear correctamente las horas de gregorian Calendars a texto , hora ejemplo 08:45
	@Test
	public void parserCalendarToHourStringForValueEigthAndFortyFive(){
		GregorianCalendar gcHora = new GregorianCalendar();
		gcHora.set(Calendar.HOUR_OF_DAY, 8);
		gcHora.set(Calendar.MINUTE, 45);
		String horaTextExpected="08:45";
		String horaTextEval="";
		horaTextEval = Conversiones.parserCalendarToHoraString(gcHora);		
		assertTrue(horaTextExpected.equals(horaTextEval ));
	}
	
	
	
	//Parsear correctamente Los Gregorian Calendars de un formato a otro
	@Test
	public void parserCalendarToHourStringForValueNineAndTwentyFive(){
		GregorianCalendar gcHora = new GregorianCalendar();
		gcHora.set(Calendar.HOUR_OF_DAY, 9);
		gcHora.set(Calendar.MINUTE, 25);
		String horaTextExpected="09:25";
		String horaTextEval="";
		horaTextEval = Conversiones.parserCalendarToHoraString(gcHora);		
		assertTrue(horaTextExpected.equals(horaTextEval ));
	}
	
	//Obtener correctamente la hora Inicio del Día
	@Test
	public void getCorrectlyInitialDayByGregorianCalendar(){
		GregorianCalendar gcHora = new GregorianCalendar();
		GregorianCalendar gcHoraInitialDay = Conversiones.getDateTimeInitialByDay(gcHora);
		
		assertTrue( gcHoraInitialDay.get(Calendar.HOUR_OF_DAY) == 0 &&
					gcHoraInitialDay.get(Calendar.MINUTE) == 0 &&
					gcHoraInitialDay.get(Calendar.SECOND) == 0 &&
					gcHoraInitialDay.get(Calendar.YEAR) == gcHora.get(Calendar.YEAR) && 
					gcHoraInitialDay.get(Calendar.MONTH) == gcHora.get(Calendar.MONTH) &&
					gcHoraInitialDay.get(Calendar.DAY_OF_MONTH) == gcHora.get(Calendar.DAY_OF_MONTH ));
	}
	
	//Obtener correctamente la hora Final del Día
	@Test
	public void getCorrectlyFinishDayByGregorianCalendar(){
		
		GregorianCalendar gcHora = new GregorianCalendar();
		GregorianCalendar gcHoraInitialDay = Conversiones.getDateTimeFinishByDay(gcHora);
			
		assertTrue( gcHoraInitialDay.get(Calendar.HOUR_OF_DAY) == 23 &&
					gcHoraInitialDay.get(Calendar.MINUTE) == 59 &&
					gcHoraInitialDay.get(Calendar.SECOND) == 59 &&
					gcHoraInitialDay.get(Calendar.YEAR) == gcHora.get(Calendar.YEAR) && 
					gcHoraInitialDay.get(Calendar.MONTH) == gcHora.get(Calendar.MONTH) &&
					gcHoraInitialDay.get(Calendar.DAY_OF_MONTH) == gcHora.get(Calendar.DAY_OF_MONTH ));
	}
	//Registrar correctamente una asistencia temprana del usuario juan.sabastizagal, Hora 8:45
	@Test
	public void registerAssistanceEarly() throws Exception {
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		objAssistance.register();				
		assertTrue(objAssistance.getStatus().equals(Constantes.ASISTENCIA_STATUS_VALUE_TEMPRANO));
	}
	
	//Registrar correctamente una asistencia tardia del usuario edward.rojas, Hora 9:25
	@Test
	public void registerAssistanceLatest() throws Exception {
		
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		objAssistance.register();
		
		assertTrue(!objAssistance.getStatus().equals(Constantes.ASISTENCIA_STATUS_VALUE_TEMPRANO) );		
	}
	
	//Registrar correctamente una asistencia temprana del usuario juan.sabastizagal, Hora 8:45 y que la hora tardia referencia de la asistencia tenga el formato HH:mm con valor 09:00
	@Test
	public void registerAssistanceEarlyWithCorrectlyFormatToHourLateReference() throws Exception {
			
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		objAssistance.register();
					
		assertTrue(objAssistance.getHourLimitReference().equals("09:00"));
	}
		
	//Registrar correctamente una asistencia tardía del usuario edward.rojas, Hora 9:25 y que la hora tardia referencia de la asistencia tenga el formato HH:mm con valor 09:00
	@Test
	public void registerAssistanceLatestWithCorrectlyFormatToHourLateReference() throws Exception {
			
		objAssistance = getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		objAssistance.register();
			
		assertTrue(objAssistance.getHourLimitReference().equals("09:00"));		
	}
	
	//Registro 2 personas, debo tener 2 personas en BD/memoria
	@Test
	public void assistanceInTheDayMustRegisterCorrectlyWithTwoRegisters() throws Exception{
		
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
	public void assistanceInTheDayMustRegisterCorrectlyWithThreeRegisters() throws Exception{
		List<Assistance> listAssistance;
		
		Assistance asistenciaEarly =  getObjectTemplateAssistanceByUserAndHour(workerJuan,"08:45");
		Assistance asistenciaLatest=  getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		Assistance asistenciaLatestNumberTwo=getObjectTemplateAssistanceByUserAndHour(workerMartin,"09:55");
				
		asistenciaEarly.register();
		asistenciaLatest.register();
		asistenciaLatestNumberTwo.register();
				
		listAssistance = asistenciaLatestNumberTwo.findAllAssistances();
				
		assertTrue(listAssistance.size() == 3 );	
		
	}
	
	//Para Edward Rojas Registro una segunda Asistencia en el mismo día, me debería rechazar
	@Test
	public void errorToIntentAssistanceRegisterTwoTimesForOneWorker(){
		
		Assistance firstAssistance=   getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:25");
		Assistance secondAssistance=   getObjectTemplateAssistanceByUserAndHour(workerEdward,"09:55");
		
		try{
			firstAssistance.register();
			secondAssistance.register();
		}catch (Exception e) {
			assertTrue(true);
			return;
		}	
		assertFalse(true);		
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
		worker.setUsername(username);
		worker.setSchedule(schedule);
		worker.persist();
		return worker;
	}
}
