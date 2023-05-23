package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, AbstractController{
	List<ObserverView> observers;
		
	public TrafficSimulatorController() {
		super();
		observers = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addObserver(ObserverView view) {
		observers.add(view);		
	}

	@Override
	public void removeObserver(ObserverView view) {
		observers.remove(view);
		
	}

	@Override
	public void notifyObserver() {
		for(ObserverView view : observers) {
			// chama algum metodo que deve ser implementado na view
			// atualizar a tela por exemplo
		}
		
	}

}
