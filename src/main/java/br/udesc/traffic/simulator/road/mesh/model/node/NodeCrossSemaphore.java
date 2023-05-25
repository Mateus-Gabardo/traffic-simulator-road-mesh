package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public class NodeCrossSemaphore extends AbstractNode {

	public NodeCrossSemaphore(int x, int y, int type, ObserverNode observer) {
		super(x, y, type, observer);
	}

	@Override
	public void moveCar(Car car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractNode getNextNode(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryNext() throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void block() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

}
