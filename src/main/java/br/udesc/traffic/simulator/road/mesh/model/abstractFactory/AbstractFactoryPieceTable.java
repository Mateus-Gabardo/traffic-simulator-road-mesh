package br.udesc.traffic.simulator.road.mesh.model.abstractFactory;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;

public abstract class AbstractFactoryPieceTable {
    public abstract AbstractPieceTable createPieceTable(ObserverNode observer, int line, int column);
}
