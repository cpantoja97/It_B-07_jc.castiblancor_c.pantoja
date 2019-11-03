package uniandes.isis2304.EPSAndes.negocio;

public class IPS implements VOIPS{

	String nombre;

	String localizacion;

	long id_IPS;

	public IPS() {
		this.id_IPS = -1;
		this.nombre = null;
		this.localizacion=null;
	}
	public IPS(long idIPS2, String pLocalizacion, String pNombre) {
		this.id_IPS = idIPS2;
		this.nombre = pNombre;
		this.localizacion=pLocalizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setID_IPS(int id) {
		this.id_IPS = id;
	}

	public long getId_IPS() {
		return this.id_IPS;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getLocalizacion() {
		return this.localizacion;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "id=" + id_IPS + ", nombre=" + nombre+ 
				", localizacion=" + localizacion+ "]";
	}
}
