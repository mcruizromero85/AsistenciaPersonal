package com.asistp.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Asistencia {
	
	protected Usuario usuario;
	protected GregorianCalendar fechaHoraAsistencia;
	protected boolean temprano;
	protected int horaConsideradoTemprano;
	
	public Asistencia(){
		horaConsideradoTemprano=9;
	}
	
	public int getHoraConsideradoTemprano() {
		return horaConsideradoTemprano;
	}
	public void setHoraConsideradoTemprano(int horaConsideradoTemprano) {
		this.horaConsideradoTemprano = horaConsideradoTemprano;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public GregorianCalendar getFechaHoraAsistencia() {
		return fechaHoraAsistencia;
	}
	public void setFechaHoraAsistencia(GregorianCalendar fechaHoraAsistencia) {
		this.fechaHoraAsistencia = fechaHoraAsistencia;
	}
	public boolean isTemprano() {
		return temprano;
	}
	public void setTemprano(boolean temprano) {
		this.temprano = temprano;
	}
			
	public void registrarAsistencia() {
		
		int horaAsistencia = fechaHoraAsistencia.get(Calendar.HOUR_OF_DAY);
		int minutosAsistencia = fechaHoraAsistencia.get(Calendar.MINUTE);
		
		if (horaAsistencia > horaConsideradoTemprano ){
			this.temprano = false;
		}else{
			if (horaAsistencia == horaConsideradoTemprano && minutosAsistencia > 0 ){
				this.temprano = false;
			}else{
				this.temprano = true;
			}
		}
		
		
	}

	public List<Asistencia> listAll() {
		// TODO Auto-generated method stub
		ArrayList<Asistencia> assistanceList = new ArrayList<Asistencia>();
		assistanceList.add(new Asistencia());
		assistanceList.add(new Asistencia());
		return assistanceList;
	}
	
}
