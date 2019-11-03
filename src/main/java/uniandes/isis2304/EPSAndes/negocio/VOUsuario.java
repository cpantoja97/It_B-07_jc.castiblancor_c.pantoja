package uniandes.isis2304.EPSAndes.negocio;

public interface VOUsuario {

	String getNombre();

	TipoDocumento getTipoDoc();

	long getNumDoc();

	String getCorreo();

	long getId_Rol();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
