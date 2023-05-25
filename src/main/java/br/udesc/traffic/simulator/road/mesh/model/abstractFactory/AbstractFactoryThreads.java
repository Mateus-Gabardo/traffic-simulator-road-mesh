package br.udesc.traffic.simulator.road.mesh.model.abstractFactory;

import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;

public abstract class AbstractFactoryThreads {
	public abstract AbstractNode createNode(int x, int y, ObserverNode observer);
	public abstract AbstractNode createCrossNode(int x, int y, ObserverNode observer);
}
