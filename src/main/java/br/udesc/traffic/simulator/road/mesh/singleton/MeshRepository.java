package br.udesc.traffic.simulator.road.mesh.singleton;

public class MeshRepository {
	private static MeshRepository instance;
	private int[][] roadMesh;
	
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
}
