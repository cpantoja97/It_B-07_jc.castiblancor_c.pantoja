package uniandes.isis2304.EPSAndes.negocio;

public class Medico implements VOMedico{

	// ***********************************************+
	// Atributos.
	// ***********************************************+

	long numeroDocumento;

	int numeroRegistroMedico;

	// ***********************************************+
	// Metodos.
	// ***********************************************+

	public Medico() {

	}

	public Medico(long numDoc, int registroMedico) {
		this.numeroDocumento = numDoc;
		this.numeroRegistroMedico = registroMedico;
	}

	public void setNumeroRegistroMedico(int numeroRegistroMedico) {
		this.numeroRegistroMedico = numeroRegistroMedico;
	}

	public long getNumeroDocumento() {
		return numeroDocumento;
	}

	public int getNumeroRegistroMedico() {
		return this.numeroRegistroMedico;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
