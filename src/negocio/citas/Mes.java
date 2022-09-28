package negocio.citas;

public class Mes {
	
	private int numeroDías;
	
	private String mes;

	public Mes(int numeroDías, String mes) {
		super();
		this.numeroDías = numeroDías;
		this.mes = mes;
	}

	public String getMes() {
		return mes;
	}

	public int getDias() {
		return numeroDías;
	}
	

}
