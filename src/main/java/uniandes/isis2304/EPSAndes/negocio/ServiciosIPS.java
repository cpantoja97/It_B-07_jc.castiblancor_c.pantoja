package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ServiciosIPS implements VOServiciosIPS{

	int capacidad;
	
	Timestamp horarioInicio;
	Timestamp horarioFin;
	
	private long id_Servicio;
	
	private long id_IPS;
	
	public ServiciosIPS() {
		this.id_IPS = -1;
		this.id_Servicio = -1;
		this.capacidad = -1;
		this.horarioInicio = null;
		this.horarioFin = null;
	}
	
	public ServiciosIPS(long idIPS, long idServicio, int capacidad, Timestamp horarioInicio, Timestamp horarioFin) {
		this.id_IPS = idIPS;
		this.id_Servicio = idServicio;
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

	public long getId_Servicio() {
		return this.id_Servicio;
	}

	public long getId_IPS() {
		return this.id_IPS;
	}

	public void setID_SERVICIO(int idServicio) {
		this.id_Servicio = idServicio;
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
		this.id_IPS = idIPS;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "capacidad=" + capacidad + ", horarioAtencion=" + horarioInicio+ 
				", IdIPS=" + id_IPS + ", IdServicio=" + id_Servicio+ "]";
	}
}
