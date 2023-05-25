package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.road.PieceModel;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoDown;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoDownLeft;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoLeft;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoRight;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoRightDown;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoUp;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoUpLeft;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadCruzamentoUpRight;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadDown;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadLeft;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadRight;
import br.udesc.traffic.simulator.road.mesh.model.road.RoadUp;
import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, AbstractController {
	List<ObserverView> observers;
	ObserverNode observerNode;
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
		case GlobalContants.CRUZAMENTO_DOWN: {
			return new RoadCruzamentoDown(tipo);
		}
		case GlobalContants.CRUZAMENTO_UP: {
			return new RoadCruzamentoUp(tipo);
		}
		case GlobalContants.CRUZAMENTO_LEFT: {
			return new RoadCruzamentoLeft(tipo);
		}
		case GlobalContants.CRUZAMENTO_RIGHT: {
			return new RoadCruzamentoRight(tipo);
		}
		case GlobalContants.CRUZAMENTO_DOWN_LEFT: {
			return new RoadCruzamentoDownLeft(tipo);
		}
		case GlobalContants.CRUZAMENTO_RIGHT_DOWN: {
			return new RoadCruzamentoRightDown(tipo);
		}
		case GlobalContants.CRUZAMENTO_UP_LEFT: {
			return new RoadCruzamentoUpLeft(tipo);
		}
		case GlobalContants.CRUZAMENTO_UP_RIGHT: {
			return new RoadCruzamentoUpRight(tipo);
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
