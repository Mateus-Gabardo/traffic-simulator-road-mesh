package br.udesc.traffic.simulator.road.mesh.model.command;

import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public class MoveUpCommand extends MoveCarCommand{

    AbstractPieceTable abstractPieceTable;
    public MoveUpCommand(AbstractPieceTable abstractPieceTable) {
        super(abstractPieceTable);
        this.abstractPieceTable = abstractPieceTable;
    }

    @Override
    public void execute(Car car) {
        AbstractPieceTable nextPiece = null;
        nextPiece.getNextPieceTable(car);
		if (nextPiece != null) {

		}
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
