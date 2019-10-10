package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ReservaServicio implements VOReservaServicio{

	private int numDocAfiliado;
	private long idServicio;
	private long idIPS;
	private Timestamp fechaHora;
	
	public ReservaServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) {
		numDocAfiliado = numdocAf;
		this.idServicio = idServicio;
		this.idIPS = idIPS;
		this.fechaHora = fechaHora;
	}
	
	public int getnumDocAfiliado() {
		return this.numDocAfiliado;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setnumDocAfiliado(int numd) {
		numDocAfiliado = numd;
	}
	
	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
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
