package base;

import java.util.List;

import base.paciente.PacienteDTO;

public interface Gateway<T>  {

	public void add(T t);
	
	public void remove(T t);
	
	public void update(T t);
	
	public List findById(String id);

	public List<T> findAll();
	
	
}
