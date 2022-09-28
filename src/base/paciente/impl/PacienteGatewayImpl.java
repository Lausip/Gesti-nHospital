package base.paciente.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.jdbc.Jdbc;
import base.medico.MedicoDTO;
import base.paciente.PacienteDTO;
import base.paciente.PacienteGateway;
import negocio.paciente.Paciente;

public class PacienteGatewayImpl<T> implements PacienteGateway<T> {

	private final static String FIND_BY_ID = "select nombre, APELLIDO from PACIENTE where id_paciente=?;";
	private final static String FIND_BY_LETTER_NAME = "Select * from paciente where nombre like ? ||'%'";
	private final static String FIND_BY_LETTER_APELLIDOS = "Select * from paciente where apellido like ?||'%'";
	private final static String FIND_BY_IDSANITARIA = "Select * from paciente where id_sanitaria like ? ||'%'";
	private final static String FIND_BY_NHISTORIAL = "Select * from paciente where id_historial like ? ||'%'";
	private final static String FIND_BY_NOMBREAPELLIDO = "Select * from paciente where nombre like ? ||'%' and apellido like ? || '%'";
	private final static String FIND_BY_NOMBREHISTORIAL = "Select * from paciente where nombre like ? ||'%' and ID_HISTORIAL= ?";
	private final static String FIND_BY_NOMBRESANITARIA = "Select * from paciente where nombre like ? ||'%' and id_sanitaria= ?";
	private final static String FIND_BY_APELLIDOHISTORIAL = "Select * from paciente where apellido like ? ||'%' and id_historial= ?";
	private final static String FIND_BY__APELLIDOSANITARIA = "Select * from paciente where apellido like ? ||'%' and id_sanitaria= ?";
	private final static String FIND_BY_SANITARIAHISTORIAL = "Select * from paciente where id_historial=?  and id_sanitaria=?";
	private final static String FIND_BY_NAH = "Select * from paciente where nombre like ? || '%' and apellido like ? || '%'  and id_historial = ?";
	private final static String FIND_BY_NAS = "Select * from paciente where nombre like ? || '%' and apellido like ? || '%'  and id_sanitaria=?";
	private final static String FIND_BY_HAS = "Select * from paciente where id_historial= ? and apellido like ? || '%'  and id_sanitaria=?";
	private final static String FIND_BY_HNS = "Select *from paciente where id_historial=? and nombre like ? || '%'  and id_sanitaria=?";
	private final static String FIND_BY_NAHS = "Select * from paciente where nombre like ? || '%' and apellido like ? || '%'  and id_historial=? and id_sanitaria=?";
	private final static String FIND_ALL = "Select * from paciente";
	private final static String FIND_FECHA_CITA = "Select horaentradacita,horasalidacita from cita,citasymedicos where id_paciente=? and fecha=?";
	private final static String FIND_FECHA = "Select * from cita,citasymedicos where id_paciente=? and fecha=?";
	private final static String FIND_HISTORIAL_BY_IDPACIENTE = "select id_historial from paciente where id_paciente=?";
	private final static String FIND_PACIENTE_BY_ID = "select * from paciente where id_paciente=?";

	

