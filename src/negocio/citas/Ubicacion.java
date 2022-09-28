package negocio.citas;

public class Ubicacion {

	public String id_ubicacion;

	public String nombre;

	public Ubicacion() {

	}

	public String getId_ubicacion() {
		return id_ubicacion;
	}

	public void setId_ubicacion(String id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
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
