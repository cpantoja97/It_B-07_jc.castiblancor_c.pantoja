package uniandes.isis2304.EPSAndes.negocio;

public interface VOIPS {

	public long getIdIPS();

	public String getNombre();

	public String getLocalizacion();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
