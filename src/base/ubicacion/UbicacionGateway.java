package base.ubicacion;



import java.time.LocalDate;
import java.util.List;

import base.Gateway;
import base.cita.CitaDTO;


public interface UbicacionGateway<T> extends Gateway<T>{

	List<UbicacionDTO> findAllUbicaciones();

	List<String> findCitas(String id_ubicacion,LocalDate fecha);

	UbicacionDTO findUbicacionID(String id_Ubicacion);







}
