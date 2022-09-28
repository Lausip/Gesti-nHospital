package base.util;

import java.util.ArrayList;
import java.util.List;

import base.cita.CitaDTO;
import base.ubicacion.UbicacionDTO;
import negocio.citas.Cita;
import negocio.citas.Ubicacion;

public class UbicacionTranscryber {

	public static Ubicacion fromUbicacionDTOToUbicacion(UbicacionDTO u) {
		Ubicacion uaux = new Ubicacion();

		uaux.id_ubicacion = u.id_ubicacion;
		uaux.nombre=u.nombre;

		return uaux;

	}

	public static List<Ubicacion> forfromUbicacionDTOToUbicacion(List<UbicacionDTO> uDTO) {

		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

		for (UbicacionDTO u : uDTO) {

			ubicaciones.add(fromUbicacionDTOToUbicacion(u));
		}

		return ubicaciones;
	}

}
