package base.enfermero.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.enfermero.EnfermeroDTO;
import base.enfermero.EnfermeroGateway;
import base.jdbc.Jdbc;
import base.medico.MedicoDTO;
import base.medico.MedicoGateway;
import negocio.sanitario.Medico;

public class EnfermeroGatewayImpl<T> implements EnfermeroGateway<T> {
	private static String FIND_BY_NEA = "Select * from enfermero where  nombrec like ? ||'%' and especialidad=? and apellido like ?||'%'";
	private static String ENCONTAR_TODOS_ENFERMEROS= "Select * from enfermero";
	private final static String FIND_BY_ESPECIALIDAD = "select * from enfermero where ESPECIALIDAD=?";
	private static String ENCONTRAR_APELLIDOS_ENFERMERO = "Select *from enfermero where apellido like ?||'%'";
	private static String ENCONTRAR_NOMBRE_ENFERMERO = "Select * from enfermero where nombrec like ? ||'%'";
	private static String ENCONTRAR_NOMBRE_ESPECIALIDAD_ENFERMERO = "Select * from enfermero where  nombrec like ? ||'%' and especialidad=?";
	private static String ENCONTRAR_NOMBRE_APELLIDOS_ENFERMERO = "Select * from enfermero where  nombrec like ? ||'%' and apellido like ?||'%'";
	private static String FIND_BY_ESPECIALIDAD_APELLIDOS = "Select *from enfermero where especialidad=? and apellido like ?||'%'";
	private final static String ENCONTRAR_ENFERMERO_VACACIONES = "select * from jornadavacenfermero where id_enfermero=? and diavac=?";
	private final static String FIND_FECHA_LAB_FINALINICIO = "Select fechaInicio, fechaFin from jornadaLabEnfermero where id_enfermero=? and fechaInicio like ?||'%' and fechaFin like? ||'%'";
	private final static String FIND_FECHA_LAB_INICIO = "Select fechaInicio from jornadaLabEnfermero where id_enfermero=? and fechaInicio like ? ||'%'";
	private final static String FIND_FECHA_LAB_FINAL = "Select fechaFin from jornadaLabEnfermero where id_enfermero=? and fechaFin like ? ||'%'";
	private final static String FIND_FECHA_CITA = "Select horaentradacita,horasalidacita from cita,citasyenfermeros where cita.id_cita=citasyenfermeros.id_citas and id_enfermero=? and fecha=?";
	private static final String ENCONTRAR_TODOS_ENFERMEROS = "select * from enfermero";
	private final static String ENCONTRAR_VACACIONES_BYID = "select diavac from jornadavacenfermero where id_enfermero=?";
	private final static String ADD_JORNADA_VAC = "insert into jornadavacenfermero values (?,?,?)";
	private final static String LAST_ID_JORNADA = "select max(id_jornadavac) from jornadavacenfermero";
	private final static String REMOVE_JORNADA_VAC = "delete from jornadavacenfermero where id_enfermero = ? and diavac = ?";
	private final static String FIND_FECHA_LABORAL = "Select fechainicio,fechaFin from jornadaLabEnfermero where id_enfermero=?";
	
	private final static String ENCONTRAR_JORNADALAB_BYID = "select fechainicio, fechafin from jornadalabenfermero where id_enfermero=?";
	
