package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public class Afiliado implements  VOAfiliado{

	Timestamp fechaNacimiento;

	int numDoc;

	public Afiliado(int numDoc2, Timestamp fechaNacimiento2) {
		this.fechaNacimiento=fechaNacimiento2;
		this.numDoc = numDoc2;
	}

	public Afiliado() {
		// TODO Auto-generated constructor stub
	}

	public Timestamp getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getNumDoc() {
		return this.numDoc;
	}

	public void setNumDoc(int numDoc) {
		this.numDoc = numDoc;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "numero documento=" + numDoc + ", fecha=" + fechaNacimiento+"]";
	}

}
