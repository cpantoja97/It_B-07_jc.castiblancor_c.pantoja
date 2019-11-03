package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ReservaServicio implements VOReservaServicio{

	private int numDocAfiliado;
	private long idServicio;
	private long idIPS;
	private Timestamp fechaHora;
	private long campania;

	public ReservaServicio() {
		numDocAfiliado = -1;
		this.idServicio = 0;
		this.idIPS = 0;
		this.fechaHora = new Timestamp(System.currentTimeMillis());
		this.campania = -1;
	}

	public ReservaServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long campania) {
		numDocAfiliado = numdocAf;
		this.idServicio = idServicio;
		this.idIPS = idIPS;
		this.fechaHora = fechaHora;
		this.campania = campania;
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
	public void setCAMPANIA(long p) {
		campania = p;
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
	
	public long getCampania() {
		return campania;
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
