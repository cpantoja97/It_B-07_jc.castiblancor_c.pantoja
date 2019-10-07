package uniandes.isis2304.EPSAndes.negocio;

public class Usuario implements VOUsuario{

	// ***********************************************+
	// Atributos.
	// ***********************************************+

	/**
	 * Nombre del usuario.
	 */
	private String nombre;

	/**
	 * tipo de documento del usuario.
	 */
	private TipoDocumento tipoDocumento;

	/**
	 * número de documento del ususario.
	 */
	private long numeroDocumento;

	/**
	 * Correo electronico del usuario
	 */
	private String correoElectronico;

	/**
	 * Rol del usuaio.
	 */
	private long rol;

	// ***********************************************+
	// Metodos.
	// ***********************************************+

	public Usuario() {
		this.correoElectronico = "";
	}

	public Usuario(long numDoc, TipoDocumento tipoDoc, String nombre2, String correo, long rol2) {
		this.correoElectronico = correo;
		this.nombre = nombre2;
		this.numeroDocumento= numDoc;
		this.rol= rol2;
		this.tipoDocumento = tipoDoc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public long getRol() {
		return this.rol;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setRol(long rol) {
		this.rol = rol;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
