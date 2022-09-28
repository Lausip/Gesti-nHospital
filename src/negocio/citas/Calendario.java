package negocio.citas;

import java.util.ArrayList;
import java.util.List;

public class Calendario {

	private List<A�o> a�os;

	private int currentA�o;
	
	private int currentSelectedA�o;

	public Calendario(int numeroA�os, int current) {
		this.currentA�o=currentSelectedA�o = current;
		crearCalendario(numeroA�os);
	}

	private void crearCalendario(int numeroA�os) {
		a�os = new ArrayList();
		for (int i = 2020; i >= 2020 - numeroA�os; i--) {
			a�os.add(new A�o(i));

		}

	}

	public List<String> getMeses(int dia) {
		return this.findA�o(currentSelectedA�o).getMeses(dia);
	}
	
	public List<Integer> getA�osList(){
		List<Integer> ints= new ArrayList();
		for(A�o a�o:a�os) {
			ints.add(new Integer(a�o.getA�o()));
		}
		return ints;
	}

	private A�o findA�o(int a�oINT) {
		for (A�o a�o : a�os) {
			if (a�o.getA�o() == a�oINT)
				return a�o;
		}
		return null;
	}

	public List<String> getMesList() {
		return getMeses(1);
	}

	public int getDiasPorMes(String str) {
		return this.findA�o(currentSelectedA�o).findMes(str);
	}

	public void setCurrentYear(int current) {
		this.currentSelectedA�o=current;
		
	}

	public int getMesnumero(String selectedItem) {
		return this.findA�o(currentSelectedA�o).getMesNumero(selectedItem);
		
	}

}
