package uniandes.isis2304.EPSAndes.negocio;

public class OrdenDeServicio implements VOOrdenDeServicio{

	private int numDocMedico;
	private int numDocAfiliado;
	private long idServicio;

	public OrdenDeServicio(int numdocMedico, int numdocAfiliado, long idServicio) {
		numDocMedico = numdocMedico;
		numDocAfiliado = numdocAfiliado;
		this.idServicio = idServicio;
	}
	
	public int getIdOrden() {
		return this.numDocMedico;
	}
	
	public void setIdOrden(int idOrden) {
		this.numDocMedico = idOrden;
	}


	@Override
	public int getIdAfiliado() {
		return numDocAfiliado;
	}

	@Override
	public long getIdServicio() {
		return idServicio;
	}
}