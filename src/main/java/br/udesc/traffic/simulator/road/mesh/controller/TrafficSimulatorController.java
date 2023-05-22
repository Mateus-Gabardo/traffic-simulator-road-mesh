package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.view.View;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, Controller{
	List<View> observers;
		
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
	public void addObserver(View view) {
		observers.add(view);		
	}

	@Override
	public void removeObserver(View view) {
		observers.remove(view);
		
	}

	@Override
	public void notifyObserver() {
		for(View view : observers) {
			// chama algum metodo que deve ser implementado na view
			// atualizar a tela por exemplo
		}
		
	}

}
