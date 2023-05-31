package br.udesc.traffic.simulator.road.mesh.singleton;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.abstractFactory.AbstractFactoryThreads;
import br.udesc.traffic.simulator.road.mesh.model.node.*;
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

public class MeshRepository {
	private static MeshRepository instance;
	private int[][] roadMesh;
    private AbstractNode[][] nodeMesh;
    private List<AbstractNode> inicioNode;
    private PieceModel[][] pieces;
    private AbstractFactoryThreads factory;
    
    private MeshRepository() {
    	inicioNode = new ArrayList<>();
    }
	
    public synchronized static MeshRepository getInstance(){
        if (instance == null) {
        	instance = new MeshRepository();        	
        }
        return instance;
    }
    
    public void setRoadMesh(int[][] malha) {
    	this.roadMesh = malha;
    }
    
    public int[][] getRoadMesh(){
    	return this.roadMesh;
    }
    
    public int getLineSize() {
    	return roadMesh.length;
    }
    
    public int getColumnSize() {
    	return roadMesh[0].length;
    }
    
    public List<AbstractNode> getNodeInit(){
    	return this.inicioNode;
    }
    
    public void addInitNode(AbstractNode node) {
    	inicioNode.add(node);
    }

    public AbstractFactoryThreads getFactory() {
		return factory;
	}

	public void setFactory(AbstractFactoryThreads factory) {
		this.factory = factory;
	}

	public AbstractNode[][] createNodeMesh(ObserverNode observer) {
    	AbstractNode[][] nodeMesh = new AbstractNode[roadMesh.length][roadMesh[0].length];
    	AbstractFactoryThreads factoryNode = this.getFactory();

        for (int i = 0; i < roadMesh.length; i++) {
            for (int j = 0; j < roadMesh[0].length; j++) {
            	int typeRoad = roadMesh[i][j];
            	AbstractNode node = null;
                if (roadMesh[i][j] >= 1 && roadMesh[i][j] <= 4) {
                	node = factoryNode.createNode(i, typeRoad, typeRoad, observer);
                } else if (roadMesh[i][j] >= 5 && roadMesh[i][j] <= 12) {
                	node = factoryNode.createCrossNode(i, typeRoad, typeRoad, observer);
                }
                nodeMesh[i][j] = node;
            }
        }
        
        this.nodeMesh = nodeMesh;

        for (int row = 0; row < roadMesh.length; row++) {
            for (int column = 0; column < roadMesh[0].length; column++) {
                int typeRoad = roadMesh[row][column];
                switch (typeRoad) {
                    case GlobalContants.UP:
                    case GlobalContants.CRUZAMENTO_UP: {
                    	setMoveUp(row, column, row-1, column);
                    	break;
                    }
                    case GlobalContants.RIGHT:
                    case GlobalContants.CRUZAMENTO_RIGHT: {
                    	setMoveRight(row, column, row, column+1);
                        break;
                    }
                    case GlobalContants.DOWN:
                    case GlobalContants.CRUZAMENTO_DOWN: {
                    	setMoveDown(row, column, row+1, column);
                        break;
                    }
                    case GlobalContants.LEFT:
                    case GlobalContants.CRUZAMENTO_LEFT: {
                    	setMoveLeft(row, column, row, column-1);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_DOWN_LEFT: {
                    	setMoveDown(row, column, row+1, column);
                    	setMoveLeft(row, column, row, column-1);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_RIGHT_DOWN: {
                    	setMoveRight(row, column, row, column+1);
                    	setMoveDown(row, column, row+1, column);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_UP_LEFT: {
                    	setMoveUp(row, column, row-1, column);
                    	setMoveLeft(row, column, row, column-1);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_UP_RIGHT: {
                    	setMoveUp(row, column, row-1, column);
                    	setMoveRight(row, column, row, column+1);
                        break;
                    }
                }
            }
        }
        return nodeMesh;
    }
    
    private boolean permiteLeft(int column) {
    	return column > 0;
    }
    
    private boolean permiteRigth(int column) {
    	return column < roadMesh.length;
    }
    
    private boolean permiteUp(int row) {
    	return row > 0;
    }
    
    private boolean permiteDown(int row) {
    	return row < roadMesh.length-1;
    }
    
    private void setMoveRight(int row, int column, int nextRow, int nextColumn) {
    	if(permiteRigth(column)) {
    		nodeMesh[row][column].setMoveDown(nodeMesh[nextRow][nextColumn]);
    	}
    }
    
    private void setMoveLeft(int row, int column, int nextRow, int nextColumn) {
    	if(permiteLeft(column)) {
    		nodeMesh[row][column].setMoveDown(nodeMesh[nextRow][nextColumn]);
    	}
    }
    
    private void setMoveUp(int row, int column, int nextRow, int nextColumn) {
    	if(permiteUp(row)) {
    		nodeMesh[row][column].setMoveDown(nodeMesh[nextRow][nextColumn]);
    	}
    }
    
    private void setMoveDown(int row, int column, int nextRow, int nextColumn) {
    	if(permiteDown(row)) {
    		nodeMesh[row][column].setMoveDown(nodeMesh[nextRow][nextColumn]);
    	}
    }
    
    public PieceModel[][] initPiece() {
		int row = roadMesh.length;
		int column = roadMesh[0].length;
		this.pieces = new PieceModel[row][column];
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
            	pieces[i][j] = getPiece(roadMesh[i][j]);
            }
        }
		return pieces;
	}
    
    public PieceModel[][] getPiieces(){
    	return pieces;
    }
	
	private PieceModel getPiece(int tipo) {
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
}
