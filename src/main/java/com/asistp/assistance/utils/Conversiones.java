package com.asistp.assistance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Conversiones {
	
	public static GregorianCalendar parserHoraFromStringToGregorianCalendar(String horaText) throws ParseException{
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		GregorianCalendar horaInGregorianCalendar = new GregorianCalendar();
		horaInGregorianCalendar.setTime(df.parse(horaText));
		
		return horaInGregorianCalendar;
	}
}
