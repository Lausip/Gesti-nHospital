package base.causas;

import java.util.List;

import base.Gateway;

public interface CausasGateway extends Gateway{

	List<String> findCausasYaA�adidas(String id_cita);

	String findNombreById(String id);

	String findIdByNombre(String ca);

}
