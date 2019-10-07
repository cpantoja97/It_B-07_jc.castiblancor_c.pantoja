package uniandes.isis2304.EPSAndes.negocio;

public class ServiciosIPS implements VOServiciosIPS{

	private int capacidad;
	
	private String horarioAtencion;
	
	private long idServicio;
	
	private long idIPS;
	
	public ServiciosIPS(long idIPS, long idServicio, int capacidad, String horarioDeAtencion) {
		this.idIPS = idIPS;
		this.idServicio = idServicio;
		this.capacidad = capacidad;
		this.horarioAtencion = horarioDeAtencion;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}

	public String getHorarioAtencion() {
		return this.horarioAtencion;
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
	
	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}
	
	public void setIdIPS(int idIPS) {
		this.idIPS = idIPS;
	}
}
