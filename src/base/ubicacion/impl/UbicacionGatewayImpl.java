package base.ubicacion.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.cita.CitaDTO;
import base.jdbc.Jdbc;
import base.medico.MedicoDTO;
import base.medico.MedicoGateway;
import base.ubicacion.UbicacionDTO;
import base.ubicacion.UbicacionGateway;
import negocio.citas.Ubicacion;
import negocio.sanitario.Medico;

public class UbicacionGatewayImpl<T> implements UbicacionGateway<T> {
	private final static String ENCONTRAR_TODAS_UBICACIONES = "Select * from ubicacion";
	private static String ENCONTRAR_UBICACION_HORARIO = "Select horaentradacita,horasalidacita from cita where id_ubicacion=? and fecha=?";
	private final static String ENCONTRAR_UBICACION_ID = "Select * from ubicacion where id_ubicacion=?";

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
	public List<UbicacionDTO> findAllUbicaciones() {
		Connection conn = null;
		Statement cs = null;
		ResultSet rs =null;
		List<UbicacionDTO> toRet = new ArrayList<UbicacionDTO>();
		try {
			conn =  Jdbc.getConnection();
			cs = conn.createStatement();
			rs = cs.executeQuery( ENCONTRAR_TODAS_UBICACIONES);
	      while(rs.next()) {
	    	    String id_ubicacion = rs.getString("id_ubicacion");
				String nombreubicacion = rs.getString("nombreubicacion");
				UbicacionDTO u = new UbicacionDTO();
				u.id_ubicacion=id_ubicacion;
				u.nombre=nombreubicacion;

				toRet.add(u);
	        	}
		}catch (SQLException e) {
				e.printStackTrace();
			} finally {
		Jdbc.close(conn);
	}
	return toRet;
	}

	@Override
	public List<String> findCitas(String id_ubicacion,LocalDate fecha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		List<String> toRet = new ArrayList<String>();
		try {
			conn =  Jdbc.getConnection();
			ps = conn.prepareStatement(ENCONTRAR_UBICACION_HORARIO);
			ps.setString(1,id_ubicacion);
			//Formatear.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ps.setString(2,""+formatter.format(fecha));			
			rs = ps.executeQuery();
	      while(rs.next()){
	    	    String hora = rs.getString("horaentradacita");
				String horasalida = rs.getString("horasalidacita");
				String t=hora+","+horasalida;
				toRet.add(t);
	        	}
		}catch (SQLException e) {
				e.printStackTrace();
			} finally {
		Jdbc.close(conn);
	}
	return toRet;
	}

	@Override
	public UbicacionDTO findUbicacionID(String id_Ubicacion) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		UbicacionDTO ubicacion= new UbicacionDTO();
		try {
			conn =  Jdbc.getConnection();
			ps = conn.prepareStatement(ENCONTRAR_UBICACION_ID);
			ps.setString(1,id_Ubicacion);	
			rs = ps.executeQuery();
	      rs.next();
	       ubicacion.id_ubicacion=id_Ubicacion;
	       ubicacion.nombre=rs.getString("nombreubicacion");
		}catch (SQLException e) {
				e.printStackTrace();
			} finally {
		Jdbc.close(conn);
	}
	return ubicacion;
	}



		
}


