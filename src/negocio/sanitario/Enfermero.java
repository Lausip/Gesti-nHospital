package negocio.sanitario;

public class Enfermero implements Sanitario {
	
	private String id_Enfermero;
	private String nombre;
	private String apellidos;
	private String email;
	private String especialidad;
	
	
	public Enfermero(String id_Enfermero, String nombre, String apellidos, String email,String especialidad) {
		super();
		this.id_Enfermero = id_Enfermero;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.especialidad=especialidad;
	}

	public Enfermero() {}

	@Override
	public String toString() {
		return nombre + ", " + apellidos+ ", " + especialidad;
	}

	public String getId_Enfermero() {
		return id_Enfermero;
	}


	public void setId_Enfermero(String id_Enfermero) {
		this.id_Enfermero = id_Enfermero;
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


	public void setEmail(String email) {
		this.email = email;
	}

	public void setEspecilidad(String especialidad) {
		this.especialidad=especialidad;
		
	}

	@Override
	public String getId() {
		return id_Enfermero;
	}

	@Override
	public String getEspecialidad() {
		return especialidad;
	}

}


