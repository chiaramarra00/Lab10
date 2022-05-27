package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private List<River> rivers;

	private Simulator sim;

	public Model() {
		super();
		dao = new RiversDAO();
		sim = new Simulator();
	}

	public List<River> getAllRivers() {
		if (rivers == null) {
			rivers = dao.getAllRivers();
		}
		return rivers;
	}

	public void setRiverStat(River river) {
		if (river.getFlows().isEmpty()) {
			river.setFlows(dao.getRiverFlows(river));
			river.setFlowAvg(dao.getRiverFlowAvg(river));
		}
	}

	public Statistiche simula(River river, double k) {
		sim.init(river, k);
		sim.run();
		return sim.getStatistiche();
	}

}
