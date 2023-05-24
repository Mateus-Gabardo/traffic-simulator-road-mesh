package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, AbstractController{
	List<ObserverView> observers;
	private int[][] roadMesh;
		
	public TrafficSimulatorController() {
		super();
		observers = new ArrayList<>();
		this.roadMesh = MeshRepository.getInstance().getRoadMesh();
	}

	@Override
	public int getRowCount() {
		return roadMesh.length;
	}

	@Override
	public int getColumnCount() {
		return roadMesh[0].length;
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
