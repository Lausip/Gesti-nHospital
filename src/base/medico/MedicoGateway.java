package base.medico;

import java.time.LocalDate;
import java.util.List;

import base.Gateway;

public interface MedicoGateway<T> extends Gateway<T> {

	public List<MedicoDTO> findByLetterName(String name);

	public List<MedicoDTO> findByLetterApellido(String apellido);

	public List<MedicoDTO> findByLetterNameApellidos(String nombre, String apellidos);

	public List<MedicoDTO> findAllMedicos();

	public List<MedicoDTO> findMedicosSinCitas();

	public List<MedicoDTO> findMedicosConCitasEnEsaFecha(LocalDate fechaLocalDate);

	public List<String> findCitasMedicoEnEsaFecha(String id_Medico, LocalDate fechaLocalDate);

	public List<MedicoDTO> findMedicosEspecialidad(String especialidad);

	public List<MedicoDTO> findMedicosNombreApellidoEspecialidad(String nombre, String especialidad, String apellido);

	public List<MedicoDTO> findMedicosEspecialidadApellido(String especialidad, String apellidos);

	public List<MedicoDTO> findMedicosNombreEspecialidad(String nombre, String especialidad);

	public boolean findMedicosVacaciones(String id_Medico, LocalDate fechaLocalDate);

	public String findMedicosLabInicoFin(String id_Medico, LocalDate fechaLocalDate);

	public String findMedicosLabInico(String id_Medico, LocalDate fechaLocalDate);

	public String findMedicosLabFin(String id_Medico, LocalDate fechaLocalDate);

	List<String> findVacacionesAsignadasByMedicoId(String id_Medico);

	List<String[]> findTrabajoAsignadoByMedicoId(String id_medico);
	
	void addJornadaVac(String id_jornada,String fecha, String idmedico);

	public String getLastIdJornadaVac();

	public void removeJornadaVac(String id_medico, String fecha);


	public List<String> findMedicosDiaLaboral(String id_Medico);

	public String getLastIdJornadaLab();

	public void addJornadaLab(String id_nuevajor, String string, String string2, String id_medico);

	public void removeJornadaLab(String id_medico, String string, String string2);


}
