package base.paciente;

import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;

import base.Gateway;
import negocio.paciente.Paciente;

public interface PacienteGateway<T> extends Gateway<T> {
	
	
	public String getNombreYApellidosFromId(String id);
	public List<PacienteDTO> findByLetterName(String name);
	public List<PacienteDTO>  findByLetterApellido(String apellido);
	public List<PacienteDTO> findByNHistorial(String nhistorial);
	public List<PacienteDTO> findByIDSanitaria(String sanitaria);
	public List<PacienteDTO> findByLetterNombreApellido(String nombre, String apellido);
	public List<PacienteDTO> findByNombreHistorial(String nombre, String nhistorial);
	public List<PacienteDTO> findByNombreSanitaria(String nombre, String sanitaria);
	public List<PacienteDTO> findByApellidoHistorial(String apellido, String nhistorial);
	public List<PacienteDTO> findByApellidoSanitaria(String apellido, String sanitaria);
	public List<PacienteDTO> findByHisotialSanitaria(String historial, String sanitaria);
	public List<PacienteDTO> findByNombreApellidoHsitorial(String nombre, String apellido, String historial);
	public List<PacienteDTO> findByNombreApellidoSanitaria(String nombre, String apellido, String sanitaria);
	public List<PacienteDTO> findByHistorialApellidoSanitaria(String historial, String apellido, String sanitaria);
	public List<PacienteDTO> findByHistorialNombreSanitaria(String historial, String nombre, String sanitaria);
	public List<PacienteDTO> findByNAHS(String nombre, String apellido, String historial, String sanitaria);
	public List<PacienteDTO> findAllPacientes();
	public List<String> findPacienteConCitasEnEsaFecha(String id_paciente, LocalDate fechaLocalDate);
	PacienteDTO findbyIdPaciente(String id);
	public boolean findFechaCita(String id_paciente, LocalDate fechaDate);
	

}
