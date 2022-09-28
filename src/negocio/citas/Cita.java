package negocio.citas;

import java.time.LocalDate;
import java.time.LocalTime;

import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;

public class Cita {
	
	public String id_cita;

	public String id_paciente;

	public String id_Ubicacion;

	public LocalDate fecha;
	public String fechaS;

	public String horaCita;

	public long duracion;

	public String horaentradacita;
	public String horasalidacita;

	public String horaEntradaPaciente;
	public String horaSalidaPaciente;

	public LocalTime horaEntradaCita;
	public LocalTime horaSalidaCita;

	public boolean haAcudido;

	public boolean urgente;

	public boolean aprobadaPorAdministrador = false;

	public String id_contacto;
	
	public String id_historial;
	public String id_especialista;
	
	public Cita(String id_cita, String id_paciente, LocalDate fecha, String horaentradapaciente, String horasalidapaciente, LocalTime horaentradacita, LocalTime horasalidacita,
			boolean haAcudido, boolean urgente, boolean aprobadaporadmin, String id_contacto, String id_ubicacion, String id_historial,String id_especialista) {
		this.id_cita = id_cita;
		this.id_paciente=id_paciente;
		this.fecha=fecha;
		this.horaEntradaPaciente=horaentradapaciente;
		this.horaSalidaPaciente=horasalidapaciente;
		this.horaEntradaCita=horaentradacita;
		this.horaSalidaCita=horasalidacita;
		this.haAcudido=haAcudido;
		this.urgente=urgente;
		this.aprobadaPorAdministrador=aprobadaporadmin;
		this.id_contacto=id_contacto;
		this.id_Ubicacion=id_ubicacion;
		this.id_historial=id_historial;
		this.id_especialista=id_especialista;
	}

	public Cita(String id_cita, String id_paciente, LocalDate fecha, LocalTime localHoraEntrada,
			LocalTime localHoraSalida, boolean selected, String contacto, String id_Ubicacion) {
	
		this.id_cita = id_cita;
		this.id_paciente = id_paciente;
		this.id_Ubicacion = id_Ubicacion;
//		this.fecha = LocalDate.parse(fecha);
		this.horaentradacita=localHoraEntrada.toString();
		this.horasalidacita=localHoraSalida.toString();
		this.urgente = selected;
		this.id_contacto = contacto;
		
	}

	
	public Cita(String id_cita, String id_paciente, LocalDate fecha, LocalTime localHoraEntrada,
			LocalTime localHoraSalida, boolean selected, String contacto, String id_Ubicacion, boolean aprobada) {
	
		this.id_cita = id_cita;
		this.id_paciente = id_paciente;
		this.id_Ubicacion = id_Ubicacion;
		this.fecha = fecha;
		this.haAcudido=false;
		this.horaentradacita=localHoraEntrada.toString();
		this.horaEntradaCita=LocalTime.now();
		this.horaSalidaCita=LocalTime.now();
		this.horasalidacita=localHoraSalida.toString();
		this.urgente = selected;
		this.id_contacto = contacto;
		this.aprobadaPorAdministrador=aprobada;
		
	}

	public Cita(String id_cita, String id_paciente, String id_Ubicacion, LocalDate fechaYHora,
			long duracion, String horaEntradaCita, String horaSalidaCita, boolean haAcudido, boolean urgente,
			String id_contacto) {
		super();
		this.id_cita = id_cita;
		this.id_paciente = id_paciente;
		this.id_Ubicacion = id_Ubicacion;
		this.fecha = fechaYHora;
		this.duracion = duracion;
		this.horaEntradaCita = LocalTime.parse(horaEntradaCita);
		this.horaSalidaCita = LocalTime.parse(horaSalidaCita);
		this.haAcudido = haAcudido;
		this.urgente = urgente;
		this.id_contacto = id_contacto;

	}


	public Cita() {
		// TODO Auto-generated constructor stub
	}

	public Cita(String id_cita, String id_paciente, LocalDate fecha, String horaentradapaciente,
			String horasalidapaciente, String horaentradacita, String horasalidacita, boolean haAcudido, boolean urgente,
			boolean aprobadaporadmin, String id_contacto, String ubicacion,  String idHistorialPaciente,
			String id_especialista) {
		this.id_cita = id_cita;
		this.id_paciente = id_paciente;
		this.fecha = fecha;
		this.horaEntradaPaciente = horaentradapaciente;
		this.horaSalidaPaciente = horasalidapaciente;
		this.horaentradacita = horaentradacita;
		this.horasalidacita = horasalidacita;
		this.haAcudido = haAcudido;
		this.urgente = urgente;
		this.aprobadaPorAdministrador = aprobadaporadmin;
		this.id_contacto = id_contacto;
		this.id_Ubicacion = ubicacion;
		this.id_historial = idHistorialPaciente;
		this.id_especialista=id_especialista;
	}

	public String getId_cita() {
		return id_cita;
	}

	public String getId_paciente() {
		return id_paciente;
	}

	public String getId_Ubicacion() {
		return id_Ubicacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public long getDuracion() {
		return duracion;
	}

	public LocalTime gethoraEntrada() {
		return horaEntradaCita;
	}

	public LocalTime gethoraSalida() {
		return horaSalidaCita;
	}

	public boolean isHaAcudido() {
		return haAcudido;
	}

	public boolean isUrgente() {
		return urgente;
	}

	public String getId_contacto() {
		return id_contacto;
	}
	
	

	public boolean isAprobadaPorAdministrador() {
		return aprobadaPorAdministrador;
	}

	public void setAprobadaPorAdministrador(boolean aprovadaPorAdministrador) {
		this.aprobadaPorAdministrador = aprovadaPorAdministrador;
	}

	@Override
	public String toString() {
		return "CitaDTO [id_cita=" + id_cita + ", id_paciente=" + id_paciente + ", id_Ubicacion=" + id_Ubicacion
				+ ", fechaYHora=" + fecha + ", duracion=" + duracion + ", horaEntrada="
				+ horaentradacita + ", horaSalida=" + horasalidacita + ", haAcudido=" + haAcudido + ", urgente=" + urgente
				+ ", id_contacto=" + id_contacto + "]";
	}

	public String toStringParaLista() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.getFecha().toString());
		sb.append("]");
		sb.append("[");
		sb.append(this.horaentradacita);
		sb.append("]");
		sb.append(" Cita con: ");
		sb.append(this.getPacienteNombreYApellidos());

		return sb.toString();
	}

	private String getPacienteNombreYApellidos() {

		DataBaseConsultor dc = new DatabaseConsultorImpl();
		return dc.getNombreYApellidosFromPaciente(this.getId_paciente());

	}

}
