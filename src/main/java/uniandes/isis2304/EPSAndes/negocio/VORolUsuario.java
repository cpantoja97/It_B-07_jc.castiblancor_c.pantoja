package uniandes.isis2304.EPSAndes.negocio;

public interface VORolUsuario {
	long getIdRolUsuario();
	String getNombre();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
