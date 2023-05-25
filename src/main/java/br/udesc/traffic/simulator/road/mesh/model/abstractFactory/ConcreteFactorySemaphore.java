package br.udesc.traffic.simulator.road.mesh.model.abstractFactory;

import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;
import br.udesc.traffic.simulator.road.mesh.model.node.NodeCrossSemaphore;
import br.udesc.traffic.simulator.road.mesh.model.node.NodeSemaphore;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public class ConcreteFactorySemaphore extends AbstractFactoryThreads{

	@Override
	public AbstractNode createNode(int x, int y, int type, ObserverNode observer) {
		return new NodeSemaphore(x, y, type, observer);
	}

	@Override
	public AbstractNode createCrossNode(int x, int y, int type, ObserverNode observer) {
		return new NodeCrossSemaphore(x, y, type, observer);
	}

}
