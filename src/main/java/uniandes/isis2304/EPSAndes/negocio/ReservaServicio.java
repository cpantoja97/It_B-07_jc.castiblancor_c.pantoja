package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ReservaServicio implements VOReservaServicio{

	private int numDocAfiliado;
	private long idServicio;
	private long idIPS;
	private Timestamp fechaHora;
	
	public ReservaServicio() {
		numDocAfiliado = 0;
		this.idServicio = 0;
		this.idIPS = 0;
		this.fechaHora = new Timestamp(System.currentTimeMillis());
	}
	
	public ReservaServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) {
		numDocAfiliado = numdocAf;
		this.idServicio = idServicio;
		this.idIPS = idIPS;
		this.fechaHora = fechaHora;
	}
	public void setNUMDOC(int p) {
		numDocAfiliado = p;
	}
	public void setID_SERVICIO(long p) {
		idServicio = p;
	}
	public void setID_IPS(long p) {
		idIPS = p;
	}
	public void setFECHAHORA(Timestamp p) {
		fechaHora = p;
	}
	
	public int getnumDocAfiliado() {
		return this.numDocAfiliado;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public long getIdIPS() {
		return idIPS;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "numero Documento Afiliado =" + numDocAfiliado + ", IdIPS =" + idIPS+
				", IdServicio =" + idServicio+ "]";
	}
}
