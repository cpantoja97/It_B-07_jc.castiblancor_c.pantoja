package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class PrestacionServicio implements VOPrestacionServicio{

	private int numDocAfiliado;
	private long idServicio;
	private long idIPSl;
	private Timestamp fechaHora;
	private long idRecepcionista;

	public PrestacionServicio() {
		numDocAfiliado = 0;
		idServicio = 0;
		idIPSl = 0;
		fechaHora = new Timestamp(System.currentTimeMillis());
		idRecepcionista = 0;
	}
	public PrestacionServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long idRecepcionista) {
		numDocAfiliado = numdocAf;
		this.idServicio = idServicio;
		this.idIPSl = idIPS;
		this.fechaHora = fechaHora;
		this.idRecepcionista = idRecepcionista;
	}
	
	public void setNUMDOC(int p) {
		numDocAfiliado = p;
	}
	public void setID_SERVICIO(long p) {
		idServicio = p;
	}
	public void setID_IPS(long p) {
		idIPSl = p;
	}
	public void setFECHAHORA(Timestamp p) {
		fechaHora = p;
	}
	public void setID_RECEPCIONISTA(long p) {
		idRecepcionista = p;
	}


	@Override
	public int getNumDocAfiliado() {
		return numDocAfiliado;
	}



	@Override
	public long getIdServicio() {
		return idServicio;
	}



	@Override
	public long getIdIPS() {
		return idIPSl;
	}



	@Override
	public Timestamp getFechaHora() {
		return fechaHora;
	}



	@Override
	public long getIdRecepcionista() {
		return idRecepcionista;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "idIPS=" + idIPSl + ", Documento Afiliado=" + numDocAfiliado+ 
				", idRecepcionista=" + idRecepcionista + ", idServicio=" +
				idServicio+ "]";
	}
}
