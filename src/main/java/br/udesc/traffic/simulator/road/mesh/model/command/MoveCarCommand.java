package br.udesc.traffic.simulator.road.mesh.model.command;

import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public abstract class MoveCarCommand implements Command{

    private AbstractPieceTable abstractPieceTable;

    public MoveCarCommand(AbstractPieceTable abstractPieceTable) {
        this.abstractPieceTable = abstractPieceTable;
    }

    public abstract void execute(Car car);
}
