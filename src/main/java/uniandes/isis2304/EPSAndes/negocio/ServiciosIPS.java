package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ServiciosIPS implements VOServiciosIPS{

	private int capacidad;
	
	private Timestamp horarioInicio;
	private Timestamp horarioFin;
	
	private long idServicio;
	
	private long idIPS;
	
	
	public ServiciosIPS(long idIPS, long idServicio, int capacidad, Timestamp horarioInicio, Timestamp horarioFin) {
		this.idIPS = idIPS;
		this.idServicio = idServicio;
		this.capacidad = capacidad;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}

	public Timestamp getHorarioInicio() {
		return this.horarioInicio;
	}
	public Timestamp getHorarioFin() {
		return this.horarioFin;
	}

	public long getIdServicio() {
		return this.idServicio;
	}

	public long getIdIPS() {
		return this.idIPS;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void setHORARIOINICIO(Timestamp horario) {
		this.horarioInicio = horario;
	}
	
	public void setHORARIOFIN(Timestamp horario) {
		this.horarioFin = horario;
	}
	
	public void setIdIPS(int idIPS) {
		this.idIPS = idIPS;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "capacidad=" + capacidad + ", horarioAtencion=" + horarioInicio+ 
				", IdIPS=" + idIPS + ", IdServicio=" + idServicio+ "]";
	}
}
