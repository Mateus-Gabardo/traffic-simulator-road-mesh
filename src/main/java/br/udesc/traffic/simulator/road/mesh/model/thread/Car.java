package br.udesc.traffic.simulator.road.mesh.model.thread;

import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;

import java.util.Random;

public class Car extends Thread{

    private int line;
    private int column;
    private boolean isFirst;
    private boolean isBlocked;
    private int timeSleep;
    private AbstractPieceTable pieceTable;
    private AbstractPieceTable out;

    public Car(int line, int column, AbstractPieceTable pieceTable) {
        this.line = line;
        this.column = column;
        this.isFirst = true;
        this.isBlocked = false;
        this.timeSleep = new Random().nextInt(2001 - 500) + 500;
        this.pieceTable = pieceTable;
        this.out = null;
    }

    @Override
    public void run() {
        try{
            while (!isBlocked && !isInterrupted()){
                if (isFirst) {
                    if (pieceTable.tryBlock()) {
                        pieceTable.getObserver().notifyStartCar(line, column);
                        setFirstFalse();
                        sleep();
                    }
                } else {
                    pieceTable.moveCar(this);
                }
            }
            pieceTable.getObserver().notifyEndCar(line, column, this);
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            pieceTable.getObserver().notifyEndCar(line, column, this);
            Thread.currentThread().interrupt();
        }
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setBlockedTrue() {
        isBlocked = true;
    }

    public void setFirstFalse() {
        isFirst = false;
    }

    public void sleep() throws InterruptedException{
        sleep(timeSleep);
    }

    public boolean getFirst() {
        return isFirst;
    }

    public boolean getBlocked() {
        return isBlocked;
    }

    public AbstractPieceTable getPieceTable() {
        return pieceTable;
    }

    public void setPieceTable(AbstractPieceTable pieceTable) {
        this.pieceTable = pieceTable;
    }

    public AbstractPieceTable getOut() {
        return out;
    }

    public void setOut(AbstractPieceTable out) {
        this.out = out;
    }
}
