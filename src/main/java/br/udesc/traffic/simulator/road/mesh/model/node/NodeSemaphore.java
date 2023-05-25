package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class NodeSemaphore extends AbstractNode{

    private Semaphore semaphore = new Semaphore(1);

    public NodeSemaphore(int x, int y, int type, ObserverNode observer) {
        super(x, y, type, observer, false);
    }

    @Override
    public synchronized void moveCar(Car car) throws InterruptedException {
        try {
            AbstractNode nextNode = getNextNode(car);
            if(nextNode != null) {
                car.setNodeAtual(nextNode);
                getObserver().notifyMoveCar(getLine(), getColumn(), nextNode.getLine(), nextNode.getColumn());
                this.release();
                car.sleep();
            } else {
                car.setBlocked(true);
                getObserver().notifyEndCar(getLine(), getColumn(), car);
                this.release();
            }
        }catch (Exception e) {
            this.release();
            throw new InterruptedException();
        }
    }

    @Override
    public AbstractNode getNextNode(Car car) {
        AbstractNode currentNode = car.getNodeAtual();
        AbstractNode nextNode = null;

        AbstractNode[] directions = {
                currentNode.getMoveLeft(),
                currentNode.getMoveDown(),
                currentNode.getMoveRight(),
                currentNode.getMoveUp()
        };

        for (AbstractNode direction : directions) {
            if (direction != null) {
                nextNode = direction;
                break;
            }
        }
        return nextNode;
    }

    @Override
    public boolean tryNext() throws InterruptedException {
        return semaphore.tryAcquire(new Random().nextInt(2001 - 500) + 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void block() throws InterruptedException {
        semaphore.acquire();
    }

    @Override
    public void release() {
        semaphore.release();
    }
}
