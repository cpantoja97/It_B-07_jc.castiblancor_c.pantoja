package uniandes.isis2304.EPSAndes.negocio;

public interface VOMedicamento {

	long getId();
	
	String getNombre();
	
	String getDescripcion();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
