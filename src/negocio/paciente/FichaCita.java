package negocio.paciente;

import java.util.List;

import negocio.prescripcion.Enfermedad;
import negocio.prescripcion.Vacuna;
import negocio.prescripcion.Prescripcion;

public class FichaCita {
	
	private String id_FichaCita;
	private Enfermedad enfermedad;
	private Vacuna vacuna;
	private List<Prescripcion> prescripciones;
	
	public FichaCita(String id_FichaCita, Enfermedad enfermedad, Vacuna vacuna, List<Prescripcion> prescripciones) {
		this.id_FichaCita = id_FichaCita;
		this.enfermedad = enfermedad;
		this.vacuna = vacuna;
		this.prescripciones = prescripciones;
	}
	
	@Override
	public String toString() {
		return  id_FichaCita + ", enfermedad: " + enfermedad + ", vacuna: " + vacuna;
	}
	
	public String getId() {
		return id_FichaCita;
	}
	
	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	
	public Vacuna getVacuna() {
		return vacuna;
	}
	
	public List<Prescripcion> getPrescripciones() {
		return prescripciones;
	}

}
