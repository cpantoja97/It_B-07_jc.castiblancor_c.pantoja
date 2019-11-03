package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class Inhabilitacion implements VOInhabilitacion{

	Timestamp fechaInicio;

	Timestamp fechaFin;

	long IPS;

	long servicio;
	
	public Inhabilitacion() {
		this.fechaInicio = null;
		this.fechaFin = null;
		this.IPS = -1;
		this.servicio = -1;
	}

	public Inhabilitacion(Timestamp pFechaInicio, Timestamp pFechaFin,long pIPS,long pServicio) {
		this.fechaInicio = pFechaInicio;
		this.fechaFin = pFechaFin;
		this.IPS = pIPS;
		this.servicio = pServicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public long getIPS() {
		return IPS;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public long getServicio() {
		return servicio;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setIPS(long iPS) {
		IPS = iPS;
	}

	public void setServicio(long servicio) {
		this.servicio = servicio;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "Servicio = " + servicio + ", IPS = " + IPS + ", fechaInicio = " + fechaInicio + ", fechaFin = "+ fechaFin+"]";
	}
}
