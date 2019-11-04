package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class ReservaServicio implements VOReservaServicio{

	int numDoc;
	long id_servicio;
	long id_IPS;
	Timestamp fechaHora;
	long campania;

	public ReservaServicio() {
		this.numDoc = -1;
		this.id_servicio = 0;
		this.id_IPS = 0;
		this.fechaHora = new Timestamp(System.currentTimeMillis());
		this.campania = -1;
	}

	public ReservaServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long campania) {
		numDoc = numdocAf;
		this.id_servicio = idServicio;
		this.id_IPS = idIPS;
		this.fechaHora = fechaHora;
		this.campania = campania;
	}
	public void setNumDoc(int p) {
		numDoc = p;
	}
	public void setId_servicio(long p) {
		id_servicio = p;
	}
	public void setId_IPS(long p) {
		id_IPS = p;
	}
	public void setFechaHora(Timestamp p) {
		fechaHora = p;
	}
	public void setCampania(long p) {
		campania = p;
	}
	public int getNumDoc() {
		return this.numDoc;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public long getIdServicio() {
		return id_servicio;
	}

	public long getIdIPS() {
		return id_IPS;
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
		return "Documento Afiliado =" + numDoc + ", IdIPS =" + id_IPS+
				", IdServicio =" + id_Servicio+", fecha =" + fechaHora+ "]";
	}
}
