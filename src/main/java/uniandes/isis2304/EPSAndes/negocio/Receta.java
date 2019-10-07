package uniandes.isis2304.EPSAndes.negocio;

public class Receta implements VOReceta{

	private long idReceta;
	private int numDocMedico;
	private int numDocAfiliado;
	
	public Receta(long idReceta, int numdocMed, int numdocAf) {
		this.idReceta = idReceta;
		numDocMedico = numdocMed;
		numDocAfiliado = numdocAf;
	}
	
	public Receta(int pId) {
		idReceta = pId;
	}
	
	public long getIdReceta() {
		return this.idReceta;
	}

	public void setIdReceta(long idReceta) {
		this.idReceta = idReceta;
	}

	@Override
	public int getIdMedico() {
		return numDocMedico;
	}

	@Override
	public int getIdAfiliado() {
		return numDocAfiliado;
	}
	
	public void setIdMedico(int numDoc) {
		numDocMedico = numDoc;
	}
	
	public void setIdAfiliado(int numDoc) {
		numDocAfiliado = numDoc;
	}
}
