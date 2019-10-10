package uniandes.isis2304.EPSAndes.negocio;

public interface VOOrdenDeServicio {

	int getIdOrden();
	int getIdAfiliado();
	long getIdServicio();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();

}
