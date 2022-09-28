package negocio.consulta.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import base.causas.CausasGateway;
import base.causas.impl.CausasGatewayImpl;
import base.cita.CitaDTO;
import base.cita.CitaGateway;
import base.cita.impl.CitaGatewayImpl;
import base.enfermero.EnfermeroDTO;
import base.enfermero.EnfermeroGateway;
import base.enfermero.impl.EnfermeroGatewayImpl;
import base.especialista.EspecialistaDTO;
import base.especialista.EspecialistaGateway;
import base.especialista.impl.EspecialistaGatewayImpl;
import base.factoria.ConsultasFactory;
import base.historial.impl.HistorialGatewayImpl;
import base.medico.MedicoDTO;
import base.medico.MedicoGateway;
import base.medico.impl.MedicoGatewayImpl;
import base.paciente.PacienteDTO;
import base.paciente.PacienteGateway;
import base.paciente.impl.PacienteGatewayImpl;
import base.prescrp.PrescripcionGateway;
import base.prescrp.impl.PrescripcionGatewayImpl;
import base.ubicacion.UbicacionDTO;
import base.ubicacion.UbicacionGateway;
import base.util.CitaTranscryber;
import base.util.EnfermeroTranscryber;
import base.util.EspecialistaTranscryber;
import base.util.MedicoTranscryber;
import base.util.PacienteTranscryber;
import base.util.UbicacionTranscryber;
import base.vacuna.VacunaGateway;
import negocio.citas.Cita;
import negocio.citas.Ubicacion;
import negocio.consulta.DataBaseConsultor;
import negocio.equipo.Especialista;
import negocio.paciente.Paciente;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Enfermero;
import negocio.sanitario.Medico;
import negocio.sanitario.Sanitario;

public class DatabaseConsultorImpl implements DataBaseConsultor {

	@Override
	public List<Cita> getCitasFromCalendario(Medico med, LocalDate loc) {

		CitaGateway<CitaDTO> cg = ConsultasFactory.createCitaGateway();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		List<CitaDTO> citas = cg.findByIdMedico(med.getId_Medico());

		List<Cita> cits = CitaTranscryber.forfromCitaDTOToCita(cg.findAllByIdAndDate(citas, formatter.format(loc)));

		return cits;
	}


