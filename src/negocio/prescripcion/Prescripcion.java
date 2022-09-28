package negocio.prescripcion;

import java.time.LocalDate;

import negocio.sanitario.Sanitario;

public class Prescripcion {
	
	private String id_Prescripcion;
	private Sanitario sanitario;
	private Medicamento medicamento;
	private LocalDate date;
	
	public Prescripcion(String id_Prescripcion, Sanitario sanitario, Medicamento medicamento, LocalDate date) {
		this.id_Prescripcion = id_Prescripcion;
		this.sanitario = sanitario;
		this.medicamento = medicamento;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return  id_Prescripcion + ", sanitario: " + sanitario + ", medicamento: " + medicamento;
	}
	
	public String getId() {
		return id_Prescripcion;
	}
	
	public Sanitario getSanitario() {
		return sanitario;
	}
	
	public Medicamento getMedicamento() {
		return medicamento;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setId(String id_Prescripcion) {
		this.id_Prescripcion = id_Prescripcion;
	}
	
	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}
	
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