	private final static String LAST_ID_JORNADA_lab = "select max(id_jornadalab) from jornadalabenfermero";
	private final static String ADD_JORNADA_LAB = "insert into jornadalabenfermero values(?,?,?,?)";
	private final static String REMOVE_JORNADA_LAB = "delete from jornadalabenfermero where id_enfermero=? and fechainicio=? and fechafin=?";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnfermeroDTO> findEnfermerosNombreApellidoEspecialidad(String nombre, String especialidad,
			String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NEA);
			pst.setString(1, nombre);
			pst.setString(2, especialidad);
			pst.setString(3,apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad2;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findTodos() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<>();
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(ENCONTAR_TODOS_ENFERMEROS);
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad;
				medico.email = email;
				toRet.add(medico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findEnfermerosEspecialidad(String especialidad) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_ESPECIALIDAD);
			pst.setString(1, especialidad);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido;
				medico.especialidad = especialidad2;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findEnfermerosApellido(String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_APELLIDOS_ENFERMERO);
			pst.setString(1, apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findEnfermerosNombre(String nombre) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_ENFERMERO);
			pst.setString(1, nombre);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido;
				medico.especialidad = especialidad;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findEnfermerosNombreEspecialidad(String nombre, String especialidad) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_ESPECIALIDAD_ENFERMERO);
			pst.setString(1, nombre);
			pst.setString(2, especialidad);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad2;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findNombreApellidosEnfermero(String nombre, String apellidos) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_APELLIDOS_ENFERMERO);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}
		return toRet;
	}

	@Override
	public List<EnfermeroDTO> findEspecialidadApellidosEnfermero(String especialidad, String apellidos) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EnfermeroDTO> toRet = new ArrayList<EnfermeroDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_ESPECIALIDAD_APELLIDOS );
			pst.setString(1, especialidad);
			pst.setString(2, apellidos);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_enfermero");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				EnfermeroDTO medico = new EnfermeroDTO();
				medico.id = id_medico;
				medico.nombre = nombrec;
				medico.apellido = apellido2;
				medico.especialidad = especialidad2;
				medico.email = email;
				toRet.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public boolean findEnfermeroVacaciones(String id_Enfermero, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean res=false;

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_ENFERMERO_VACACIONES);
			pst.setString(1, id_Enfermero);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			pst.setString(2, "" + formatter.format(fecha));
			rs = pst.executeQuery();
			if(rs.next()) {
				res=true;}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return res;
	}

	@Override
	public String findEnfermeroLabInicoFin(String id_Enfermero, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_FINALINICIO);
			ps.setString(1, id_Enfermero);
			// Formatear.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2, "" + formatter.format(fecha));
			ps.setString(3, "" + formatter.format(fecha));
			rs = ps.executeQuery();
			if(rs.next()) {
				String inicio=rs.getString("fechaInicio");
				String fin=rs.getString("fechaFin");
				toRet=inicio+","+fin;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;
	}

	@Override
	public String findEnfermeroLabInicoInicioIgual(String id_Enfermero, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_INICIO);
			ps.setString(1, id_Enfermero);
			// Formatear.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2, "" + formatter.format(fecha));
			rs = ps.executeQuery();
			if(rs.next()) {
				toRet=rs.getString("fechaInicio");
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;
	}

	@Override
	public String findEnfermeroLabFin(String id_Enfermero, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_FINAL);
			ps.setString(1, id_Enfermero);
			// Formatear.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2, "" + formatter.format(fecha));
			rs = ps.executeQuery();
			if(rs.next()) {
				toRet=rs.getString("fechaFin");
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return toRet;
	}

	@Override
	public List<String> findEnfermeroCitaValidar(String id_Enfermero, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_CITA);
			ps.setString(1, id_Enfermero);
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

	@Override
	public List<String> findVacacionesAsignadasByEnfermeroId(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_VACACIONES_BYID);
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				lista.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return lista;
	}
	
	@Override
	public void addJornadaVac(String id_jornada, String fecha, String id_enfermero) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(ADD_JORNADA_VAC);
			ps.setString(1, id_jornada);
			ps.setString(2, id_enfermero);
			ps.setString(3, fecha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}

	@Override
	public String getLastIdJornadaVac() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		String toRet = "";
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(LAST_ID_JORNADA);
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
	public void removeJornadaVac(String id_enfermero, String fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(REMOVE_JORNADA_VAC);
			ps.setString(1, id_enfermero);
			ps.setString(2, fecha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}
	@Override
	public List<String> findEnfermeroDiaLaboral(String id_Enfermero) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		List<String> result=new ArrayList<String>();
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LABORAL);
			ps.setString(1, id_Enfermero);
			rs = ps.executeQuery();
			if(rs.next()) {
				toRet=rs.getString("fechaInicio").substring(0,10)+","+rs.getString("fechafin").substring(0,10);
				result.add(toRet);
				
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		return result;
	}

	@Override
	public List<String[]> findTrabajoAsignadoByEnfermeroId(String id_enf) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String[]> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_JORNADALAB_BYID);
			pst.setString(1, id_enf);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				String[] fechainiciofechafin = new String[2];
				fechainiciofechafin[0] = rs.getString(1);
				fechainiciofechafin[1] = rs.getString(2);
				lista.add(fechainiciofechafin);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return lista;
	}

	@Override
	public String getLastIdJornadaLab() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		String toRet = "";
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(LAST_ID_JORNADA_lab);
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
	public void addJornadaLab(String id_jornada, String fecha1, String fecha2, String id_enfermero) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(ADD_JORNADA_LAB);
			ps.setString(1, id_jornada);
			ps.setString(2, id_enfermero);
			ps.setString(3, fecha1);
			ps.setString(4, fecha2);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}

	@Override
	public void removeJornadaLab(String id_enfermero, String fech1, String fech2) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(REMOVE_JORNADA_LAB);
			ps.setString(1, id_enfermero);
			ps.setString(2, fech1);
			ps.setString(3, fech2);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
		
	}

}
