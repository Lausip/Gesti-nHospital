package base.prescrp.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import base.jdbc.Jdbc;
import base.medico.MedicoDTO;
import base.prescrp.PrescripcionGateway;

public class PrescripcionGatewayImpl implements PrescripcionGateway {

	private final static String FIND_ALL = "select * from prescricon";
	private final static String FIND_MED = "select * from medicamento";
	private final static String LAST_ID_PRESCRP = "select max(id_prescripcion) from prescricon";
	private final static String LAST_ID_MED = "select max(id_medicamento) from medicamento";
	private final static String INSERT_PRESCRP = "insert into prescricon values(?,?)";
	private final static String INSERT_MED = "insert into medicamento values(?,?)";

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
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_ALL);
			rs = pst.executeQuery();
			while (rs.next()) {
				toRet.add(rs.getString("info"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public List<String> findMedicamento() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(FIND_MED);
			rs = pst.executeQuery();
			while (rs.next()) {
				toRet.add(rs.getString("nombre"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	@Override
	public String getLastId() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		String toRet = "";
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(LAST_ID_PRESCRP);
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
	public String getLastIdMedicamento() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		String toRet = "";
		try {
			conn = Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery(LAST_ID_MED);
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
	public void add(String id, String nueva) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(INSERT_PRESCRP);

			ps1.setString(1, id);
			ps1.setString(2, nueva);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	@Override
	public void addMedicamento(String id, String nueva) {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps1 = c.prepareStatement(INSERT_MED);

			ps1.setString(1, id);
			ps1.setString(2, nueva);
			ps1.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(c);
		}

	}

	@Override
	public String getIdByInfo(String info) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_prescripcion from prescricon where info=?");
			pst.setString(1, info);

			rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}
		return res;
	}
	
	@Override
	public String getIdMEDICAMENTOByInfo(String string) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_medicamento from medicamento where nombre=?");
			pst.setString(1, string);

			rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}
		return res;
	}

	@Override
	public String getInfoById(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select info from prescricon where id_prescripcion=?");
			pst.setString(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}
		return res;
	}
	
	@Override
	public String getMEDICAMENTONameById(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select nombre from medicamento where id_medicamento=?");
			pst.setString(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}
		return res;
	}


	@Override
	public List<String> findPrescripcionesByCita(String id_cita) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_prescripcion from citayprescripcion where id_cita=?");
			pst.setString(1, id_cita);

			rs = pst.executeQuery();
			while (rs.next()) {
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
	public List<String[]> findMedicamentosByCita(String id_cita) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String[]> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_medicamento, cantidad, intervalo, duracion, otrainfo from citaymedicamento where id_cita=?");
			pst.setString(1, id_cita);

			rs = pst.executeQuery();
			while (rs.next()) {
				String[] res = new String[5];
				res[0]=rs.getString(1);
				res[1]=rs.getString(2);
				res[2]=rs.getString(3);
				res[3]=rs.getString(4);
				res[4]=rs.getString(5);
				lista.add(res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return lista;
	}




}
