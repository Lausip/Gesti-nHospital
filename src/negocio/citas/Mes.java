package negocio.citas;

public class Mes {
	
	private int numeroD�as;
	
	private String mes;

	public Mes(int numeroD�as, String mes) {
		super();
		this.numeroD�as = numeroD�as;
		this.mes = mes;
	}

	public String getMes() {
		return mes;
	}

	public int getDias() {
		return numeroD�as;
	}
	

}
