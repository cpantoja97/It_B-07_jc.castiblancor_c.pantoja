package uniandes.isis2304.EPSAndes.negocio;

public class IPS implements VOIPS{

	String nombre;

	String localizacion;

	long idIPS;

	public IPS(long idIPS2, String pLocalizacion, String pNombre) {
		this.idIPS = idIPS2;
		this.nombre = pNombre;
		this.localizacion=pLocalizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdIPS(int id) {
		this.idIPS = id;
	}

	public long getIdIPS() {
		return this.idIPS;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getLocalizacion() {
		return this.localizacion;
	}
}
