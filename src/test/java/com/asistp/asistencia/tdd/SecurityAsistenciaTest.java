package com.asistp.asistencia.tdd;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asistp.assistance.utils.Constantes;
import com.asistp.assistance.utils.Conversiones;
import com.asistp.domain.Assistance;
import com.asistp.domain.Roles;
import com.asistp.domain.Schedule;
import com.asistp.domain.Worker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class SecurityAsistenciaTest {

	@Before
	public void setUp() throws Exception {
		
	}
	
	//Parsear correctamente las horas a gregorian Calendars, hora ejemplo 8:45
	@Test
	public void validateGetAllRolesFromUserMauroRuiz(){
		
		Worker worker=Worker.findByField("username", "mauro.ruiz");
		Set<Roles> roles=worker.getRoles();
		
		Iterator<Roles> iRol = roles.iterator();
		
		while (iRol.hasNext()){
			Roles rol = iRol.next();
			System.out.println(rol.getName());
		}
		
		assertTrue(roles.size()>0);
	}
}
