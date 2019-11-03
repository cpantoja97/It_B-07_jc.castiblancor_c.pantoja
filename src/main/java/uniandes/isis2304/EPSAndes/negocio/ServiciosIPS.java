package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ServiciosIPS implements VOServiciosIPS{

	int capacidad;
	
	Timestamp horarioInicio;
	Timestamp horarioFin;
	
	private long idServicio;
	
	private long idIPS;
	
	public ServiciosIPS() {
		this.idIPS = -1;
		this.idServicio = -1;
		this.capacidad = -1;
		this.horarioInicio = null;
		this.horarioFin = null;
	}
	
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

	public void setID_SERVICIO(int idServicio) {
		this.idServicio = idServicio;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void setHorarioInicio(Timestamp horario) {
		this.horarioInicio = horario;
	}
	
	public void setHorarioFin(Timestamp horario) {
		this.horarioFin = horario;
	}
	
	public void setID_IPS(int idIPS) {
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
