package negocio.citas;

import java.util.ArrayList;
import java.util.List;

public class Calendario {

	private List<Año> años;

	private int currentAño;
	
	private int currentSelectedAño;

	public Calendario(int numeroAños, int current) {
		this.currentAño=currentSelectedAño = current;
		crearCalendario(numeroAños);
	}

	private void crearCalendario(int numeroAños) {
		años = new ArrayList();
		for (int i = 2020; i >= 2020 - numeroAños; i--) {
			años.add(new Año(i));

		}

	}

	public List<String> getMeses(int dia) {
		return this.findAño(currentSelectedAño).getMeses(dia);
	}
	
	public List<Integer> getAñosList(){
		List<Integer> ints= new ArrayList();
		for(Año año:años) {
			ints.add(new Integer(año.getAño()));
		}
		return ints;
	}

	private Año findAño(int añoINT) {
		for (Año año : años) {
			if (año.getAño() == añoINT)
				return año;
		}
		return null;
	}

	public List<String> getMesList() {
		return getMeses(1);
	}

	public int getDiasPorMes(String str) {
		return this.findAño(currentSelectedAño).findMes(str);
	}

	public void setCurrentYear(int current) {
		this.currentSelectedAño=current;
		
	}

	public int getMesnumero(String selectedItem) {
		return this.findAño(currentSelectedAño).getMesNumero(selectedItem);
		
	}

}
