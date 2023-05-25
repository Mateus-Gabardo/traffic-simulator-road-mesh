package br.udesc.traffic.simulator.road.mesh.singleton;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.node.*;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public class MeshRepository {
	private static MeshRepository instance;
	private int[][] roadMesh;
    private AbstractNode[][] nodeMesh;
	
    public synchronized static MeshRepository getInstance(){
        if (instance == null)
			instance = new MeshRepository();
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

    public AbstractNode[][] createNodeMesh(ObserverNode observer, int type) {

        this.nodeMesh = new AbstractNode[roadMesh.length][roadMesh[0].length];

        for (int i = 0; i < roadMesh.length; i++) {
            for (int j = 0; j < roadMesh[0].length; j++) {
            	int typeRoad = roadMesh[i][j];
            	
                if (roadMesh[i][j] >= 1 && roadMesh[i][j] <= 4) {
                    if (type == GlobalContants.MONITOR){
                        AbstractNode novo = new NodeMonitor(i, j, typeRoad, observer);
                        nodeMesh[i][j] = novo;
                    } else {
                        AbstractNode novo = new NodeSemaphore(i, j, typeRoad, observer);
                        nodeMesh[i][j] = novo;
                    }
                } else if (roadMesh[i][j] >= 5 && roadMesh[i][j] <= 12) {
                    if (type == GlobalContants.MONITOR){
                        AbstractNode novo = new NodeCrossMonitor(i, j, typeRoad, observer);
                        nodeMesh[i][j] = novo;
                    } else {
                        AbstractNode novo = new NodeCrossSemaphore(i, j, typeRoad, observer);
                        nodeMesh[i][j] = novo;
                    }
                }
            }
        }

        for (int row = 0; row < roadMesh.length; row++) {
            for (int column = 0; column < roadMesh[0].length; column++) {
                int typeRoad = roadMesh[row][column];
                switch (typeRoad) {
                    case GlobalContants.UP:
                    case GlobalContants.CRUZAMENTO_UP: {
                        nodeMesh[row][column].setMoveUp(nodeMesh[row-1][column]);
                        break;
                    }
                    case GlobalContants.RIGHT:
                    case GlobalContants.CRUZAMENTO_RIGHT: {
                        nodeMesh[row][column].setMoveRight(nodeMesh[row][column+1]);
                        break;
                    }
                    case GlobalContants.DOWN:
                    case GlobalContants.CRUZAMENTO_DOWN: {
                        nodeMesh[row][column].setMoveDown(nodeMesh[row+1][column]);
                        break;
                    }
                    case GlobalContants.LEFT:
                    case GlobalContants.CRUZAMENTO_LEFT: {
                        nodeMesh[row][column].setMoveLeft(nodeMesh[row][column-1]);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_DOWN_LEFT: {
                        nodeMesh[row][column].setMoveDown(nodeMesh[row+1][column]);
                        nodeMesh[row][column].setMoveLeft(nodeMesh[row][column-1]);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_RIGHT_DOWN: {
                        nodeMesh[row][column].setMoveRight(nodeMesh[row][column+1]);
                        nodeMesh[row][column].setMoveDown(nodeMesh[row+1][column]);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_UP_LEFT: {
                        nodeMesh[row][column].setMoveUp(nodeMesh[row-1][column]);
                        nodeMesh[row][column].setMoveLeft(nodeMesh[row][column-1]);
                        break;
                    }
                    case GlobalContants.CRUZAMENTO_UP_RIGHT: {
                        nodeMesh[row][column].setMoveUp(nodeMesh[row-1][column]);
                        nodeMesh[row][column].setMoveRight(nodeMesh[row][column+1]);
                        break;
                    }
                    default:
                        return null;
                }
            }
        }

        return nodeMesh;
    }
}
