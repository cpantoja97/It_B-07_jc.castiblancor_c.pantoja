package uniandes.isis2304.EPSAndes.negocio;

public class RolUsuario implements VORolUsuario {

	long id;
	String nombre;

	public RolUsuario(long pId, String pNom) {
		id = pId;
		nombre = pNom;
	}

	public long getIdRolUsuario() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setIdRolUsuario(long idn) {
		id = idn;
	}
	public void setNombre(String pnombre) {
		nombre = pnombre;
	}
	
	public String toString() 
	{
		return "RolUsuario [id=" + id + ", nombre=" + nombre + "]";
	}
}
