package negocio.administracion;

import java.util.List;

import negocio.administracion.fichas.FichaLaboral;
import negocio.citas.Cita;

public class Administrativo {
	
	private String id_Admin;
	private List<Cita> citas;
	private List<FichaLaboral> fichas;
	
	public Administrativo(String id_Admin, List<Cita> citas, List<FichaLaboral> fichas) {
		this.id_Admin = id_Admin;
		this.citas = citas;
		this.fichas = fichas;
	}
	
	public String getId() {
		return id_Admin;
	}
	
	public List<Cita> getCitas() {
		return citas;
	}
	
	public List<FichaLaboral> getFichas() {
		return fichas;
	}

}
