package br.udesc.traffic.simulator.road.mesh.controller;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public interface AbstractController {
	
	public void addObserver(ObserverNode observer);
	
	public void removeObserver(ObserverNode observer);
	
	public void notifyObserver();

}
