package negocio.sanitario;

public class Medico implements Sanitario{
	
	private String id_Medico;
	@Override
	public String toString() {
		return nombre + ", " + apellidos + ", " + especialidad;
	}
	


	private String nombre;
	private String apellidos;
	private String especialidad;
	private String email;
	
	
	public Medico(String id_Medico, String nombre, String apellidos, String email) {
		super();
		this.id_Medico = id_Medico;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}


	public Medico() {

	}


	public String getId_Medico() {
		return id_Medico;
	}


	public void setId_Medico(String id_Medico) {
		this.id_Medico = id_Medico;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public String getEspecialidad() {
		return especialidad;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}


	public void setEspecilidad(String especialidad) {
		this.especialidad=especialidad;
		
	}


	@Override
	public String getId() {
		return id_Medico;
	}

	
	
	
	

}
