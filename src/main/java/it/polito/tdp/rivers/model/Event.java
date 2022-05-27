package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {

	private LocalDate data;
	private Flow fIn;
	private double c;
	private double fOut;
	private int fattore;

	public Event(LocalDate data, Flow fIn, double c, double fOut, int fattore) {
		super();
		this.data = data;
		this.fIn = fIn;
		this.c = c;
		this.fOut = fOut;
		this.fattore = fattore;
	}

	public Flow getfIn() {
		return fIn;
	}

	public int getFattore() {
		return fattore;
	}

	public LocalDate getData() {
		return data;
	}

	public double getC() {
		return c;
	}

	public double getfOut() {
		return fOut;
	}

	public void setfOut(double fOut) {
		this.fOut = fOut;
	}

	public void setC(double c) {
		this.c = c;
	}

	@Override
	public int compareTo(Event o) {
		return data.compareTo(o.data);
	}

}
