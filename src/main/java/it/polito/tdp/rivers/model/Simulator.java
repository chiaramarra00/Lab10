package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class Simulator {

	private River river;
	private double k;
	private double Q;
	private double cPrecedente;

	private PriorityQueue<Event> queue;

	private Statistiche statistiche;

	private void creaEventi() {
		Q = k * river.getFlowAvg() * 30 * 86400;
		cPrecedente = Q / 2;
		for (Flow f : river.getFlows()) {
			LocalDate data = f.getDay();
			int fattore = 1;
			if (Math.random() < 0.05)
				fattore = 10;
			Event e = new Event(data, f, 0, 0, fattore);
			queue.add(e);
		}

	}

	public void init(River river, double k) {
		queue = new PriorityQueue<Event>();
		statistiche = new Statistiche();
		this.river = river;
		this.k = k;
		creaEventi();
	}

	public void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			processaEvento(e);
		}
	}

	private void processaEvento(Event e) {
		double fOutMin = e.getFattore() * 0.8 * river.getFlowAvg() * 86400;
		double c = cPrecedente + e.getfIn().getFlow() * 86400 - fOutMin;
		if (c < 0) {
			e.setC(0);
			e.setfOut(cPrecedente + e.getfIn().getFlow() * 86400);
			statistiche.incrementaGiorniNegativi();
			statistiche.getListaC().add(0.0);
			cPrecedente = 0;
		} else if (c > Q) {
			e.setC(Q);
			e.setfOut(e.getfIn().getFlow() * 86400 + cPrecedente - Q);
			statistiche.getListaC().add(Q);
			cPrecedente = Q;
		} else {
			e.setC(c);
			e.setfOut(fOutMin);
			statistiche.getListaC().add(c);
			cPrecedente = c;
		}
	}

	public Statistiche getStatistiche() {
		return statistiche;
	}

}