	@Override
	public String getNombreYApellidosFromId(String id) {

		Connection conn = null;
		PreparedStatement ps = null;
		String toRet = "";

		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID);

			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				toRet += rs.getString("nombre");
				toRet += " ";
				toRet += rs.getString("APELLIDO");
			}
			

			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return toRet;
	}

	@Override
	public void add(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub

	}


	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PacienteDTO> findByLetterName(String name) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_LETTER_NAME);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByLetterApellido(String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_LETTER_APELLIDOS);
			pst.setString(1, apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNHistorial(String nhistorial) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NHISTORIAL);
			pst.setString(1, nhistorial);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByIDSanitaria(String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_IDSANITARIA);
			pst.setString(1, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByLetterNombreApellido(String nombre, String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NOMBREAPELLIDO);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre2 = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre2;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNombreHistorial(String nombre, String nhistorial) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NOMBREHISTORIAL);
			pst.setString(1, nombre);
			pst.setString(2, nhistorial);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre2 = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre2;
				paciente.apellido = apellido;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNombreSanitaria(String nombre, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NOMBRESANITARIA);
			pst.setString(1, nombre);
			pst.setString(2, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre2 = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre2;
				paciente.apellido = apellido;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByApellidoHistorial(String apellido, String nhistorial) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_APELLIDOHISTORIAL);
			pst.setString(1, apellido);
			pst.setString(2, nhistorial);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial2 = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial2;
				paciente.id_sanitaria = id_sanitaria;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByApellidoSanitaria(String apellido, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY__APELLIDOSANITARIA);
			pst.setString(1, apellido);
			pst.setString(2, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido2 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido2;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByHisotialSanitaria(String historial, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_SANITARIAHISTORIAL);
			pst.setString(1, historial);
			pst.setString(2, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre;
				paciente.apellido = apellido;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNombreApellidoHsitorial(String nombre, String apellido, String historial) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NAH);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, historial);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNombreApellidoSanitaria(String nombre, String apellido, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NAS);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByHistorialApellidoSanitaria(String historial, String apellido, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_HAS);
			pst.setString(1, historial);
			pst.setString(2, apellido);
			pst.setString(3, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByHistorialNombreSanitaria(String historial, String nombre, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_HNS);
			pst.setString(1, historial);
			pst.setString(2, nombre);
			pst.setString(3, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findAllPacientes() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(FIND_ALL);
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<PacienteDTO> findByNAHS(String nombre, String apellido, String historial, String sanitaria) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDTO> toRet = new ArrayList<PacienteDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NAHS);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, historial);
			pst.setString(4, sanitaria);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_paciente");
				String nombre1 = rs.getString("nombre");
				String apellido1 = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria2 = rs.getString("id_sanitaria");
				PacienteDTO paciente = new PacienteDTO();
				paciente.id = id;
				paciente.nombre = nombre1;
				paciente.apellido = apellido1;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria2;
				toRet.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<String> findPacienteConCitasEnEsaFecha(String id_paciente, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_CITA);
			ps.setString(1, id_paciente);
			// Formatear.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2, "" + formatter.format(fecha));
			rs = ps.executeQuery();
			while (rs.next()) {
				String hora = rs.getString("horaentradacita");
				String horasalida = rs.getString("horasalidacita");
				String t = hora + "," + horasalida;
				toRet.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;

	}

	public String getIdHistorial(String id_paciente) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_HISTORIAL_BY_IDPACIENTE);
			ps.setString(1, id_paciente);

			rs = ps.executeQuery();
			while (rs.next()) {
				toRet = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;

	}

	@Override
	public List findById(String id) {
		return null;
	}
	
	@Override
	public PacienteDTO findbyIdPaciente(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PacienteDTO paciente = new PacienteDTO();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_PACIENTE_BY_ID);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String idp = rs.getString("id_paciente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String ciudad = rs.getString("ciudad");
				String id_contacto = rs.getString("id_contacto");
				String id_historial = rs.getString("id_historial");
				String id_sanitaria = rs.getString("id_sanitaria");
				paciente.id = idp;
				paciente.nombre = nombre;
				paciente.apellido = apellido;
				paciente.ciudad = ciudad;
				paciente.id_contacto = id_contacto;
				paciente.id_historial = id_historial;
				paciente.id_sanitaria = id_sanitaria;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return paciente;
	}

	@Override
	public boolean findFechaCita(String id_paciente, LocalDate fechaDate) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		 boolean result=false;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA);
			ps.setString(1, id_paciente);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2, "" + formatter.format(fechaDate));
			rs = ps.executeQuery();
			if(rs.next()) {
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return result;
	}


}
