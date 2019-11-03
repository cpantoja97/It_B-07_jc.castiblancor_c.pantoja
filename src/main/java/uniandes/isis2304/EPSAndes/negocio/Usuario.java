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
	private TipoDocumento tipoDoc;

	/**
	 * número de documento del ususario.
	 */
	private long numDoc;

	/**
	 * Correo electronico del usuario
	 */
	private String correo;

	/**
	 * Rol del usuaio.
	 */
	private long id_rol;

	// ***********************************************+
	// Metodos.
	// ***********************************************+

	public Usuario() {
		this.correo = "";
	}

	public Usuario(long numDoc, TipoDocumento tipoDoc, String nombre2, String correo, long rol2) {
		this.correo = correo;
		this.nombre = nombre2;
		this.numDoc= numDoc;
		this.id_rol= rol2;
		this.tipoDoc = tipoDoc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoDocumento getTipoDoc() {
		return this.tipoDoc;
	}

	public long getNumDoc() {
		return this.numDoc;
	}

	public String getCorreo() {
		return this.correo;
	}

	public long getId_Rol() {
		return this.id_rol;
	}

	public void setCorreo(String correoElectronico) {
		this.correo = correoElectronico;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumDoc(int numeroDocumento) {
		this.numDoc = numeroDocumento;
	}

	public void setId_Rol(long rol) {
		this.id_rol = rol;
	}

	public void setTipoDoc(TipoDocumento tipoDocumento) {
		this.tipoDoc = tipoDocumento;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase
	 */
	public String toString() 
	{
		return "nombre=" + nombre + ", correo=" + correo+ 
				", numero documento=" + numDoc
				+ ", rol=" + id_rol+ "]";
	}

}
