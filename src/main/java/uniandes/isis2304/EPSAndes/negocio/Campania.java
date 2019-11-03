package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class Campania implements VOCampania{

	private long id;
	private String nombre;
	private int afiliadosEsperados;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;

	public Campania() {
		this.id = 0;
		this.nombre = null;
		this.afiliadosEsperados = -1;
		this.fechaInicio = null;
		this.fechaFin = null;
	}
	public Campania(long id, String nombre, int pAfiliados, Timestamp pFechaInicio, Timestamp pFechaFin) {
		this.id = id;
		this.nombre = nombre;
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

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "id = " + id + ", nombre = " + nombre + ", Afiliados esperados = " + afiliadosEsperados + ", fechaInicio = " + fechaInicio + ", fechaFin = "+ fechaFin+"]";
	}
}
