package base.especialista.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.especialista.EspecialistaDTO;
import base.especialista.EspecialistaGateway;
import base.jdbc.Jdbc;

public class EspecialistaGatewayImpl<T> implements EspecialistaGateway<T> {
	private final static String ENCONTRAR_ESPECIALISTAS = "Select id_especialista,nombre from especialista";
	private final static String ENCONTRAR_ESPECIALISTAS_NOMBRE = "Select id_especialista,nombre from especialista where nombre like ? ||'%'";
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
	public List findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EspecialistaDTO> buscarEspecialistas() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EspecialistaDTO> toRet = new ArrayList<EspecialistaDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_ESPECIALISTAS);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_especialista = rs.getString("id_especialista");
				String nombre = rs.getString("nombre");

				EspecialistaDTO especialista= new EspecialistaDTO();
				especialista.id_especialista = id_especialista;
				especialista.nombre = nombre;
				toRet.add(especialista);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
		
	}

	@Override
	public List<EspecialistaDTO> buscarEspecialistasByName(String nombre) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EspecialistaDTO> toRet = new ArrayList<EspecialistaDTO>();
		try {
			conn = Jdbc.getConnection();
			pst = conn.prepareStatement(ENCONTRAR_ESPECIALISTAS_NOMBRE);
			pst.setString(1,nombre);
			rs = pst.executeQuery();
			while (rs.next()) {
				String id_especialista = rs.getString("id_especialista");
				String nombred = rs.getString("nombre");

				EspecialistaDTO especialista= new EspecialistaDTO();
				especialista.id_especialista = id_especialista;
				especialista.nombre = nombred;
				toRet.add(especialista);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			Jdbc.close(conn);
		}

		return toRet;
	}

	

}

