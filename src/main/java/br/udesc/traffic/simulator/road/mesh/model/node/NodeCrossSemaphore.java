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
	public synchronized void moveCar(Car car) throws InterruptedException {
		AbstractNode nextNode = null;
		AbstractNode currentNode = car.getNodeAtual();
		AbstractNode firstNode = currentNode;
		List<AbstractNode> nodesCross = new ArrayList<>();

		try{
			nextNode = getNextNode(car);
			if (!nextNode.getIsCross()) {
				if (this.tryNext()) {
					car.setNodeAtual(nextNode);
					this.getObserver().notifyMoveCar(this.getLine(), this.getColumn(), nextNode.getLine(), nextNode.getColumn());
					this.release();
					car.sleep();
				}
			} else {
				boolean find = false;
				boolean isOK = true;
				nodesCross.add(nextNode);
				if (nextNode.tryNext()) {
					nextNode.block();
				}else {
					isOK = false;
				}
				currentNode = nextNode;
				while (!find) {
					nextNode = currentNode.getNextNode(car);
					if (nextNode.getIsCross()){
						nodesCross.add(nextNode);
						if (nextNode.tryNext()) {
							nextNode.block();
						}else {
							isOK = false;
						}
						currentNode = nextNode;
					} else {
						nodesCross.add(nextNode);
						if (nextNode.tryNext()) {
							nextNode.block();
						}else {
							isOK = false;
						}
						find = true;
					}
				}
				
				if (isOK) {
					for (AbstractNode node : nodesCross) {
						car.setNodeAtual(node);
						firstNode.getObserver().notifyMoveCar(firstNode.getLine(), firstNode.getColumn(), node.getLine(), node.getColumn());
						firstNode.release();
						firstNode = node;
						car.sleep();
					}
				} else {
					for (AbstractNode node2 : nodesCross) {
						node2.release();
					}
				}
			}

		} catch (InterruptedException e) {
			this.release();
			throw new InterruptedException();
		}
	}

	@Override
	public AbstractNode getNextNode(Car car) {
		Random random = new Random();
		AbstractNode currentNode = car.getNodeAtual();
		AbstractNode next = null;
		boolean find = false;
		while (!find) {
			int randomValue = random.nextInt(4) + 1;
			switch (randomValue) {
				case 1:{
					if (isMoveUp()) {
						next = currentNode.getMoveUp();
						find = true;
					}
					break;
				}
				case 2:{
					if (isMoveDown()) {
						next = currentNode.getMoveDown();
						find = true;
					}
					break;
				}
				case 3:{
					if (isMoveRight()) {
						next = currentNode.getMoveRight();
						find = true;
					}
					break;
				}
				case 4:{
					if (isMoveLeft()) {
						next = currentNode.getMoveLeft();
						find = true;
					}
					break;
				}
			}
		}

		return next;
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
