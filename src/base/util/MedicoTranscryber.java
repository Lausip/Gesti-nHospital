package base.util;

import java.util.ArrayList;
import java.util.List;

import base.medico.MedicoDTO;
import negocio.sanitario.Medico;

public class MedicoTranscryber {
	
	public static MedicoDTO fromMedicoToDTO(Medico med) {
		MedicoDTO medic= new MedicoDTO();
		
//		medic.id=med.getId_Medico();
//		medic.nombre=med.getNombre();
//		medic.apellido=med.getApellidos();
//		medic.email=med.getEmail();
		
		return medic;
		
	}
	
	public static Medico fromMedicoDTOToMedic(MedicoDTO med) {
		Medico medic= new Medico();
		
	    medic.setId_Medico(med.id);
	    medic.setNombre(med.nombre);
	    medic.setApellidos(med.apellido);
	    medic.setEmail(med.email);
	    medic.setEspecilidad(med.especialidad);		
	    
		return medic;
		
		
		
	}
	
	public static List<Medico> forfromMedicoDTOtoMedico(List<MedicoDTO> listmedicoDTO){
		List<Medico> listMedico= new ArrayList<Medico>();
		for(MedicoDTO medico:listmedicoDTO) {
			listMedico.add(fromMedicoDTOToMedic(medico));
		}
		return listMedico;
		
	}
	
	

}
