package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class NodeCrossSemaphore extends AbstractNode {

	private Semaphore semaphore = new Semaphore(1);

	public NodeCrossSemaphore(int x, int y, int type, ObserverNode observer) {
		super(x, y, type, observer, true);
	}

	@Override
	public synchronized void moveCar(Car car) {
		AbstractNode nextNode = null;
		AbstractNode actual = this;
		List<AbstractNode> nodesCross = new ArrayList<>();

		try{
			nextNode = getNextNode(car);
			if (!nextNode.getIsCross()) {
				if (tryNext()) {
					car.setNodeAtual(nextNode);
					this.getObserver().notifyMoveCar(this.getLine(), this.getColumn(), nextNode.getLine(), nextNode.getColumn());
					this.release();
					car.sleep();
				}
			} else {
				boolean find = false;
				nodesCross.add(nextNode);
				actual = nextNode;
				while (!find) {
					nextNode = actual.getNextNode(car);
					if (nextNode.getIsCross()){
						nodesCross.add(nextNode);
						actual = nextNode;
					} else {
						nodesCross.add(nextNode);
						find = true;
					}
				}
				boolean isOK = true;
				for (AbstractNode node : nodesCross) {
					if (node.tryNext()) {
						node.block();
					} else {
						isOK = false;
						for (AbstractNode node2 : nodesCross) {
							node2.release();
						}
					}
				}
				if (isOK) {
					for (AbstractNode node : nodesCross) {
						car.setNodeAtual(node);
						this.getObserver().notifyMoveCar(actual.getLine(), actual.getColumn(), nextNode.getLine(), nextNode.getColumn());
						this.release();
						actual = node;
						car.sleep();
					}
				}
			}

		} catch (InterruptedException e) {
			this.release();
			if (nextNode != null) {
				nextNode.release();
			}
			car.setBlockedTrue();
		}
	}

	@Override
	public AbstractNode getNextNode(Car car) {
		Random random = new Random();
		AbstractNode actual = this;
		boolean find = false;
		while (!find) {
			int randomValue = random.nextInt(4) + 1;
			switch (randomValue) {
				case 1:{
					if (isMoveUp()) {
						actual = actual.getMoveUp();
						find = true;
					}
					break;
				}
				case 2:{
					if (isMoveDown()) {
						actual = actual.getMoveDown();
						find = true;
					}
					break;
				}
				case 3:{
					if (isMoveRight()) {
						actual = actual.getMoveRight();
						find = true;
					}
					break;
				}
				case 4:{
					if (isMoveLeft()) {
						actual = actual.getMoveLeft();
						find = true;
					}
					break;
				}
			}
		}

		return actual;
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
