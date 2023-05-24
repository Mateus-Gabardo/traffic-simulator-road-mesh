package br.udesc.traffic.simulator.road.mesh.model.tables;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public abstract class AbstractPieceTable {

    private final int LINE;
    private final int COLUMN;
    private ObserverPieceTable observer;

    public AbstractPieceTable(int LINE, int COLUMN, ObserverPieceTable observer) {
        this.LINE = LINE;
        this.COLUMN = COLUMN;
        this.observer = observer;
    }

    public int getLINE() {
        return LINE;
    }

    public int getCOLUMN() {
        return COLUMN;
    }

    public ObserverPieceTable  getObserver() {
        return observer;
    }

    public abstract void moveCar(Car car);
    public abstract void moveCarSimple(Car car) throws InterruptedException;
    public abstract AbstractPieceTable getNextPieceTable(Car car);
    public abstract boolean tryBlock() throws InterruptedException;
    public abstract void block() throws InterruptedException;
    public abstract void release();
}
