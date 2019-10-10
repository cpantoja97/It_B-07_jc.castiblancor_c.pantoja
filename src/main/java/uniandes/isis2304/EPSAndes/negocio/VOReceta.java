package uniandes.isis2304.EPSAndes.negocio;

public interface VOReceta {
	long getIdReceta();
	int getIdMedico();
	int getIdAfiliado();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
