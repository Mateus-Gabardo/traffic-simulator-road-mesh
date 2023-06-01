package br.udesc.traffic.simulator.road.mesh.model.node;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class NodeMonitor extends AbstractNode {

    private ReentrantLock recurse = new ReentrantLock();

    public NodeMonitor(int x, int y, int type, ObserverNode observer) {
        super(x, y, type, observer, false);
    }

    @Override
    public synchronized void moveCar(Car car) throws InterruptedException {
    	AbstractNode nextNode = null;
		AbstractNode currentNode = car.getNodeAtual();
		AbstractNode firstNode = currentNode;
		List<AbstractNode> nodesCross = new ArrayList<>();

		try{
			nextNode = getNextNode(car);
			if(nextNode == null) {
    			car.setBlocked(true);
    			getObserver().notifyEndCar(getLine(), getColumn(), car);
    			this.release();
			} else if (!nextNode.getIsCross()) {
				moveOne(car, nextNode);
			} else {
				boolean find = false;
				boolean isOK = true;
				
				if (nextNode.tryNext()) {
					nodesCross.add(nextNode);
					currentNode = nextNode;
					while (!find) {
						nextNode = getNextNodeSimple(currentNode);
						if (nextNode.getIsCross()){
							if (nextNode.tryNext()) {
								nodesCross.add(nextNode);
								currentNode = nextNode;
							}else {
								isOK = false;
							}
						} else {
							if (nextNode.tryNext()) {
								nodesCross.add(nextNode);
							}else {
								isOK = false;
							}
							find = true;
						}
					}
				} else {
					isOK = false;
				}
				
				if (isOK) {
					moveCross(car, firstNode, nodesCross);
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
    
    private void moveCross(Car car, AbstractNode firstNode, List<AbstractNode> nodesCross) throws InterruptedException {
		for (AbstractNode node : nodesCross) {
			car.setNodeAtual(node);
			firstNode.getObserver().notifyMoveCar(firstNode.getLine(), firstNode.getColumn(), node.getLine(), node.getColumn());
			firstNode.release();
			firstNode = node;
			car.sleep();
		}
	}

	private void moveOne(Car car, AbstractNode nextNode) throws InterruptedException {
		if (nextNode.tryNext()) {
			car.setNodeAtual(nextNode);
			this.getObserver().notifyMoveCar(this.getLine(), this.getColumn(), nextNode.getLine(), nextNode.getColumn());
			this.release();
			car.sleep();
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
	
	public AbstractNode getNextNodeSimple(AbstractNode initialNode) {
        AbstractNode currentNode = initialNode;
        AbstractNode nextNode = null;

        AbstractNode[] directions = {
                currentNode.getMoveLeft(),
                currentNode.getMoveDown(),
                currentNode.getMoveRight(),
                currentNode.getMoveUp()
        };
        
        Random random = new Random();
        
        while (nextNode == null) {
        	 int randomIndex = random.nextInt(directions.length);
             nextNode = directions[randomIndex];
        }

        return nextNode;
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
