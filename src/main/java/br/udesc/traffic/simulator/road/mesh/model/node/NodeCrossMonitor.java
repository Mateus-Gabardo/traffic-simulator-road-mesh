package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class NodeCrossMonitor extends AbstractNode{

	private ReentrantLock lock = new ReentrantLock();

	public NodeCrossMonitor(int x, int y, int type, ObserverNode observer) {
		super(x, y, type, observer, true);
	}

	@Override
	public synchronized void moveCar(Car car) throws InterruptedException {
		throw new InterruptedException();
	}


	@Override
	public AbstractNode getNextNode(Car car) {
		return null;
	}

	@Override
	public boolean tryNext() throws InterruptedException {
		return lock.tryLock(new Random().nextInt(2001 - 500) + 500, TimeUnit.MILLISECONDS);
	}

	@Override
	public void block() throws InterruptedException {
		lock.lock();
	}

	@Override
	public void release() {
		if (lock.isHeldByCurrentThread()) {
			lock.unlock();
		}
	}
}
