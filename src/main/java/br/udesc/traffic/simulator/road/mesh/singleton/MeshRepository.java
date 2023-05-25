package br.udesc.traffic.simulator.road.mesh.singleton;

import java.util.ArrayList;
import java.util.List;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.node.*;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public class MeshRepository {
	private static MeshRepository instance;
	private int[][] roadMesh;
    private AbstractNode[][] nodeMesh;
    private List<AbstractNode> inicioNode;
    
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

    public AbstractNode[][] createNodeMesh(ObserverNode observer, int type) {
    	int linha = roadMesh.length;
    	int coluna = roadMesh[0].length;
    	AbstractNode[][] nodeMesh = new AbstractNode[roadMesh.length][roadMesh[0].length];

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
        
        this.nodeMesh = nodeMesh;

        for (int row = 0; row < roadMesh.length; row++) {
            for (int column = 0; column < roadMesh[0].length; column++) {
                int typeRoad = roadMesh[row][column];
                boolean isOK = true;
                switch (typeRoad) {
                    case GlobalContants.UP:
                    case GlobalContants.CRUZAMENTO_UP:
                        if (row - 1 < 0) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveUp(this.nodeMesh[row - 1][column]);
                        }
                        break;
                    case GlobalContants.RIGHT:
                    case GlobalContants.CRUZAMENTO_RIGHT:
                        if (column + 1 > roadMesh[0].length) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveRight(this.nodeMesh[row][column + 1]);
                        }
                        break;
                    case GlobalContants.DOWN:
                    case GlobalContants.CRUZAMENTO_DOWN:
                        if (row + 1 > roadMesh.length) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveDown(this.nodeMesh[row + 1][column]);
                        }
                        break;
                    case GlobalContants.LEFT:
                    case GlobalContants.CRUZAMENTO_LEFT:
                        if (column - 1 > 0) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveLeft(this.nodeMesh[row][column - 1]);
                        }
                        break;
                    case GlobalContants.CRUZAMENTO_DOWN_LEFT:
                        if (row + 1 > roadMesh.length || column - 1 > 0) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveDown(this.nodeMesh[row + 1][column]);
                            this.nodeMesh[row][column].setMoveLeft(this.nodeMesh[row][column - 1]);
                        }
                        break;
                    case GlobalContants.CRUZAMENTO_RIGHT_DOWN:
                        if (column + 1 > roadMesh[0].length || row + 1 > roadMesh.length) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveRight(this.nodeMesh[row][column + 1]);
                            this.nodeMesh[row][column].setMoveDown(this.nodeMesh[row + 1][column]);
                        }
                        break;
                    case GlobalContants.CRUZAMENTO_UP_LEFT:
                        if (row - 1 < 0 || column - 1 > 0) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveUp(this.nodeMesh[row - 1][column]);
                            this.nodeMesh[row][column].setMoveLeft(this.nodeMesh[row][column - 1]);
                        }
                        break;
                    case GlobalContants.CRUZAMENTO_UP_RIGHT:
                        if (row - 1 < 0 || column + 1 > roadMesh[0].length) {
                            isOK = false;
                        }
                        if (isOK) {
                            this.nodeMesh[row][column].setMoveUp(this.nodeMesh[row - 1][column]);
                            this.nodeMesh[row][column].setMoveRight(this.nodeMesh[row][column + 1]);
                        }
                        break;
                }
            }
        }
        return this.nodeMesh;
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
}
