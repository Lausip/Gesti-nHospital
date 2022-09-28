package base.medico.impl;

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

import base.jdbc.Jdbc;
import base.medico.MedicoDTO;
import base.medico.MedicoGateway;
import negocio.sanitario.Medico;

public class MedicoGatewayImpl<T> implements MedicoGateway<T> {
	private static String ENCONTRAR_NOMBRE_MEDICO = "Select * from medico where nombrec like ? ||'%'";
	private static String ENCONTRAR_APELLIDOS_MEDICO = "Select *from medico where apellido like ?||'%'";
	private static String ENCONTRAR_NOMBRE_APELLIDOS_MEDICO = "Select * from medico where  nombrec like ? ||'%' and apellido like ?||'%'";

	private static String ENCONTAR_TODOS_MEDICOS = "Select * from medico";
	private static String ENCONTAR_MEDICOS_NO_ID_CITA = "Select * from medico ,citasymedicos where medico.id_medico!=citasymedicos.id_medico";
	private static String FIND_MEDICOS_CITAS_EN_ESA_FECHA = "Select * from cita,medico where cita.id_cita=medico.id_medico and fecha=? ";
	private static String ENCONTRAR_NOMBRE_ESPECIALIDAD_MEDICO = "Select * from medico where  nombrec like ? ||'%' and especialidad=?";
	private static String FIND_BY_NEA = "Select * from medico where  nombrec like ? ||'%' and especialidad=? and apellido like ?||'%'";
	private static String FIND_BY_ESPECIALIDAD_APELLIDOS = "Select *from medico where especialidad=? and apellido like ?||'%'";
	
	private final static String FIND_BY_ID = "select * from medico where id_medico=?";
	private final static String FIND_BY_ESPECIALIDAD = "select * from medico where ESPECIALIDAD=?";
	private final static String ENCONTRAR_MEDICO_VACACIONES = "select * from jornadavac where id_medico=? and diavac=?";
	private final static String FIND_FECHA_CITA = "Select horaentradacita,horasalidacita from cita,citasymedicos where cita.id_cita=citasymedicos.id_citas and id_medico=? and fecha=?";
	private final static String FIND_FECHA_LAB_INICIO = "Select fechaInicio from jornadaLab where id_medico=? and fechaInicio like ? ||'%'";
	private final static String FIND_FECHA_LAB_FINAL = "Select fechaFin from jornadaLab where id_medico=? and fechaFin like ? ||'%'";
	private final static String FIND_FECHA_LAB_FINALINICIO = "Select fechaInicio, fechaFin from jornadaLab where id_medico=? and fechaInicio like ?||'%' and fechaFin like? ||'%'";
	private final static String ENCONTRAR_VACACIONES_BYID = "select diavac from jornadavac where id_medico=?";

	private final static String FIND_FECHA_LABORAL = "select fechaInicio,fechafin from jornadalab where id_medico=?";

	private final static String ENCONTRAR_JORNADALAB_BYID = "select fechainicio, fechafin from jornadalab where id_medico=?";

	private final static String ADD_JORNADA_VAC = "insert into jornadavac values(?,?,?)";
	private final static String LAST_ID_JORNADA = "select max(id_jornadavac) from jornadavac";
	private final static String REMOVE_JORNADA_VAC = "delete from jornadavac where id_medico = ? and diavac = ?";
	
