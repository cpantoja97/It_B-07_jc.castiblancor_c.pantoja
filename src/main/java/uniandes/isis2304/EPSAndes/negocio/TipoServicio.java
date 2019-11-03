package uniandes.isis2304.EPSAndes.negocio;

public class TipoServicio implements VOTipoServicio{

	private Long id;
	private String nombre;

	public TipoServicio(Long pId, String pNombre) {
		this.id = pId;
		this.nombre = pNombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
