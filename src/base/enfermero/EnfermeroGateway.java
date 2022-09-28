package base.enfermero;

import java.time.LocalDate;
import java.util.List;

import base.Gateway;

public interface EnfermeroGateway<T> extends Gateway<T>{

		public List<EnfermeroDTO> findEnfermerosNombreApellidoEspecialidad(String nombre, String especialidad, String apellido);

		public List<EnfermeroDTO> findTodos();

		public List<EnfermeroDTO> findEnfermerosEspecialidad(String especialidad);

		public List<EnfermeroDTO> findEnfermerosApellido(String apellido);

		public List<EnfermeroDTO> findEnfermerosNombre(String nombre);

		public List<EnfermeroDTO> findEnfermerosNombreEspecialidad(String nombre, String especialidad);

		public List<EnfermeroDTO> findNombreApellidosEnfermero(String nombre, String apellido);

		public List<EnfermeroDTO> findEspecialidadApellidosEnfermero(String nombre, String apellido);

		public boolean findEnfermeroVacaciones(String id_Enfermero, LocalDate fechaLocalDate);

		public String findEnfermeroLabInicoFin(String id_Enfermero, LocalDate fechaLocalDate);

		public String findEnfermeroLabInicoInicioIgual(String id_Enfermero, LocalDate fechaLocalDate);

		public String findEnfermeroLabFin(String id_Enfermero, LocalDate fechaLocalDate);

		public List<String> findEnfermeroCitaValidar(String id_Enfermero, LocalDate fechaLocalDate);


		List<String> findVacacionesAsignadasByEnfermeroId(String id);

		void addJornadaVac(String id_jornada, String fecha, String id_enfermero);

		String getLastIdJornadaVac();

		void removeJornadaVac(String id_enfermero, String fecha);


		public List<String> findEnfermeroDiaLaboral(String id_Enfermero);

		public List<String[]> findTrabajoAsignadoByEnfermeroId(String id);

		public String getLastIdJornadaLab();

		public void addJornadaLab(String id_nuevajor, String string, String string2, String id_enfermero);

		public void removeJornadaLab(String id_enfermero, String string, String string2);

}
