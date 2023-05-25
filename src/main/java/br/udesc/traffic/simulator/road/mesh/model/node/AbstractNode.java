package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public abstract class AbstractNode {
    private AbstractNode moveUp;
    private AbstractNode moveDown;
    private AbstractNode moveRight;
    private AbstractNode moveLeft;
    private int line;
    private int column;
    private int type;
    private ObserverNode observer;

    public AbstractNode(int x, int y, int type, ObserverNode observer) {
        this.moveUp = null;
        this.moveDown = null;
        this.moveRight = null;
        this.moveLeft = null;
        this.line = x;
        this.column = y;
        this.type = type;
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

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
    
    public int getType() {
    	return type;
    }
    
    public ObserverNode getObserver() {
    	return this.observer;
    }

    public abstract void moveCar(Car car);
    public abstract AbstractNode getNextNode(Car car);
    public abstract boolean tryNext() throws InterruptedException;
    public abstract void block() throws InterruptedException;
    public abstract void release();
}
