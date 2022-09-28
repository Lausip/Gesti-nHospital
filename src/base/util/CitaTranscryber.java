package base.util;

import java.util.ArrayList;
import java.util.List;

import base.cita.CitaDTO;
import negocio.citas.Cita;

public class CitaTranscryber {

	public static Cita fromCitaDTOToCita(CitaDTO cita) {
		Cita citaaux = new Cita();

		citaaux.id_cita = cita.id_cita;
		citaaux.id_paciente = cita.id_paciente;
		citaaux.id_contacto = cita.id_contacto;
		citaaux.id_Ubicacion = cita.id_Ubicacion;
		citaaux.horaEntradaPaciente = cita.horaEntradaPaciente;
		citaaux.horaSalidaPaciente=cita.horaSalidaPaciente;
		citaaux.duracion = cita.duracion;
		citaaux.haAcudido = cita.haAcudido;
		citaaux.horasalidacita = cita.horasalidacita;
		citaaux.horaentradacita = cita.horaentradacita;
		citaaux.fecha=cita.fecha;
		citaaux.urgente = cita.urgente;
		citaaux.aprobadaPorAdministrador=cita.aprobadaPorAdministrador;
		return citaaux;

	}
	
	public static CitaDTO fromCitaToCitaDTO(Cita cita) {
		CitaDTO citaaux = new CitaDTO();

		citaaux.id_cita = cita.id_cita;
		citaaux.id_paciente = cita.id_paciente;
		citaaux.id_contacto = cita.id_contacto;
		citaaux.id_Ubicacion = cita.id_Ubicacion;
		citaaux.horaEntradaPaciente=cita.horaEntradaPaciente;
		citaaux.horaSalidaPaciente=cita.horaSalidaPaciente;
		citaaux.duracion = cita.duracion;
		citaaux.haAcudido = cita.haAcudido;
		citaaux.horasalidacita = cita.horasalidacita;
		citaaux.horaentradacita = cita.horaentradacita;
		
		citaaux.horaentradacita=cita.horaentradacita;
		citaaux.horasalidacita=cita.horasalidacita;
		citaaux.fecha=cita.fecha;
		//falta fehcaYHora
		citaaux.urgente = cita.urgente;
		
		citaaux.aprobadaPorAdministrador=cita.aprobadaPorAdministrador;
		return citaaux;

	}

	public static List<Cita> forfromCitaDTOToCita(List<CitaDTO> citasDTO) {

		List<Cita> citas = new ArrayList<Cita>();

		for (CitaDTO citadto : citasDTO) {

			citas.add(fromCitaDTOToCita(citadto));
		}

		return citas;
	}

}
