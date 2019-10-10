package uniandes.isis2304.EPSAndes.negocio;

public class ItemReceta implements VOItemReceta{

	private long idReceta;
	private long idMedicamento;
	private int cantidad;
	private String indicaciones;
	
	public ItemReceta(long idReceta, long idMedicamento, int cantidad, String indicaciones) {
		this.idReceta = idReceta;
		this.idMedicamento = idMedicamento;
		this.cantidad = cantidad;
		this.indicaciones = indicaciones;
	}
	
	public long getIdReceta() {
		return this.idReceta;
	}

	public void setId(int id) {
		this.idReceta = id;
	}

	@Override
	public long getIdMedicamento() {
		return idMedicamento;
	}

	@Override
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public String getIndicaciones() {
		return indicaciones;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "id Receta=" + idReceta + ", id medicamento=" + idMedicamento+ 
				", cantidad=" + cantidad + ", indicaciones=" + indicaciones + "]";
	}
	
}
