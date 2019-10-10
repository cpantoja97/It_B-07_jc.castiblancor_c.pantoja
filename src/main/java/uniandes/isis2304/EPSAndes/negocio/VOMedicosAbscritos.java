package uniandes.isis2304.EPSAndes.negocio;

public interface VOMedicosAbscritos {

	int getNumeroDocumentoMedico();
	
	long getIdIPS();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
