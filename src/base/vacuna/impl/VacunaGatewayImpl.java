package base.vacuna.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import base.jdbc.Jdbc;
import base.util.Formater;
import base.vacuna.VacunaGateway;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Medico;

public abstract class VacunaGatewayImpl implements VacunaGateway{
	
	

	private static final String FINDALL_BY_DATE_MED = "select * from vacuna v where v.ID_VACUNA in("
			+ "select ID_VACUNA from MEDICOYVACUNAS where ID_MEDICO=? and fecha=?) ";

	@Override
	public void add(Vacuna t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Vacuna t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vacuna t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<Vacuna> findByDateAndMed(Medico med, String date) {
		Connection c = null;

		List<Vacuna> vacunas = null;
		try {
			c = Jdbc.getConnection();

			PreparedStatement ps = c.prepareStatement(FINDALL_BY_DATE_MED);

			ps.setString(1, med.getId());
			ps.setString(2, date);
			ResultSet rs = ps.executeQuery();

			vacunas = Formater.forfromResultSettoVacunas(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vacunas;
	}

	@Override
	public List<Vacuna> findAll() {
		return null;
	}

}
