package uniandes.isis2304.EPSAndes.negocio;

public class Recepcionista implements VORecepcionista {

	private int numDoc;

	private long idIPS;

	public Recepcionista(int numDoc2, long idIPS2) {
		this.numDoc = numDoc2;
		this.idIPS = idIPS2;
	}

	public Recepcionista() {
		// TODO Auto-generated constructor stub
	}

	public int getNumDoc() {
		return this.numDoc;
	}

	public long getIdIPS() {
		return this.idIPS;
	}

	public void setIdIPS(int idIPS) {
		this.idIPS = idIPS;
	}

	public void setNumDoc(int numDoc) {
		this.numDoc = numDoc;
	}
}
