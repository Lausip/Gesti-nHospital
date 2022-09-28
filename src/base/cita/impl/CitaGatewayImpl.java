package base.cita.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.cita.CitaDTO;
import base.cita.CitaGateway;
import base.jdbc.Jdbc;
import base.util.Formater;
import negocio.citas.Cita;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;

public class CitaGatewayImpl<T> implements CitaGateway<T> {

	private static final String FIND_By_MEDICO_ID = "select id_citas from citasYmedicos where id_medico=? ";

	private static final String FINDALL_BY_ID_AND_FECHA = "select * from cita where id_cita=? and fecha=?;";
	private static final String ADD_CITA_VENTANACITA = "insert into cita values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_HORAS_BYID = "update cita set horaEntradaPaciente=?,horaSalidaPaciente=? where id_cita=?";
	private static final String UPDATE_HORASCITA_BYID = "update cita set horaEntradaCita=?,horaSalidaCita=? where id_cita=?";
	private static final String UPDATE_HA_ACUDIDO = "update cita set haacudido=? where id_cita=?";

	private static final String UPDATE_CAUSAS_CITA = "insert into citaycausas values(?,?)";

	private static final String UPDATE_URGENTE = "update cita set urgente=? where id_cita=?";
	private static final String SETACCEPTED = "update cita set aprobadaPorAdministrador=? where id_cita=?";


	private static final String FINDALL_BY_ID = "select * from cita where id_cita=?;";
	private static final String INSERT_TABLA_HISTORIAL = "insert into historial values(?,?,?,?,?)";
	private static final String INSERT_MEDICO_CITA = "insert into citasymedicos values(?,?)";
	private static final String INSERT_ENFERMERO_CITA = "insert into citasyenfermeros values(?,?)";
	private static final String INSERT_CONTACTO = "insert into contacto values(?,?)";

	private static final String FIND_BY_ID_PACIENTE = "select * from cita where id_paciente=?;";

	
	

	private static final String FINDALLUNAPPROVED = "select * from CITA c WHERE c.APROBADAPORADMINISTRADOR=false" ;


