package br.udesc.traffic.simulator.road.mesh.controller;

import br.udesc.traffic.simulator.road.mesh.view.View;

public interface Controller {
	
	public void addObserver(View view);
	
	public void removeObserver(View view);
	
	public void notifyObserver();

}
