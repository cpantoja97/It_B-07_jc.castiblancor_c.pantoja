package uniandes.isis2304.EPSAndes.negocio;

public class Medico implements VOMedico{

	// ***********************************************+
	// Atributos.
	// ***********************************************+

	long numDoc;

	int regMedico;

	// ***********************************************+
	// Metodos.
	// ***********************************************+

	public Medico() {
		this.numDoc =0;
		this.regMedico =0;
	}

	public Medico(long numDoc, int registroMedico) {
		this.numDoc = numDoc;
		this.regMedico = registroMedico;
	}

	public void setRegMedico(int numeroRegistroMedico) {
		this.regMedico = numeroRegistroMedico;
	}

	public long getNumDoc() {
		return numDoc;
	}

	public int getRegMedico() {
		return this.regMedico;
	}

	public void setNumDoc(int numeroDocumento) {
		this.numDoc = numeroDocumento;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "numero documento Medico=" + numDoc + 
				", numero Registro Medico=" + regMedico+ "]";
	}
}
