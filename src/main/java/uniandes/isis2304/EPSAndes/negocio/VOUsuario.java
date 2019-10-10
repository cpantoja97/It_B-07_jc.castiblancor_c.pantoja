package uniandes.isis2304.EPSAndes.negocio;

public interface VOUsuario {

	String getNombre();

	TipoDocumento getTipoDocumento();

	long getNumeroDocumento();

	String getCorreoElectronico();

	long getRol();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
