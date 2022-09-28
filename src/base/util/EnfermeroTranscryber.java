package base.util;

import java.util.ArrayList;
import java.util.List;

import base.enfermero.EnfermeroDTO;
import negocio.sanitario.Enfermero;


public class EnfermeroTranscryber {

	public static List<Enfermero> forfromEnfermeroDTOtoEnfermero(
			List<EnfermeroDTO> listEnfermeroD) {
		List<Enfermero> listEnfermero= new ArrayList<Enfermero>();
		for(EnfermeroDTO list:listEnfermeroD) {
			listEnfermero.add(fromEnfermeroDTOToEnfermero(list));
		}
		return listEnfermero;
	}

	private static Enfermero fromEnfermeroDTOToEnfermero(EnfermeroDTO e) {
      Enfermero medic= new Enfermero();
		
	    medic.setId_Enfermero(e.id);
	    medic.setNombre(e.nombre);
	    medic.setApellidos(e.apellido);
	    medic.setEmail(e.email);
	    medic.setEspecilidad(e.especialidad);		
	    
		return medic;
		
	}

}
