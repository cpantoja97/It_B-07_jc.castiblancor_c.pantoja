package uniandes.isis2304.EPSAndes.negocio;

public class Medicamento implements VOMedicamento{

	private long id;
	
	private String nombre;
	
	private String descripcion;
	
	
	public Medicamento(long idMedicamento, String nombre2, String descripcion2) {
		id = idMedicamento;
		nombre = nombre2;
		descripcion = descripcion2;
	}

	public long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "id=" + id + ", nombre=" + nombre+ 
				", descricion=" + descripcion+ "]";
	}

}
