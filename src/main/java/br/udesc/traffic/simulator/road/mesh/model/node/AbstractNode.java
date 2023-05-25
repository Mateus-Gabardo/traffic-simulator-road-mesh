package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.tables.AbstractPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public abstract class AbstractNode {
    private AbstractNode moveUp;
    private AbstractNode moveDown;
    private AbstractNode moveRight;
    private AbstractNode moveLeft;
    private int x;
    private int y;
    private ObserverPieceTable observer;

    public AbstractNode(int x, int y, ObserverPieceTable observer) {
        this.moveUp = null;
        this.moveDown = null;
        this.moveRight = null;
        this.moveLeft = null;
        this.x = x;
        this.y = y;
        this.observer = observer;
    }

    public void setMoveUp(AbstractNode moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(AbstractNode moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveRight(AbstractNode moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveLeft(AbstractNode moveLeft) {
        this.moveLeft = moveLeft;
    }

    public AbstractNode getMoveUp() {
        return moveUp;
    }

    public AbstractNode getMoveDown() {
        return moveDown;
    }

    public AbstractNode getMoveRight() {
        return moveRight;
    }

    public AbstractNode getMoveLeft() {
        return moveLeft;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void moveCar(Car car);
    public abstract AbstractNode getNextNode(Car car);
    public abstract boolean tryNext() throws InterruptedException;
    public abstract void block() throws InterruptedException;
    public abstract void release();
}
