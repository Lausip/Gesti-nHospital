package base.cita;

import java.util.List;

import base.Gateway;
import negocio.citas.Cita;

public interface CitaGateway<T> extends Gateway<T> {

	
	public List<CitaDTO> findByIdMedico(String id );
	
	public List<CitaDTO> findAllByIdAndDate(List<CitaDTO> citas,String  fecha);
	
	public void UpdateHoraEntradas(CitaDTO cita);

	public void anadirCita(Cita c);
	
	public void updateCausasCita(String causa, String id_cita);

	public List<CitaDTO> findAllUnapproved();

	public void updateUrgenciaCita(String id_cita, boolean urgente);

	public void setAcepted(String id_cita);

	public void UpdateHoraEntradasCita(CitaDTO fromCitaToCitaDTO);
	
	
}
