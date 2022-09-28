package base.causas.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.causas.CausasGateway;
import base.jdbc.Jdbc;

public class CausasGatewayImpl implements CausasGateway {

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
	public List<String> findAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> toRet = new ArrayList<String>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select * from causas");
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
	public List<String> findCausasYaAñadidas(String id_cita) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<>();

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_causa from citaycausas where id_cita=?");
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
	public String findNombreById(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select nombre from causas where id_causa=?");
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
	public String findIdByNombre(String ca) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String res = "";

		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement("select id_causa from causas where nombre=?");
			pst.setString(1, ca);

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

}
