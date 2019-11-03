package uniandes.isis2304.EPSAndes.negocio;

public class Servicio implements VOServicio{

	private long idServicio;

	private String nombre;

	private long tipo;

	public Servicio() {
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

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "nombre=" + nombre + ", id=" + idServicio+"]";
	}
}
