package negocio.consulta;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JRadioButton;

import negocio.citas.Cita;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;

public interface DataBaseConsultor {
	
	
	public List<Cita> getCitasFromCalendario(Medico med,LocalDate loc);
	
	
	public String getNombreYApellidosFromPaciente(String str);


	public void actualizarHorasCita(Cita cit);


	public void actualizarAcudirCita(boolean rdbtnHaAcudido, String id);


	public void actualizarCausasCita(String causas, String id_cita, LocalDate fecha, String horaCita, String id_paciente);


	public String getIdHistorialPaciente(String id_paciente);


	public String getId_ContactoFromId_Paciente(String id_paciente);


	public List<Vacuna> findAllVacunasByDAte(LocalDate date, Medico medico);


	public List<Cita> findNotAproved();




	void actualizarUrgenteCita(Cita cita);


	public void actualizarCitaAceptada(String id_cita);


	public void actualizarHorasCitaIni(Cita cit);

}
