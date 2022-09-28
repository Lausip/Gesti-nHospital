package base.historial.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.historial.HistorialGateway;
import base.historial.VacunaDTO;
import base.jdbc.Jdbc;
import negocio.prescripcion.Vacuna;

public class HistorialGatewayImpl implements HistorialGateway {

	private static final String ADD_HISTORIAL = "insert into Historial values(?,?,?)";
	private static final String UPDATE_HISTORIAL = "update historial set fecha=?, hora=? where id_historial=?";
	private static final String BUSCARVACUNASBYHISTORIAL = "Select fecha,nombre,id_cita,id_vacuna\n"
			+ "from informe,informeyvacuna,vacuna\n"
			+ "where informe.id_historial=?\n"
			+ "and informe.id_informe=informeyvacuna.id_informe \n"
			+ "and informeyvacuna.id_vacuna=vacuna.id_vacuna\n";
	private static final String BUSCARVACUNASBYHISTORIALBYNAME = "Select fecha,nombre,id_cita,id_vacuna\n"
			+ "from informe,informeyvacuna,vacuna \n"
			+ "where informe.id_historial=?\n"
			+ "and informe.id_informe=informeyvacuna.id_informe \n"
			+ "and informeyvacuna.id_vacuna=vacuna.id_vacuna\n"
			+ "and vacuna.nombre like ?|| '%'";
	private static final String BUSCARENFERMEDADBYHISTORIAL="Select fecha,nombre,id_cita,id_ENFERMEDAD\n"
			+ "from informe,informeyenfermedad,enfermedad\n"
			+ "where informe.id_historial=? \n"
			+ "and informe.id_informe=informeyenfermedad.id_informe \n"
			+ "and informeyenfermedad.id_enfermedad=enfermedad.id_enfermedad";
	private static final String BUSCARENFERMEDADBYHISTORIALBYNAME="Select fecha,nombre,id_cita,id_ENFERMEDAD\n"
			+ "from informe,informeyenfermedad,enfermedad\n"
			+ "where informe.id_historial=? \n"
			+ "and informe.id_informe=informeyenfermedad.id_informe \n"
			+ "and informeyenfermedad.id_enfermedad=enfermedad.id_enfermedad and enfermedad.nombre like ? ||'%'";
	private static final String BUSCARMEDICOCITAVACUNA="Select distinct(nombrec),apellido\r\n"
			+ "from INFORME, citasYmedicos,medico,vacuna,INFORMEYVACUNA\r\n"
			+ "where informe.id_informe=INFORMEYVACUNA.id_informe and INFORMEYVACUNA.id_vacuna=?\r\n"
			+ " and informe.id_historial=? \r\n"
			+ "and informe.id_cita=? and informe.id_cita=citasYmedicos.id_citas and citasYmedicos.id_medico=medico.id_medico ";
	private static final String BUSCARMEDICOCITAENFERMEDAD="Select nombrec,apellido\r\n"
			+ "from INFORME, citasYmedicos,medico,ENFERMEDAD,INFORMEYENFERMEDAD\r\n"
			+ "where informe.id_informe=INFORMEYENFERMEDAD.id_informe and INFORMEYENFERMEDAD.id_ENFERMEDAD=?\r\n"
			+ " and informe.id_historial=?\r\n"
			+ "and informe.id_cita=? and informe.id_cita=citasYmedicos.id_citas and citasYmedicos.id_medico=medico.id_medico";
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
	public List findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWithFechaAndHora(String fecha, String hora) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(ADD_HISTORIAL);
			ps.setString(1, "historial_5");
			ps.setString(2, fecha);
			ps.setString(3, hora);
			rs = ps.executeQuery();
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public void update(String id_historial, LocalDate fecha, String horaCita) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement(UPDATE_HISTORIAL);
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(1, f.format(fecha));
			ps.setString(2, horaCita);
			ps.setString(3, id_historial);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	public List<String> buscarVacunasDeUnHistorial(String id_historial) {
		Connection c = null;
		List<String> vacunas= new ArrayList<>();
		try {
			
			c = Jdbc.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(BUSCARVACUNASBYHISTORIAL);
			ps.setString(1, id_historial);
			rs = ps.executeQuery();
			while(rs.next()) {
                String result= rs.getString("fecha")+","+rs.getString("nombre");
                String id_vacuna=rs.getString("id_vacuna");
                String id_cita=rs.getString("id_cita");
                ResultSet rs2 = null;
    			PreparedStatement ps2 = c.prepareStatement(BUSCARMEDICOCITAVACUNA);
    			ps2.setString(1, id_vacuna);
    			ps2.setString(2, id_historial);
    			ps2.setString(3, id_cita);
    			rs2 = ps2.executeQuery();
    			while(rs2.next()) {
    				result+=","+rs2.getString("nombrec")+" "+rs2.getString("apellido");;
    			}
    			  vacunas.add(result);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		return vacunas;
		
	}

	public List<String> buscarEnfermedadesDeUnHistorial(String id_historial) {
		Connection c = null;
		List<String> enfermedad= new ArrayList<>();
		try {
			
			c = Jdbc.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(BUSCARENFERMEDADBYHISTORIAL);
			ps.setString(1,id_historial);
			rs = ps.executeQuery();
			while(rs.next()) {
                String result= rs.getString("fecha")+","+rs.getString("nombre");
                String id_enfermedad=rs.getString("id_enfermedad");
                String id_cita=rs.getString("id_cita");
                ResultSet rs2 = null;
    			PreparedStatement ps2 = c.prepareStatement(BUSCARMEDICOCITAENFERMEDAD);
    			ps2.setString(1, id_enfermedad);
    			ps2.setString(2, id_historial);
    			ps2.setString(3, id_cita);
    			rs2 = ps2.executeQuery();
    			while(rs2.next()) {
    				result+=","+rs2.getString("nombrec")+" "+rs2.getString("apellido");
    			}
    			 
    			enfermedad.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		return enfermedad;
	}

	public List<String> buscarEnfermedadesDeUnHistorialByName(String historial, String text) {
		Connection c = null;
		List<String> enfermedad= new ArrayList<>();
		try {
			
			c = Jdbc.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(BUSCARENFERMEDADBYHISTORIALBYNAME);
			ps.setString(1,historial);
			ps.setString(2,text);
			rs = ps.executeQuery();
			while(rs.next()) {
                String result= rs.getString("fecha")+","+rs.getString("nombre");
                String id_enfermedad=rs.getString("id_enfermedad");
                String id_cita=rs.getString("id_cita");
                ResultSet rs2 = null;
    			PreparedStatement ps2 = c.prepareStatement(BUSCARMEDICOCITAENFERMEDAD);
    			ps2.setString(1, id_enfermedad);
    			ps2.setString(2, historial);
    			ps2.setString(3, id_cita);
    			rs2 = ps2.executeQuery();
    			while(rs2.next()) {
    				result+=","+rs2.getString("nombrec")+" "+rs2.getString("apellido");
    			}
 
    			enfermedad.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		return enfermedad;
	}

	public List<String> buscarVacunasDeUnHistorialByName(String id_historial, String text) {
		Connection c = null;
		List<String> vacunas= new ArrayList<>();
		try {
			
			c = Jdbc.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(BUSCARVACUNASBYHISTORIALBYNAME);
			ps.setString(1,id_historial);
			ps.setString(2,text);
			rs = ps.executeQuery();
			while(rs.next()) {
                String result= rs.getString("fecha")+","+rs.getString("nombre");
                String id_vacuna=rs.getString("id_vacuna");
                String id_cita=rs.getString("id_cita");
                ResultSet rs2 = null;
    			PreparedStatement ps2 = c.prepareStatement(BUSCARMEDICOCITAVACUNA);
    			ps2.setString(1, id_vacuna);
    			ps2.setString(2, id_historial);
    			ps2.setString(3, id_cita);
    			rs2 = ps2.executeQuery();
    			while(rs2.next()) {
    				result+=","+rs2.getString("nombrec")+" "+rs2.getString("apellido");
    			}
    			  vacunas.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}
		return vacunas;
	}

}