	private final static String ADD_JORNADA_LAB = "insert into jornadalab values(?,?,?,?)";
	private final static String LAST_ID_JORNADA_lab = "select max(id_jornadalab) from jornadalab";
	private final static String REMOVE_JORNADA_lab = "delete from jornadalab where id_medico = ? and fechainicio=? and fechafin=?";
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
		return null;
	}

	@Override
	public List<MedicoDTO> findByLetterName(String name) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_MEDICO);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findByLetterApellido(String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_APELLIDOS_MEDICO);
			pst.setString(1, apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findByLetterNameApellidos(String nombre, String apellidos) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_APELLIDOS_MEDICO);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findAllMedicos() {

		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(ENCONTAR_TODOS_MEDICOS);
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findMedicosSinCitas() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(ENCONTAR_MEDICOS_NO_ID_CITA);
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findMedicosConCitasEnEsaFecha(LocalDate fechaLocalDate) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_MEDICOS_CITAS_EN_ESA_FECHA);
			pst.setString(1, fechaLocalDate.toString());
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<String> findCitasMedicoEnEsaFecha(String id_Medico, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_CITA);
			ps.setString(1, id_Medico);
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
	public List<MedicoDTO> findMedicosEspecialidad(String especialidad) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_ESPECIALIDAD);
			pst.setString(1, especialidad);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findMedicosNombreApellidoEspecialidad(String nombre, String especialidad, String apellido) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_NEA);
			pst.setString(1, nombre);
			pst.setString(2, especialidad);
			pst.setString(3,apellido);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findMedicosEspecialidadApellido(String especialidad, String apellidos) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_BY_ESPECIALIDAD_APELLIDOS);
			pst.setString(1, especialidad);
			pst.setString(2, apellidos);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public List<MedicoDTO> findMedicosNombreEspecialidad(String nombre, String especialidad) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDTO> toRet = new ArrayList<MedicoDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_NOMBRE_ESPECIALIDAD_MEDICO);
			pst.setString(1, nombre);
			pst.setString(2, especialidad);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_medico = rs.getString("id_medico");
				String nombrec = rs.getString("nombrec");
				String apellido2 = rs.getString("apellido");
				String especialidad2 = rs.getString("especialidad");
				String email = rs.getString("email");
				MedicoDTO medico = new MedicoDTO();
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
	public boolean findMedicosVacaciones(String id_Medico, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean res=false;

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_MEDICO_VACACIONES);
			pst.setString(1, id_Medico);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			pst.setString(2, "" + formatter.format(fecha));
			rs = pst.executeQuery();
			if(rs.next()) {
				res=true;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return res;
	}
	
	@Override
	public List<String> findVacacionesAsignadasByMedicoId(String id_Medico) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_VACACIONES_BYID);
			pst.setString(1, id_Medico);
			
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
	public List<String[]> findTrabajoAsignadoByMedicoId(String id_medico) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String[]> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_JORNADALAB_BYID);
			pst.setString(1, id_medico);
			
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
	public String findMedicosLabInicoFin(String id_Medico, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_FINALINICIO);
			ps.setString(1, id_Medico);
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
	public String findMedicosLabInico(String id_Medico, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_INICIO);
			ps.setString(1, id_Medico);
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
	public String findMedicosLabFin(String id_Medico, LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LAB_FINAL);
			ps.setString(1, id_Medico);
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
	
	
	public void addJornadaVac(String id_jornada,String fecha, String idmedico) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			
				ps = conn.prepareStatement(ADD_JORNADA_VAC);
				ps.setString(1, id_jornada);
				ps.setString(2, idmedico);
				ps.setString(3, fecha);
				ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}
	
	@Override
	public void addJornadaLab(String id_jornada, String fechainicio, String fechafin, String id_medico) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			
				ps = conn.prepareStatement(ADD_JORNADA_LAB);
				ps.setString(1, id_jornada);
				ps.setString(2, id_medico);
				ps.setString(3, fechainicio);
				ps.setString(4, fechafin);
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
	public void removeJornadaVac(String id_medico, String fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(REMOVE_JORNADA_VAC);
			ps.setString(1, id_medico);
			ps.setString(2, fecha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}

	@Override
	public List<String> findMedicosDiaLaboral(String id_Medico) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String toRet = null;
		List<String> result=new ArrayList<String>();
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(FIND_FECHA_LABORAL);
			ps.setString(1, id_Medico);
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

	public void removeJornadaLab(String id_medico, String fechai, String fechaf) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Jdbc.getConnection();
			ps = conn.prepareStatement(REMOVE_JORNADA_lab);
			ps.setString(1, id_medico);
			ps.setString(2, fechai);
			ps.setString(3, fechaf);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}
		
	}



}
