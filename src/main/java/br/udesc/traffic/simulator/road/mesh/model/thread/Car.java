package br.udesc.traffic.simulator.road.mesh.model.thread;

import java.util.Random;

import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;

public class Car extends Thread {
	private boolean isFirst;
	private boolean isBlocked;
	private int timeSleep;
	private AbstractNode nodeAtual;

	public Car(AbstractNode nodeAtual) {
		this.isFirst = true;
		this.isBlocked = false;
		this.timeSleep = new Random().nextInt(2001 - 500) + 500;
		this.nodeAtual = nodeAtual;
	}

	@Override
	public void run() {
		try {
			while (!isBlocked && !isInterrupted()) {
				if (isFirst) {
					if (nodeAtual.tryNext()) {
						nodeAtual.getObserver().notifyStartCar(nodeAtual.getLine(), nodeAtual.getColumn());
						setFirstFalse();
						sleep();
					}
				} else {
					nodeAtual.moveCar(this);
				}
			}
			nodeAtual.getObserver().notifyEndCar(nodeAtual.getLine(), nodeAtual.getColumn(), this);
			Thread.currentThread().interrupt();
		} catch (InterruptedException e) {
			nodeAtual.getObserver().notifyEndCar(nodeAtual.getLine(), nodeAtual.getColumn(), this);
			Thread.currentThread().interrupt();
		}
	}

	public void setBlockedTrue() {
		isBlocked = true;
	}

	public void setFirstFalse() {
		isFirst = false;
	}

	public void sleep() throws InterruptedException {
		sleep(timeSleep);
	}

	public boolean getFirst() {
		return isFirst;
	}

	public boolean getBlocked() {
		return isBlocked;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public int getTimeSleep() {
		return timeSleep;
	}

	public void setTimeSleep(int timeSleep) {
		this.timeSleep = timeSleep;
	}

	public AbstractNode getNodeAtual() {
		return nodeAtual;
	}

	public void setNodeAtual(AbstractNode nodeAtual) {
		this.nodeAtual = nodeAtual;
	}
}
