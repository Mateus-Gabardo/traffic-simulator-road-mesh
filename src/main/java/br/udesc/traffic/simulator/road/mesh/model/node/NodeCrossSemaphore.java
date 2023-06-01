package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class NodeCrossSemaphore extends AbstractNode {

	private Semaphore semaphore = new Semaphore(1);

	public NodeCrossSemaphore(int x, int y, int type, ObserverNode observer) {
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
