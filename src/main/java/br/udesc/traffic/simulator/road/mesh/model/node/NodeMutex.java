package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.concurrent.Semaphore;

public class NodeMutex extends AbstractNode{

    private Semaphore semaphore = new Semaphore(1);

    public NodeMutex(int x, int y, ObserverPieceTable observer) {
        super(x, y, observer);
    }

    @Override
    public void moveCar(Car car) {

    }

    @Override
    public AbstractNode getNextNode(Car car) {
        return null;
    }

    @Override
    public boolean tryNext() throws InterruptedException {
        return false;
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
