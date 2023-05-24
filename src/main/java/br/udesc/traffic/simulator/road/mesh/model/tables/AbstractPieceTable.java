package br.udesc.traffic.simulator.road.mesh.model.tables;

import br.udesc.traffic.simulator.road.mesh.model.command.CommandInvoker;
import br.udesc.traffic.simulator.road.mesh.model.command.MoveCarCommand;
import br.udesc.traffic.simulator.road.mesh.model.command.MoveUpCommand;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public abstract class AbstractPieceTable {

    //normal piece
    private final int LINE;
    private final int COLUMN;
    private ObserverPieceTable observer;
    protected CommandInvoker commandInvoker;
    private MoveCarCommand moveCarCommand;

    //crossing piece
    private boolean moveUP;
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveLeft;

    private boolean isCrossing;

    public AbstractPieceTable(int LINE, int COLUMN, ObserverPieceTable observer, boolean isCrossing) {
        this.LINE = LINE;
        this.COLUMN = COLUMN;
        this.observer = observer;
        commandInvoker = new CommandInvoker();
        moveUP = false;
        moveDown = false;
        moveRight = false;
        moveLeft = false;
        this.isCrossing = isCrossing;
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

    public void moveUpCommand() {
        commandInvoker.add(new MoveUpCommand(this));
    }

    public abstract void moveCar(Car car);
    public abstract void moveCarSimple(Car car) throws InterruptedException;
    public abstract AbstractPieceTable getNextPieceTable(Car car);
    public abstract boolean tryBlock() throws InterruptedException;
    public abstract void block() throws InterruptedException;
    public abstract void release();

    public boolean isMoveUP() {
        return moveUP;
    }

    public void setMoveUP(boolean moveUP) {
        this.moveUP = moveUP;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }
}
