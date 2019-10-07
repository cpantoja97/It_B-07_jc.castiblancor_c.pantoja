package uniandes.isis2304.EPSAndes.negocio;

public class MedicosAbscritos implements VOMedicosAbscritos{

	private int numeroDocumentoMedico;
	
	private long idIPS;
	
	public MedicosAbscritos(int numdocMed, long idIPS) {
		numeroDocumentoMedico = numdocMed;
		this.idIPS = idIPS;
	}
	
	public int getNumeroDocumentoMedico() {
		return this.numeroDocumentoMedico;
	}

	public long getIdIPS() {
		return this.idIPS;
	}

	public void setIdIPS(int idIPS) {
		this.idIPS = idIPS;
	}
	
	public void setNumeroDocumentoMedico(int numeroDocumentoMedico) {
		this.numeroDocumentoMedico = numeroDocumentoMedico;
	}
}
