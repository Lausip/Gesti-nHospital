package base.util;

import java.util.ArrayList;
import java.util.List;

import base.especialista.EspecialistaDTO;
import negocio.equipo.Especialista;
import negocio.paciente.Paciente;

public class EspecialistaTranscryber {
	public static Especialista fromEspecialistaDTOToEspecialista(EspecialistaDTO p) {
		Especialista e= new Especialista();
		e.id_especialista=p.id_especialista;
		e.nombre=p.nombre;
      return e;
      }
		
		
		

	public static List<Especialista> forfromEspecialistaDTOtoEspecialista(List<EspecialistaDTO> listEspecialistaDTO){
		List<Especialista> listMedico= new ArrayList<Especialista>();
		for(EspecialistaDTO paciente:listEspecialistaDTO) {
			listMedico.add( fromEspecialistaDTOToEspecialista(paciente));
		}
		return listMedico;
	}
}
