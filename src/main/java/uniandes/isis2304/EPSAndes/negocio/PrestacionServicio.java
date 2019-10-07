package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class PrestacionServicio implements VOPrestacionServicio{

	private int numDocAfiliado;
	private long idServicio;
	private long idIPSl;
	private Timestamp fechaHora;
	private long idRecepcionista;

	public PrestacionServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long idRecepcionista) {
		numDocAfiliado = numdocAf;
		this.idServicio = idServicio;
		this.idIPSl = idIPS;
		this.fechaHora = fechaHora;
		this.idRecepcionista = idRecepcionista;
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

}
