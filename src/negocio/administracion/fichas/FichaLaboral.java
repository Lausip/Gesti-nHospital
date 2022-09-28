package negocio.administracion.fichas;

import java.util.List;

import negocio.sanitario.Medico;

public class FichaLaboral {
	
	private String id_FichaLaboral;
	private Medico medico;
	private List<JornadaLaboral> jornadasLaborales;
	
	public FichaLaboral(String id_FichaLaboral, Medico medico, List<JornadaLaboral> jornadasLaborales) {
		this.id_FichaLaboral = id_FichaLaboral;
		this.medico = medico;
		this.jornadasLaborales = jornadasLaborales;
	}
	
	@Override
	public String toString() {
		return  id_FichaLaboral + ", sanitario: " + medico + ", jornadas: " + jornadasLaborales.size();
	}
	
	public String getId() {
		return id_FichaLaboral;
	}
	
	public Medico getSanitario() {
		return medico;
	}
	
	public List<JornadaLaboral> getJornadasLaborales() {
		return jornadasLaborales;
	}

}
