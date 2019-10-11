package uniandes.isis2304.EPSAndes.negocio;

public class OrdenDeServicio implements VOOrdenDeServicio{

	private int numDocMedico;
	private int numDocAfiliado;
	private long idServicio;

	public OrdenDeServicio() {
		numDocMedico = 0;
		numDocAfiliado = 0;
		idServicio = 0;
	}
	public OrdenDeServicio(int numdocMedico, int numdocAfiliado, long idServicio) {
		numDocMedico = numdocMedico;
		numDocAfiliado = numdocAfiliado;
		this.idServicio = idServicio;
	}
	
	public void setID_MEDICO(int id) {
		this.numDocMedico = id;
	}
	public void setID_AFILIADO(int id) {
		this.numDocAfiliado = id;
	}
	public void setID_SERVICIO(long id) {
		this.idServicio = id;
	}
	public int getIdOrden() {
		return this.numDocMedico;
	}
	
	@Override
	public int getIdAfiliado() {
		return numDocAfiliado;
	}

	@Override
	public long getIdServicio() {
		return idServicio;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "idServicio=" + idServicio + ", Documento Afiliado=" + numDocAfiliado+ 
				", numero documento Medico=" + numDocMedico + "]";
	}
}