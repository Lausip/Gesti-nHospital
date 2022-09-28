package base.util;

import java.util.ArrayList;
import java.util.List;

import base.paciente.PacienteDTO;
import negocio.paciente.Paciente;


public class PacienteTranscryber {
	public static Paciente fromPacienteDTOToPaciente(PacienteDTO p) {
		Paciente paciente= new Paciente();
		
	    paciente.setId_paciente(p.id);
	    paciente.setNombre(p.nombre);
	    paciente.setApellido(p.apellido);
	    paciente.setCiudad(p.ciudad);
	    paciente.setId_contacto(p.id_contacto);	
	    paciente.setId_historial(p.id_historial);
	    paciente.setId_sanitario(p.id_sanitaria);
  
		return paciente;
		
		
		
	}
	
	public static List<Paciente> forfromPacienteDTOtoPaciente(List<PacienteDTO> listpacienteDTO){
		List<Paciente> listMedico= new ArrayList<Paciente>();
		for(PacienteDTO paciente:listpacienteDTO) {
			listMedico.add( fromPacienteDTOToPaciente(paciente));
		}
		return listMedico;
		
	}
}
