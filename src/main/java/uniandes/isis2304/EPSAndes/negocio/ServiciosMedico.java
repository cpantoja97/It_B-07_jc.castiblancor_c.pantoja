package uniandes.isis2304.EPSAndes.negocio;

public class ServiciosMedico implements VOServiciosMedico{

	private String especialidad;

	private long idServicio;

	private int numeroDocumento;

	public ServiciosMedico(int numdocMed, long idServicio, String especialidad) {
		numeroDocumento = numdocMed;
		this.idServicio = idServicio;
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public long getIdServicio() {
		return this.idServicio;
	}

	public int getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
}