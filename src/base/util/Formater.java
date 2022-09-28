package base.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.cita.CitaDTO;
import negocio.prescripcion.Vacuna;

public class Formater {

	public static CitaDTO fromResultSettoCita(ResultSet rs) {
		CitaDTO cita =null ;

		try {
			
				cita=new CitaDTO();
				cita.id_cita = rs.getString("id_cita");
				cita.id_paciente = rs.getString("id_paciente");				
				cita.horaEntradaPaciente=rs.getString("horaEntradaPaciente");
				cita.horaSalidaPaciente =rs.getString("horaSalidaPaciente");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				cita.fecha = LocalDate.parse((CharSequence) rs.getString("fecha"), formatter);
				cita.horaentradacita = rs.getString("horaEntradaCita");
				cita.horasalidacita = rs.getString("horaSalidaCita");
				cita.haAcudido = rs.getBoolean("haAcudido");
				cita.urgente = rs.getBoolean("urgente");
				cita.aprobadaPorAdministrador=rs.getBoolean("aprobadaPorAdministrador");

				cita.id_contacto = rs.getString("id_contacto");
				cita.id_Ubicacion = rs.getString("id_paciente");

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return cita;

	}

	public static List<CitaDTO> fromRsId_CitaToCitaDTO(ResultSet rs) {
		List<CitaDTO> cits = new ArrayList<>();
		try {
			while (rs.next()) {
				CitaDTO cita = new CitaDTO();

				cita.id_cita = rs.getString("id_citas");
				cits.add(cita);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cits;

	}

	public static List<CitaDTO> forfromResultSettoCita(ResultSet rs) {
		List<CitaDTO> citas = new ArrayList();
		try {
			while (rs.next()) {
				System.out.println(rs.toString());
				citas.add(fromResultSettoCita(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return citas;

	}
	
	private static Vacuna toVacuna(ResultSet rs) throws SQLException {
		return new Vacuna(rs.getString("id_vacuna"),rs.getString("nombre"),rs.getString("tipovacuna"));
	}

	public static List<Vacuna> forfromResultSettoVacunas(ResultSet rs) throws SQLException {
		List<Vacuna> vacunas = new ArrayList();
		while(rs.next()) {
			vacunas.add(toVacuna(rs));
		}
		return vacunas;
	}

}
