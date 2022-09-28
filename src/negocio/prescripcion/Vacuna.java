package negocio.prescripcion;

public class Vacuna {
	
	private String id_Vacuna;
	private String nombre;
	


	
	private String tipoVacuna;
	
	public Vacuna(String id_Vacuna) {
		this.id_Vacuna = id_Vacuna;
	}
	
	public Vacuna(String id_Vacuna, String tipoVacuna) {
		this(id_Vacuna);
		this.tipoVacuna=tipoVacuna;
	}

	public Vacuna(String id_Vacuna, String nombre, String tipoVacuna) {
		this(id_Vacuna,tipoVacuna);
		this.nombre=nombre;
	}

	public String getId() {
		return id_Vacuna;
	}
	public String getNombre() {
		return nombre;
	}
	

	public String getTextToList() {
		return "Vacuna: "+this.nombre+" Tipo: "+this.tipoVacuna;
	}

}