	@Override
	public void add(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> findById(String id) {
		Connection c = null;

		List<CitaDTO> citas = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FINDALL_BY_ID);

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			citas = Formater.forfromResultSettoCita(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (List<T>) citas;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CitaDTO> findByIdMedico(String id) {
		Connection c = null;

		List<CitaDTO> citas = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FIND_By_MEDICO_ID);

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			citas = Formater.fromRsId_CitaToCitaDTO(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return citas;
	}

	@Override
	public List<CitaDTO> findAllByIdAndDate(List<CitaDTO> citas, String fecha) {
		Connection c = null;
		List<CitaDTO> citasFINDALL = new ArrayList<>();
		CitaDTO citaAux=null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FINDALL_BY_ID_AND_FECHA);
			for (CitaDTO cita : citas) {

				ps.setString(1, cita.id_cita);
				ps.setString(2, fecha);

				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					citaAux = Formater.fromResultSettoCita(rs);
				}
				
				if (citaAux != null) {
					citasFINDALL.add(citaAux);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return citasFINDALL;
	}

	@Override
	public void UpdateHoraEntradas(CitaDTO cita) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(UPDATE_HORAS_BYID);

			ps.setString(1, cita.horaEntradaPaciente);
			ps.setString(2, cita.horaSalidaPaciente);

			ps.setString(3, cita.id_cita);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void updateAcudirCita(boolean rdbtnHaAcudido, String id) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(UPDATE_HA_ACUDIDO);

			ps.setBoolean(1, rdbtnHaAcudido);
			ps.setString(2, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	@Override
	public void anadirCita(Cita cita) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement(ADD_CITA_VENTANACITA);
			ps.setString(1, cita.id_cita);
			ps.setString(2, cita.id_paciente);
			// No asignar hora
			if (cita.horaentradacita == "-") {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				ps.setString(3, "" + formatter.format(cita.fecha));
				ps.setString(4, cita.horaEntradaPaciente);
				ps.setString(5, cita.horaSalidaPaciente);
				ps.setString(6, cita.horaentradacita);
				ps.setString(7, cita.horasalidacita);
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				ps.setString(3, "" + formatter.format(cita.fecha));
				ps.setString(4, cita.horaEntradaPaciente);
				ps.setString(5, cita.horaSalidaPaciente);
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
				ps.setString(6, formato.format(cita.horaEntradaCita));
				ps.setString(7, formato.format(cita.horaSalidaCita));
			}
			ps.setBoolean(8, cita.haAcudido);
			ps.setBoolean(9, cita.urgente);
			ps.setBoolean(10, cita.aprobadaPorAdministrador);
			ps.setString(11, cita.id_contacto);
			ps.setString(12, cita.id_Ubicacion);
			ps.setString(13, cita.id_historial);

			ps.setString(14, cita.id_especialista);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void updateCausasCita(String causa, String id_cita) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(UPDATE_CAUSAS_CITA);

			ps1.setString(1, causa);
			ps1.setString(2, id_cita);
			ps1.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void anadirMedicoAlaCita(String id_cita, Medico m) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(INSERT_MEDICO_CITA);

			ps1.setString(1, id_cita);
			ps1.setString(2, m.getId());
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void anadirEnfermeroAlaCita(String id_cita, Enfermero en) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(INSERT_ENFERMERO_CITA);

			ps1.setString(1, id_cita);
			ps1.setString(2, en.getId());
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void anadirContacto(String id_contacto, String text) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(INSERT_CONTACTO);

			ps1.setString(1, id_contacto);
			ps1.setString(2, text);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	@Override
	public List<CitaDTO> findAllUnapproved() {
		Connection c = null;

		List<CitaDTO> citas = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FINDALLUNAPPROVED);
			ResultSet rs = ps.executeQuery();

			citas = Formater.forfromResultSettoCita(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return  citas;
	}

	@Override
	public void updateUrgenciaCita(String id_cita, boolean urgente) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(UPDATE_URGENTE);

			ps.setBoolean(1, urgente);
			ps.setString(2, id_cita);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		
	}

	@Override
	public void setAcepted(String id_cita) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(SETACCEPTED);

			ps.setBoolean(1, true);
			ps.setString(2, id_cita);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		
	}

	@Override
	public void UpdateHoraEntradasCita(CitaDTO cita) {		
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(UPDATE_HORASCITA_BYID);

			ps.setString(1, cita.horaentradacita);
			ps.setString(2, cita.horasalidacita);

			ps.setString(3, cita.id_cita);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void updatePrescripciones(String id_cita, String id_prescrp) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement("insert into citayprescripcion values(?,?)");

			ps1.setString(1, id_cita);
			ps1.setString(2, id_prescrp);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
	}

	public void removePrescripcion(String id_cita, String id_p) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c
					.prepareStatement("delete from citayprescripcion where id_cita=? and id_prescripcion=?");

			ps1.setString(1, id_cita);
			ps1.setString(2, id_p);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void removeMedicamento(String id_cita, String id_p, String cantidad, String intervalo, String duracion,
			String otrainfo) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(
					"delete from citaymedicamento where id_cita=? and id_medicamento=? and cantidad=? and intervalo=? and duracion=? and otrainfo=?");

			ps1.setString(1, id_cita);
			ps1.setString(2, id_p);
			ps1.setString(3, cantidad);
			ps1.setString(4, intervalo);
			ps1.setString(5, duracion);
			ps1.setString(6, otrainfo);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
	}

	public void updateMedicamentos(String id_cita, String id_p, String cantidad, String intervalo, String duracion,
			String otrainfo) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement("insert into citaymedicamento values(?,?,?,?,?,?)");

			ps1.setString(1, id_cita);
			ps1.setString(2, id_p);
			ps1.setString(3, cantidad);
			ps1.setString(4, intervalo);
			ps1.setString(5, duracion);
			ps1.setString(6, otrainfo);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void removeCausa(String id_cita, String id) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement("delete from citaycausas where id_cita=? and id_causa=?");

			ps1.setString(1, id_cita);
			ps1.setString(2, id);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
	}

	public List<CitaDTO> todoslasCitasPaciente(String id_paciente) {
		Connection c = null;
		List<CitaDTO> citas = new ArrayList<>();
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FIND_BY_ID_PACIENTE);
			ps.setString(1, id_paciente);
			rs = ps.executeQuery();
			while (rs.next()) {
				CitaDTO cita = new CitaDTO();
				cita.id_cita = rs.getString("id_cita");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				cita.fecha = fecha;
				cita.id_Ubicacion = rs.getString("id_ubicacion");
				citas.add(cita);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return citas;

	}

}
