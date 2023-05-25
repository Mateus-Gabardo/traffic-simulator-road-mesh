package br.udesc.traffic.simulator.road.mesh.model.abstractFactory;

import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;
import br.udesc.traffic.simulator.road.mesh.model.node.NodeCrossMonitor;
import br.udesc.traffic.simulator.road.mesh.model.node.NodeMonitor;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public class ConcreteFactoryMonitor extends AbstractFactoryThreads{

	@Override
	public AbstractNode createNode(int x, int y, int type, ObserverNode observer) {
		return new NodeMonitor(x, y, type, observer);
	}

	@Override
	public AbstractNode createCrossNode(int x, int y, int type, ObserverNode observer) {
		return new NodeCrossMonitor(x, y, type, observer);
	}
}
