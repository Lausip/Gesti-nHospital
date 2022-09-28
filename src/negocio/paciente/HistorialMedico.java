package negocio.paciente;

import java.util.List;

public class HistorialMedico {
	
	private String id_Historial;
	private List<FichaCita> fichas;
	
	public HistorialMedico(String id_Historial, List<FichaCita> fichas) {
		this.id_Historial = id_Historial;
		this.fichas = fichas;
	}
	
	public String getId() {
		return id_Historial;
	}
	
	public List<FichaCita> getFichas() {
		return fichas;
	}

}
