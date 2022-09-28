package base.prescrp;

import java.util.List;

import base.Gateway;

public interface PrescripcionGateway extends Gateway {

	List<String> findMedicamento();

	String getLastId();

	void add(String id, String nueva);

	String getLastIdMedicamento();

	void addMedicamento(String id, String nueva);

	String getIdByInfo(String info);

	List<String> findPrescripcionesByCita(String id_cita);

	String getInfoById(String id);

	List<String[]> findMedicamentosByCita(String id_cita);

	String getIdMEDICAMENTOByInfo(String string);

	String getMEDICAMENTONameById(String id);

}
