package com.asistp.assistance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Conversiones {
	
	public static GregorianCalendar parserHoraFromStringToGregorianCalendar(String horaText) throws ParseException{
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		GregorianCalendar horaInGregorianCalendar = new GregorianCalendar();
		int year=horaInGregorianCalendar.get(Calendar.YEAR);
		int month=horaInGregorianCalendar.get(Calendar.MONTH);
		int day=horaInGregorianCalendar.get(Calendar.DAY_OF_MONTH);
		
		horaInGregorianCalendar.setTime(df.parse(horaText));
		horaInGregorianCalendar.set(Calendar.YEAR,year );
		horaInGregorianCalendar.set(Calendar.MONTH, month);
		horaInGregorianCalendar.set(Calendar.DAY_OF_MONTH, day);
		
		return horaInGregorianCalendar;
	}

	public static String parserCalendarToHoraString(Calendar gcHora) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(gcHora.getTime());
	}

	public static GregorianCalendar getDateTimeFinishByDay(GregorianCalendar now) {
		GregorianCalendar gcHoraWithFinishHour = new GregorianCalendar();
		
		gcHoraWithFinishHour.set(Calendar.YEAR, now.get(Calendar.YEAR));
		gcHoraWithFinishHour.set(Calendar.MONTH, now.get(Calendar.MONTH) );
		gcHoraWithFinishHour.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) );
		
		gcHoraWithFinishHour.set(Calendar.HOUR_OF_DAY, 23);
		gcHoraWithFinishHour.set(Calendar.MINUTE, 59 );
		gcHoraWithFinishHour.set(Calendar.SECOND, 59 );
		
		return gcHoraWithFinishHour;
	}

	public static GregorianCalendar getDateTimeInitialByDay(GregorianCalendar now) {
		GregorianCalendar gcHoraWithInitialHour = new GregorianCalendar();
		
		gcHoraWithInitialHour.set(Calendar.YEAR, now.get(Calendar.YEAR));
		gcHoraWithInitialHour.set(Calendar.MONTH, now.get(Calendar.MONTH) );
		gcHoraWithInitialHour.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) );
		
		gcHoraWithInitialHour.set(Calendar.HOUR_OF_DAY, 0);
		gcHoraWithInitialHour.set(Calendar.MINUTE, 0 );
		gcHoraWithInitialHour.set(Calendar.SECOND, 0 );
		
		return gcHoraWithInitialHour;
	}
}
