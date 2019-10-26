package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class Campana implements VOCampana{

	private long id;
	private int afiliadosEsperados;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;

	public void setId(long id) {
		this.id = id;
	}


	public Campana(int pAfiliados, Timestamp pFechaInicio, Timestamp pFechaFin) {
		this.afiliadosEsperados = pAfiliados;
		this.fechaInicio = pFechaInicio;
		this.fechaFin = pFechaFin;
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
