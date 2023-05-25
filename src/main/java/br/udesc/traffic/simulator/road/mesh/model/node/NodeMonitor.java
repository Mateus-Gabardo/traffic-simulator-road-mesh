package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverPieceTable;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NodeMonitor extends AbstractNode{

    private ReentrantLock position = new ReentrantLock();

    public NodeMonitor(int x, int y, ObserverPieceTable observer, Lock position) {
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
        position.lock();
    }

    @Override
    public void release() {
        if (position.isHeldByCurrentThread()) {
            position.unlock();
        }
    }
}
