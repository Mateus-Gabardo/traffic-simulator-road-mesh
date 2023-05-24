package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.road.PieceModel;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadDown;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadLeft;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadRight;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadUp;
import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, AbstractController {
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
	public PieceModel getValueAt(int rowIndex, int columnIndex) {
		int tipo = roadMesh[rowIndex][columnIndex];
		switch (tipo) {
		case GlobalContants.UP: {
			return new RoadUp(tipo);
		}
		case GlobalContants.RIGHT: {
			return new RoadRight(tipo);
		}
		case GlobalContants.DOWN: {
			return new RoadDown(tipo);
		}
		case GlobalContants.LEFT: {
			return new RoadLeft(tipo);
		}
		default:
			return null;
		}
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
		for (ObserverView view : observers) {
			// chama algum metodo que deve ser implementado na view
			// atualizar a tela por exemplo
		}

	}

	/**
	 * Avalia se vai retornar algum valor ou não
	 * 
	 * @param linha
	 * @param coluna
	 * @return
	 */
	public boolean hasElementAt(int linha, int coluna, int[][] state) {
		if (state[linha][coluna] == 0) {
			return false;
		} else {
			return true;
		}
	}

}
