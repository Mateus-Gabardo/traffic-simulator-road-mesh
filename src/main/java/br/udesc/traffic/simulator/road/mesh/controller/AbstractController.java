package br.udesc.traffic.simulator.road.mesh.controller;

import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;

public interface AbstractController {
	
	public void addObserver(ObserverView view);
	
	public void removeObserver(ObserverView view);
	
	public void notifyObserver();

}
