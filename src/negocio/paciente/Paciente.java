package negocio.paciente;

import negocio.consulta.DataBaseConsultor;
import negocio.consulta.impl.DatabaseConsultorImpl;

public class Paciente {
public String id_paciente;
String nombre;
String apellido;
String ciudad;
String id_contacto;
String id_historial;
String id_sanitario;

public String getId_paciente() {
	return id_paciente;
}
public void setId_paciente(String id_paciente) {
	this.id_paciente = id_paciente;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
public String getId_contacto() {
	return id_contacto;
}
public void setId_contacto(String id_contacto) {
	this.id_contacto = id_contacto;
}
public String getId_historial() {
	return id_historial;
}
public void setId_historial(String id_historial) {
	this.id_historial = id_historial;
}
public String getId_sanitario() {
	return id_sanitario;
}
public void setId_sanitario(String id_sanitario) {
	this.id_sanitario = id_sanitario;
}
public Paciente() {

}
@Override
public String toString() {
	return  nombre + ", " + apellido + ", id_historial=" + id_historial + ", N_Sanitario="
			+ id_sanitario;
}
public String toStringParaJList() {
	DataBaseConsultor db= new DatabaseConsultorImpl();
	return this.nombre+" "+this.apellido+" ";
}
	
}
