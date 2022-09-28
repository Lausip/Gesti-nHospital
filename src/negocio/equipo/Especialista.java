package negocio.equipo;

public class Especialista {
	public String id_especialista;
	public String nombre;

	public Especialista() {
	}

	public String getId_especialista() {
		return id_especialista;
	}

	public void setId_especialista(String id_especialista) {
		this.id_especialista = id_especialista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
