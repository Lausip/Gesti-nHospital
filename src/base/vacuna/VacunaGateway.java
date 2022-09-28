package base.vacuna;

import java.time.LocalDate;
import java.util.List;

import base.Gateway;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Medico;

public interface VacunaGateway extends Gateway<Vacuna> {
	
	List<Vacuna> findByDateAndMed(Medico med, String date);

}
