package uniandes.isis2304.EPSAndes.negocio;

public class Servicio implements VOServicio{

	private long idServicio;

	private String nombre;

	public Servicio() {
		// TODO Auto-generated constructor stub
	}

	public Servicio(long idServicio2, String nombre2) {
		this.idServicio = idServicio2;
		this.nombre = nombre2;
	}

	public long getIdServicio() {
		return this.idServicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
