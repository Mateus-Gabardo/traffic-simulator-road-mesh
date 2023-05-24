package br.udesc.traffic.simulator.road.mesh.model.abstractFactory;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;

public abstract class AbstractFactoryPieceTable {
    public abstract AbstractPieceTable createPieceTable(ObserverPieceTable observer, int line, int column);
}