	@Override
	public String getNombreYApellidosFromPaciente(String str) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return pg.getNombreYApellidosFromId(str);
	}

	public static List<Medico> buscarNombreMedico(String nombre) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findByLetterName(nombre));
	}

	public static List<Medico> buscarNombreApellidosMedico(String nombre, String apellidos) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findByLetterNameApellidos(nombre, apellidos));

	}

	public static List<Medico> buscarApellidosMedico(String apellidos) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findByLetterApellido(apellidos));

	}

	public static List<Paciente> buscarNombrePaciente(String nombre) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByLetterName(nombre));
	}

	public static List<Paciente> buscarApellidosPaciente(String apellido) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByLetterApellido(apellido));
	}

	public static List<Paciente> buscarNHistorialPaciente(String nhistorial) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByNHistorial(nhistorial));

	}

	public static List<Paciente> buscarSanitariaPaciente(String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByIDSanitaria(sanitaria));
	}

	public static List<Paciente> buscarNombreApellidoPaciente(String nombre, String apellido) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByLetterNombreApellido(nombre, apellido));
	}

	public static List<Paciente> buscarNombreHistorialPaciente(String nombre, String nhistorial) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByNombreHistorial(nombre, nhistorial));

	}

	public static List<Paciente> buscarNombreSanitariaPaciente(String nombre, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByNombreSanitaria(nombre, sanitaria));
	}

	public static List<Paciente> buscarApellidoHistorialPaciente(String apellido, String nhistorial) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByApellidoHistorial(apellido, nhistorial));
	}

	public static List<Paciente> buscarApellidoSanitariaPaciente(String apellido, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByApellidoSanitaria(apellido, sanitaria));
	}

	public static List<Paciente> buscarHistorialSanitariaPaciente(String historial, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByHisotialSanitaria(historial, sanitaria));
	}

	public static List<Paciente> buscarNAHPaciente(String nombre, String apellido, String historial) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber
				.forfromPacienteDTOtoPaciente(pg.findByNombreApellidoHsitorial(nombre, apellido, historial));
	}

	public static List<Paciente> buscarNASPaciente(String nombre, String apellido, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber
				.forfromPacienteDTOtoPaciente(pg.findByNombreApellidoSanitaria(nombre, apellido, sanitaria));
	}

	public static List<Paciente> buscarHASPaciente(String historial, String apellido, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber
				.forfromPacienteDTOtoPaciente(pg.findByHistorialApellidoSanitaria(historial, apellido, sanitaria));
	}

	public static List<Paciente> buscarHNSPaciente(String historial, String nombre, String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber
				.forfromPacienteDTOtoPaciente(pg.findByHistorialNombreSanitaria(historial, nombre, sanitaria));
	}

	public static List<Paciente> buscarTodoPaciente(String nombre, String apellido, String historial,
			String sanitaria) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findByNAHS(nombre, apellido, historial, sanitaria));
	}

	public static List<Medico> buscarMedicosSinCitas() {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findMedicosSinCitas());
	}

	public static List<Medico> buscarMedicosConCitasEnEseHorario(LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findMedicosConCitasEnEsaFecha(fechaLocalDate));
	}

	public static List<Medico> todoslosMedicos() {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findAllMedicos());

	}

	public static List<Enfermero> todoslosEnfermeros() {
		EnfermeroGateway<EnfermeroDTO> em = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(em.findAll());
	}

	public static List<Enfermero> TodoslosEnfermeros() {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(eg.findTodos());
	}

	public static List<Paciente> todoslosPacientes() {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return PacienteTranscryber.forfromPacienteDTOtoPaciente(pg.findAllPacientes());
	}

	public static List<Ubicacion> buscarTodasUbicaiones() {
		UbicacionGateway<UbicacionDTO> ug = ConsultasFactory.creaUbicacionGateway();
		return UbicacionTranscryber.forfromUbicacionDTOToUbicacion(ug.findAllUbicaciones());
	}

	public static List<String> buscarCitasEnEsaUbicacion(String id_ubicacion, LocalDate fecha) {
		UbicacionGateway<UbicacionDTO> ug = ConsultasFactory.creaUbicacionGateway();
		return ug.findCitas(id_ubicacion, fecha);
	}

	public static List<String> validarMedicoNoCita(String id_Medico, LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findCitasMedicoEnEsaFecha(id_Medico, fechaLocalDate);
	}

	public static List<String> buscarCitasEnEsaCita(String id_paciente, LocalDate fechaLocalDate) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return pg.findPacienteConCitasEnEsaFecha(id_paciente, fechaLocalDate);
	}

	public static List<Medico> buscarEspecialidadMedico(String especialidad) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findMedicosEspecialidad(especialidad));
	}

	public static List<Medico> buscarNombreEspecialidadApellidosMedico(String nombre, String especialidad,
			String apellido) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber
				.forfromMedicoDTOtoMedico(mg.findMedicosNombreApellidoEspecialidad(nombre, especialidad, apellido));
	}

	public static List<Medico> buscarEspecialidadApellidosMedico(String especialidad, String apellidos) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findMedicosEspecialidadApellido(especialidad, apellidos));
	}

	public static List<Medico> buscarNombreEspecialidadMedico(String nombre, String especialidad) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return MedicoTranscryber.forfromMedicoDTOtoMedico(mg.findMedicosNombreEspecialidad(nombre, especialidad));
	}

	public static boolean validarMedicoNoVacaciones(String id_Medico, LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findMedicosVacaciones(id_Medico, fechaLocalDate);
	}

	public static String validarMedicoLaboralInicioFinIgual(String id_Medico, LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findMedicosLabInicoFin(id_Medico, fechaLocalDate);
	}

	public static String validarMedicoLaboralInicioIgual(String id_Medico, LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findMedicosLabInico(id_Medico, fechaLocalDate);
	}

	public static String validarMedicoLaboralFinIgual(String id_Medico, LocalDate fechaLocalDate) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findMedicosLabFin(id_Medico, fechaLocalDate);
	}


	public static void anadirCita(Cita c) {

		CitaGateway<CitaDTO> mg = ConsultasFactory.createCitaGateway();
		mg.anadirCita(c);

	}

	@Override
	public void actualizarHorasCita(Cita cit) {
		CitaGatewayImpl<Cita> cgi = new CitaGatewayImpl<>();
		cgi.UpdateHoraEntradas(CitaTranscryber.fromCitaToCitaDTO(cit));

	}

	@Override
	public void actualizarAcudirCita(boolean rdbtnHaAcudido, String id) {
		CitaGatewayImpl cgi = new CitaGatewayImpl();
		cgi.updateAcudirCita(rdbtnHaAcudido, id);
	}

	public static void actualizarPrescripciones(Cita cita, List<String> prescripciones, List<String> borrar) {
		CitaGatewayImpl cgi = new CitaGatewayImpl();
		PrescripcionGateway pr = new PrescripcionGatewayImpl();
		List<String> todas = pr.findPrescripcionesByCita(cita.id_cita);
		for (String p : borrar) {
			String id_p = pr.getIdByInfo(p);
			cgi.removePrescripcion(cita.id_cita, id_p);
		}
		for (String p : prescripciones) {
			String id_p = pr.getIdByInfo(p);
			if (!todas.contains(id_p)) {
				cgi.updatePrescripciones(cita.id_cita, id_p);
			}

		}

	}

	public static void actualizarMedicamentos(Cita cita, List<String[]> medicamentosAñadidos, List<String[]> borrar) {
		CitaGatewayImpl cgi = new CitaGatewayImpl();
		PrescripcionGateway pr = new PrescripcionGatewayImpl();
		List<String[]> todas = pr.findMedicamentosByCita(cita.id_cita);
		for (String[] p : borrar) {
			String id_p = pr.getIdMEDICAMENTOByInfo(p[0]);
			cgi.removeMedicamento(cita.id_cita, id_p, p[1], p[2], p[3], p[4]);
		}
		for (String[] p : medicamentosAñadidos) {
			String id_p = pr.getIdMEDICAMENTOByInfo(p[0]);
			cgi.updateMedicamentos(cita.id_cita, id_p, p[1], p[2], p[3], p[4]);
		}
	}
	
	public static void updateCausas(Cita cita, List<String> causasAñadidas, List<String> causasBorrar) {
		CausasGateway c= new CausasGatewayImpl();
		CitaGatewayImpl cgi = new CitaGatewayImpl();
		for (String ca:causasBorrar) {
			String id = c.findIdByNombre(ca);
			cgi.removeCausa(cita.id_cita, id);
		}
		for (String ca:causasAñadidas) {
			String id = c.findIdByNombre(ca);
			cgi.updateCausasCita(id, cita.id_cita);
		}
		
	}

	@Override
	public void actualizarCausasCita(String causas, String id_cita, LocalDate fecha, String horaCita,
			String id_historial) {
		CitaGatewayImpl c = new CitaGatewayImpl();
		c.updateCausasCita(causas, id_cita);
		HistorialGatewayImpl g = new HistorialGatewayImpl();
		g.update(id_historial, fecha, horaCita);
	}

	public static List<Enfermero> buscarNombreEspecialidadApellidosEnfermero(String nombre, String especialidad,
			String apellido) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(
				eg.findEnfermerosNombreApellidoEspecialidad(nombre, especialidad, apellido));
	}

	public static List<Enfermero> buscarEspecialidadEnfemero(String especialidad) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(eg.findEnfermerosEspecialidad(especialidad));
	}

	public static List<Enfermero> buscarApellidosEnfermero(String apellido) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(eg.findEnfermerosApellido(apellido));
	}

	public static List<Enfermero> buscarNombreEnfermero(String nombre) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(eg.findEnfermerosNombre(nombre));
	}

	public static List<Enfermero> buscarNombreEspecialidadEnfermero(String nombre, String especialidad) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber
				.forfromEnfermeroDTOtoEnfermero(eg.findEnfermerosNombreEspecialidad(nombre, especialidad));
	}

	public static List<Enfermero> buscarNombreApellidosEnfermero(String nombre, String apellido) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber.forfromEnfermeroDTOtoEnfermero(eg.findNombreApellidosEnfermero(nombre, apellido));
	}

	public static List<Enfermero> buscarEspecialidadApellidosEnfermero(String nombre, String apellido) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return EnfermeroTranscryber
				.forfromEnfermeroDTOtoEnfermero(eg.findEspecialidadApellidosEnfermero(nombre, apellido));
	}

	public static boolean validarEnfermeroNoVacaciones(String id_Enfermero, LocalDate fechaLocalDate) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return eg.findEnfermeroVacaciones(id_Enfermero, fechaLocalDate);
	}

	public static String validarEnferermeroLaboralInicioFinIgual(String id_Enfermero, LocalDate fechaLocalDate) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return eg.findEnfermeroLabInicoFin(id_Enfermero, fechaLocalDate);
	}

	public static String validarEnfermeroLaboralInicioIgual(String id_Enfermero, LocalDate fechaLocalDate) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return eg.findEnfermeroLabInicoInicioIgual(id_Enfermero, fechaLocalDate);
	}

	public static String validarEnfermeroLaboralFinIgual(String id_Enfermero, LocalDate fechaLocalDate) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return eg.findEnfermeroLabFin(id_Enfermero, fechaLocalDate);
	}

	public static List<String> validarEnfermeroNoCita(String id_Enfermero, LocalDate fechaLocalDate) {
		EnfermeroGateway<EnfermeroDTO> eg = ConsultasFactory.createEnfermeroConsultas();
		return eg.findEnfermeroCitaValidar(id_Enfermero, fechaLocalDate);
	}

	@Override
	public String getId_ContactoFromId_Paciente(String id_paciente) {
		PacienteGateway p = new PacienteGatewayImpl<>();
		List<Paciente> paciente = p.findById(id_paciente);
		if (paciente.get(0) != null)
			return paciente.get(0).getId_contacto();
		return null;
	}

	@Override
	public String getIdHistorialPaciente(String id_paciente) {
		PacienteGatewayImpl p = new PacienteGatewayImpl();
		return p.getIdHistorial(id_paciente);
	}

	public static List<String> diasVacacionesMedico(Sanitario sanitario) {
		MedicoGateway medicoGateway = new MedicoGatewayImpl<>();
		return medicoGateway.findVacacionesAsignadasByMedicoId(sanitario.getId());

	}

	public static List<String> diasVacacionesEnfermero(Sanitario sanitario) {
		EnfermeroGateway enfermeroGateway = new EnfermeroGatewayImpl<>();
		return enfermeroGateway.findVacacionesAsignadasByEnfermeroId(sanitario.getId());

	}

	public static List<String[]> diasTrabajoMedico(Sanitario sanitario) {
		MedicoGateway medicoGateway = new MedicoGatewayImpl<>();
		return medicoGateway.findTrabajoAsignadoByMedicoId(sanitario.getId());

	}

	public static List<String[]> diasTrabajoEnfermero(Sanitario sanitario) {
		EnfermeroGateway enfermeroGateway = new EnfermeroGatewayImpl<>();
		return enfermeroGateway.findTrabajoAsignadoByEnfermeroId(sanitario.getId());

	}

	public static void updateJornadaVacMedico(List<String> fechas, String id_medico, List<String> paraBorrar) {
		MedicoGateway medicoGateway = new MedicoGatewayImpl<>();
		for (String fecha : fechas) {
			String id_jor = medicoGateway.getLastIdJornadaVac();
			String[] parts = id_jor.split("_");
			int nuevoid = Integer.parseInt(parts[1]) + 1;
			String id_nuevajor = "jornadavac_" + nuevoid;
			medicoGateway.addJornadaVac(id_nuevajor, fecha, id_medico);
		}
		for (String fecha : paraBorrar) {
			medicoGateway.removeJornadaVac(id_medico, fecha);
		}

	}

	public static void updateJornadaLabMedico(List<String[]> nuevasJornadas, String id_medico,
			List<String[]> paraBorrar) {
		MedicoGateway medicoGateway = new MedicoGatewayImpl<>();
		for (String[] fechas : nuevasJornadas) {
			String id_jor = medicoGateway.getLastIdJornadaLab();
			String[] parts = id_jor.split("_");
			int nuevoid = Integer.parseInt(parts[1]) + 1;
			String id_nuevajor = "jornadalab_" + nuevoid;
			medicoGateway.addJornadaLab(id_nuevajor, fechas[0], fechas[1], id_medico);
		}
		for (String[] fecha : paraBorrar) {
			medicoGateway.removeJornadaLab(id_medico, fecha[0], fecha[1]);
		}

	}

	public static void updateJornadaVacEnfermero(List<String> fechas, String id_enfermero, List<String> paraBorrar) {
		EnfermeroGateway enf = new EnfermeroGatewayImpl();

		for (String fecha : fechas) {
			String id_jor = enf.getLastIdJornadaVac();
			String[] parts = id_jor.split("_");
			int nuevoid = Integer.parseInt(parts[1]) + 1;
			String id_nuevajor = "jornadavac_" + nuevoid;
			enf.addJornadaVac(id_nuevajor, fecha, id_enfermero);
		}
		for (String fecha : paraBorrar) {
			enf.removeJornadaVac(id_enfermero, fecha);
		}
	}

	public static void updateJornadaLabEnfermero(List<String[]> nuevasJornadas, String id_enfermero,
			List<String[]> paraBorrar) {
		EnfermeroGateway enf = new EnfermeroGatewayImpl();

		for (String[] fecha : nuevasJornadas) {
			String id_jor = enf.getLastIdJornadaLab();
			String[] parts = id_jor.split("_");
			int nuevoid = Integer.parseInt(parts[1]) + 1;
			String id_nuevajor = "jornadavac_" + nuevoid;
			enf.addJornadaLab(id_nuevajor, fecha[0], fecha[1], id_enfermero);
		}
		for (String[] fecha : paraBorrar) {
			enf.removeJornadaLab(id_enfermero, fecha[0], fecha[1]);
		}

	}

	public static Paciente getPacienteById(String id) {
		PacienteGatewayImpl pg = new PacienteGatewayImpl<>();
		return PacienteTranscryber.fromPacienteDTOToPaciente(pg.findbyIdPaciente(id));
	}

	public static void anadirEnfermeroAlaCita(String id_cita, Enfermero e) {
		CitaGatewayImpl c = new CitaGatewayImpl();
		c.anadirEnfermeroAlaCita(id_cita,e);


	}

	public static void anadirContacto(String id_contacto, String text) {
		CitaGatewayImpl c= new CitaGatewayImpl();
		  c.anadirContacto(id_contacto,text);}
	

	public static void anadirMedicoAlaCita(String id_cita, Medico m) {
		CitaGatewayImpl c = new CitaGatewayImpl();
	    c.anadirMedicoAlaCita(id_cita,m);


	}



	public static List<String> todoslasVacunas(String id_historial) {
	HistorialGatewayImpl h = new HistorialGatewayImpl();
	return h.buscarVacunasDeUnHistorial(id_historial);
	
	}
	public static List<Especialista> todoslosEspecialistas() {
		EspecialistaGateway<EspecialistaDTO> eg= new EspecialistaGatewayImpl<EspecialistaDTO>();
		return EspecialistaTranscryber.forfromEspecialistaDTOtoEspecialista(eg.buscarEspecialistas());
	}
	
	public static void añadirContacto(String id_contacto, String text) {
		CitaGatewayImpl c = new CitaGatewayImpl();
		c.anadirContacto(id_contacto, text);



	}

	@Override
	public List<Vacuna> findAllVacunasByDAte(LocalDate date, Medico medico) {
		VacunaGateway vaGate = ConsultasFactory.forVacunaGateway();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		List<Vacuna> vacunas = vaGate.findByDateAndMed(medico, formatter.format(date));

		return vacunas;
	}

	public static List<Especialista> buscarEspecialistasPorNombre(String nombre) {
		EspecialistaGateway<EspecialistaDTO> eg = new EspecialistaGatewayImpl<EspecialistaDTO>();
		return EspecialistaTranscryber.forfromEspecialistaDTOtoEspecialista(eg.buscarEspecialistasByName(nombre));
	}



	@Override
	public List<Cita> findNotAproved() {
		CitaGateway<CitaDTO> ciGate = ConsultasFactory.createCitaGateway();

		List<Cita> cits = CitaTranscryber.forfromCitaDTOToCita(ciGate.findAllUnapproved());
		
		return cits;
	}

	@Override
	public void actualizarUrgenteCita(Cita cita) {
		CitaGateway<CitaDTO> citaGate= ConsultasFactory.createCitaGateway();
		
		citaGate.updateUrgenciaCita(cita.id_cita,cita.urgente);
		
	}

	@Override
	public void actualizarCitaAceptada(String id_cita) {
		CitaGateway<CitaDTO> citagateway= ConsultasFactory.createCitaGateway();
		
		citagateway.setAcepted(id_cita);
		
	}

	@Override
	public void actualizarHorasCitaIni(Cita cit) {
		CitaGateway<CitaDTO> cgi = ConsultasFactory.createCitaGateway();
		cgi.UpdateHoraEntradasCita(CitaTranscryber.fromCitaToCitaDTO(cit));
		

	}




	public static List<String> todoslasEnfermedades(String id_historial) {
		HistorialGatewayImpl h = new HistorialGatewayImpl();
		return h.buscarEnfermedadesDeUnHistorial(id_historial);
	}


	public static List<Cita> todoslasCitasPaciente(String id_paciente) {
		CitaGatewayImpl<CitaDTO> c= new CitaGatewayImpl<>();
		return  CitaTranscryber.forfromCitaDTOToCita(c.todoslasCitasPaciente(id_paciente));
		}


	public static Ubicacion ubicacionId(String id_Ubicacion) {
		UbicacionGateway<UbicacionDTO> ug = ConsultasFactory.creaUbicacionGateway();
		return UbicacionTranscryber.fromUbicacionDTOToUbicacion(ug.findUbicacionID(id_Ubicacion));
	}


	public static List<String> buscarEnfermedad(String text, String historial) {
		HistorialGatewayImpl h = new HistorialGatewayImpl();
		return h.buscarEnfermedadesDeUnHistorialByName(text,historial);
	}


	public static List<String> buscarVacuna(String id_historial, String text) {
		HistorialGatewayImpl h = new HistorialGatewayImpl();
		return h.buscarVacunasDeUnHistorialByName(id_historial,text);
	}


	public static List<String> validarMedicoLaboralDia(String id_Medico) {
		MedicoGateway<MedicoDTO> mg = ConsultasFactory.createMedicoConsultas();
		return mg.findMedicosDiaLaboral(id_Medico);
	}


	public static List<String> validarEnfermeroLaboralDia(String id_Enfermero) {
		EnfermeroGateway<EnfermeroDTO> mg = ConsultasFactory.createEnfermeroConsultas();
		return mg.findEnfermeroDiaLaboral(id_Enfermero);
	}


	public static boolean buscarCitasEnEsaCitaBoolean(String id_paciente, LocalDate fechaDate) {
		PacienteGateway<PacienteDTO> pg = ConsultasFactory.creaPacienteGateway();
		return pg.findFechaCita(id_paciente,fechaDate);
	}


	public static String[] findPrescripciones() {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		List<String> pre = p.findAll();
		String[] res = new String[pre.size()];
		for (int i = 0; i < pre.size(); i++) {
			res[i] = pre.get(i);
		}
		return res;

	}

	public static String[] findMedicamentos() {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		List<String> pre = p.findMedicamento();
		String[] res = new String[pre.size()];
		for (int i = 0; i < pre.size(); i++) {
			res[i] = pre.get(i);
		}
		return res;
	}

	public static void guardarNuevaPrescripcion(String nueva) {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		String id_p = p.getLastId();
		String[] parts = id_p.split("_");
		int nuevoid = Integer.parseInt(parts[1]) + 1;
		String id = "prescripcion_" + nuevoid;
		p.add(id, nueva);

	}

	public static void guardarNuevoMedicamento(String nueva) {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		String id_p = p.getLastIdMedicamento();
		String[] parts = id_p.split("_");
		int nuevoid = Integer.parseInt(parts[1]) + 1;
		String id = "medicamento_" + nuevoid;
		p.addMedicamento(id, nueva);

	}

	public static List<String> cargarPrescripciones(String id_cita) {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		List<String> idspres = p.findPrescripcionesByCita(id_cita);
		List<String> res = new ArrayList<>();
		for (String id : idspres) {
			res.add(p.getInfoById(id));
		}
		return res;
	}

	public static List<String[]> cargarMedicamentos(String id_cita) {
		PrescripcionGateway p = new PrescripcionGatewayImpl();
		List<String[]> idspres = p.findMedicamentosByCita(id_cita);
		List<String[]> res = new ArrayList<>();
		for (String id[] : idspres) {
			String[] r = new String[5];
			r[0] = p.getMEDICAMENTONameById(id[0]);
			r[1] = id[1];
			r[2] = id[2];
			r[3] = id[3];
			r[4] = id[4];
			res.add(r);
		}
		return res;
	}

	public static String[] findCausas() {
		CausasGateway c=new CausasGatewayImpl();
		List<String> all = c.findAll();
		
		String[] ret = new String[all.size()];
		for (int i =0;i<all.size();i++) {
			ret[i]=all.get(i);
		}
		return ret;
	}

	public static List<String> findCausasYaAsignadas(String id_cita) {
		CausasGateway c=new CausasGatewayImpl();
		List<String> idYaAsignado = c.findCausasYaAñadidas(id_cita);
		List<String> nombres = new ArrayList<>();
		for (String id:idYaAsignado) {
			nombres.add(c.findNombreById(id));
		}
		return nombres;
	}

	

}

