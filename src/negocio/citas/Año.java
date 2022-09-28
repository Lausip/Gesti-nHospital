package negocio.citas;

import java.util.ArrayList;
import java.util.List;

public class A�o {
	
	private List<Mes> meses;
	private int a�o;
	
	public A�o(int a�o) {
		this.a�o=a�o;
		rellenarA�o();
	}

	private void rellenarA�o() {
		meses= new ArrayList();
		meses.add(new Mes(31, "Enero"));
		if(a�o%4==0) {
			meses.add(new Mes(29, "Febrero"));
		}else {
			meses.add(new Mes(28, "Febrero"));
		}
		meses.add(new Mes(31, "Marzo"));
		meses.add(new Mes(30, "Abril"));
		meses.add(new Mes(31, "Mayo"));
		meses.add(new Mes(30, "Junio"));
		meses.add(new Mes(31, "Julio"));
		meses.add(new Mes(31, "Agosto"));
		meses.add(new Mes(30, "Septiembre"));
		meses.add(new Mes(31, "Octubre"));
		meses.add(new Mes(30, "Noviembre"));
		meses.add(new Mes(31, "Diciembre"));
	}

	public List<String> getMeses(int dia) {
		List<String> mesString= new ArrayList();
		for(Mes mes: meses) {
			if(mes.getDias()>=dia)
				mesString.add(mes.getMes());
		}
		return mesString;
	}

	public int getA�o() {
		return a�o;
	}

	public int findMes(String str) {
		for(Mes mes: meses) {
			if(mes.getMes().equals(str)) {
				return mes.getDias();
			}
		}
		return -1;
		
	}

	public int getMesNumero(String selectedItem) {
		int contador=1;
		for(Mes mes: meses) {
			if(mes.getMes().equals(selectedItem)) {
				return contador;
			}
			contador++;
		}

		return contador;
		
	}

}
