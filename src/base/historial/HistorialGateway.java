package base.historial;

import java.util.List;

import base.Gateway;

public interface HistorialGateway extends Gateway {

	void addWithFechaAndHora(String fecha, String hora);
}
