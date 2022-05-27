package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

public class Statistiche {
	
	private int numGiorniNegativi;
	private List<Double> listaC;
	
	
	public Statistiche() {
		numGiorniNegativi = 0;
		listaC = new ArrayList<Double>();
	}

	public List<Double> getListaC() {
		return listaC;
	}
	
	public int getNumGiorniNegativi() {
		return numGiorniNegativi;
	}

	public void incrementaGiorniNegativi() {
		numGiorniNegativi++;
	}
	
	public double getOccupazioneMedia() {
		int somma = 0;
		for (Double d : listaC) {
			somma += d;
		}
		return somma/listaC.size();
	}

}
