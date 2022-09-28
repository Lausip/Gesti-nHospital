package base.factoria;

import java.time.LocalDate;
import java.util.List;

import base.cita.CitaDTO;
import base.cita.CitaGateway;
import base.cita.impl.CitaGatewayImpl;
import base.enfermero.EnfermeroDTO;
import base.enfermero.EnfermeroGateway;
import base.enfermero.impl.EnfermeroGatewayImpl;
import base.medico.MedicoDTO;
import base.medico.MedicoGateway;
import base.medico.impl.MedicoGatewayImpl;
import base.paciente.PacienteDTO;
import base.paciente.PacienteGateway;
import base.paciente.impl.PacienteGatewayImpl;
import base.ubicacion.UbicacionDTO;
import base.ubicacion.UbicacionGateway;
import base.ubicacion.impl.UbicacionGatewayImpl;
import base.vacuna.VacunaGateway;
import base.vacuna.impl.VacunaGatewayImpl;
import negocio.prescripcion.Vacuna;
import negocio.sanitario.Medico;

public class ConsultasFactory {
	
	public static MedicoGateway<MedicoDTO> createMedicoConsultas() {
		return new MedicoGatewayImpl<>();
	}
	
	public static EnfermeroGateway<EnfermeroDTO> createEnfermeroConsultas() {
		return new EnfermeroGatewayImpl<>();
	}
	
	
	public static CitaGateway<CitaDTO> createCitaGateway(){
		return new CitaGatewayImpl<>();
	}
	
	
	public static PacienteGateway<PacienteDTO> creaPacienteGateway() {
		return new PacienteGatewayImpl<>();
	}


	public static UbicacionGateway<UbicacionDTO> creaUbicacionGateway() {
		return new UbicacionGatewayImpl<>();
	}

	public static VacunaGateway forVacunaGateway() {
		return new VacunaGatewayImpl() {
		};
	}

}
