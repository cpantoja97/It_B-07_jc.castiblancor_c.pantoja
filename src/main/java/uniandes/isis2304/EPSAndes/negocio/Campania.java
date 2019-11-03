package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class Campania implements VOCampania{

	private long id;
	private String nombre;
	private int afiliadosEsperados;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;

	public Campania(int pAfiliados, Timestamp pFechaInicio, Timestamp pFechaFin) {
		this.afiliadosEsperados = pAfiliados;
		this.fechaInicio = pFechaInicio;
		this.fechaFin = pFechaFin;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setAfiliadosEsperados(int afiliadosEsperados) {
		this.afiliadosEsperados = afiliadosEsperados;
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAfiliadosEsperados() {
		return afiliadosEsperados;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

}
