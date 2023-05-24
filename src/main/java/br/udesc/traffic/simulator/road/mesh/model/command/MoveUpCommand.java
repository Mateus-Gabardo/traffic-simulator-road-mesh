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
        try {
            nextPiece.getNextPieceTable(car);
            if (nextPiece != null) {

            }
        } catch (InterruptedException e) {
            abstractPieceTable.release();
            if (nextPiece != null) {
                nextPiece.release();
            }
            car.setBlockedTrue();
        }
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
