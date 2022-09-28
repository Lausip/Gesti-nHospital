package negocio.administracion.fichas;

import java.time.LocalDate;

public class JornadaLaboral {
	
	private String id_JornadaLaboral;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public JornadaLaboral(String id_JornadaLaboral, LocalDate fechaInicio, LocalDate fechaFin) {
		this.id_JornadaLaboral = id_JornadaLaboral;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public String getId() {
		return id_JornadaLaboral;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}

}
