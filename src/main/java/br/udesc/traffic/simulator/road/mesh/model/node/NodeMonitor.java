package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class NodeMonitor extends AbstractNode {

    private ReentrantLock recurse = new ReentrantLock();

    public NodeMonitor(int x, int y, int type, ObserverNode observer) {
        super(x, y, type, observer, false);
    }

    @Override
    public synchronized void moveCar(Car car) {
    	
    }

    @Override
    public AbstractNode getNextNode(Car car) {
    	return null;
    }

    @Override
    public boolean tryNext() throws InterruptedException {
    	return recurse.tryLock(new Random().nextInt(2001 - 500) + 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void block() throws InterruptedException {
    	recurse.lock();
    }

    @Override
    public void release() {
        if (recurse.isHeldByCurrentThread()) {
        	recurse.unlock();
        }
    }
}
