package br.udesc.traffic.simulator.road.mesh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;
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
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;

public class TrafficSimulatorController implements AbstractTrafficSimulatorTableController, AbstractController {
	ObserverNode observerNode;
	private int[][] roadMesh;
	private boolean interruptClick;
	private AbstractNode[][] nodeMesh;
	private List<Car> cars;
	private int type;

	public TrafficSimulatorController(int type) {
		super();
		this.type = type;
		interruptClick = false;
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
	
	public boolean hasElementAt(int linha, int coluna, int[][] state) {
		if (state[linha][coluna] == 0) {
			return false;
		} else {
			return true;
		}
	}
	
    public void onIniciar(String s) {
        interruptClick = false;
        nodeMesh = MeshRepository.getInstance().createNodeMesh(observerNode, type);
        mapeaEntrada();
        cars = new ArrayList<>();
        if(s.matches("^\\d+$")){
            int numThreads = Integer.parseInt(s);
            for(int i = 0; i < numThreads; i ++){
                geraCarro();
            }
        }
    }
    
    public void geraCarro(){
        int posicao = new Random().nextInt(MeshRepository.getInstance().getNodeInit().size());
        int linha = MeshRepository.getInstance().getNodeInit().get(posicao).getLine();
        int coluna = MeshRepository.getInstance().getNodeInit().get(posicao).getColumn();
        Car car = new Car(nodeMesh[linha][coluna]);
        cars.add(car);
        car.start();
    }
    
    public void onEncerrarCarros(){
        interruptClick = true;
        for (Car carro: cars) {
            carro.setBlockedTrue();
        }
        cars.clear();
    }
    
	private void mapeaEntrada() {
		for (int coluna = 0; coluna < roadMesh[0].length; coluna++) {
			if (roadMesh[0][coluna] == GlobalContants.DOWN) {
				MeshRepository.getInstance().addInitNode(nodeMesh[0][coluna]);
			}
			if (roadMesh[roadMesh.length - 1][coluna] == GlobalContants.UP) {
				MeshRepository.getInstance().addInitNode(nodeMesh[roadMesh.length - 1][coluna]);
			}
		}
		for (int linha = 0; linha < roadMesh.length - 1; linha++) {
			if (roadMesh[linha][0] == GlobalContants.RIGHT) {
				MeshRepository.getInstance().addInitNode(nodeMesh[linha][0]);
			}
			if (roadMesh[linha][roadMesh[0].length - 1] == GlobalContants.LEFT) {
				MeshRepository.getInstance().addInitNode(nodeMesh[linha][roadMesh[0].length - 1]);
			}
		}
	}

	@Override
	public void addObserver(ObserverNode observer) {
		observerNode = observer;
	}

	@Override
	public void notificarInicioCarro(int linha, int coluna) {
		observerNode.notifyStartCar(linha, coluna);
	}

	@Override
	public void notificarMovimentoCarro(int linhaAntiga, int colunaAntiga, int linhaNova, int colunaNova) {
		observerNode.notifyMoveCar(linhaAntiga, colunaAntiga, linhaNova, colunaNova);
	}

	@Override
	public void notificarFimCarro(int linha, int coluna, Car car) {
		observerNode.notifyEndCar(linha, coluna, car);
        if (!interruptClick) {
            cars.remove(car);
            geraCarro();
        }
	}
}
