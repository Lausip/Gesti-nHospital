package base.especialista;

import java.util.List;

import base.Gateway;

public interface EspecialistaGateway<T> extends Gateway<T> {

	List<EspecialistaDTO>buscarEspecialistas();

	List<EspecialistaDTO> buscarEspecialistasByName(String nombre);


}

