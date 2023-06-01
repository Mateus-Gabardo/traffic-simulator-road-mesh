package br.udesc.traffic.simulator.road.mesh.model.thread;

import java.util.List;
import java.util.Random;

import br.udesc.traffic.simulator.road.mesh.model.node.AbstractNode;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;

public class CarGenerator extends Thread{
	private List<Car> cars;
	private int quantidade;
	
	public CarGenerator(int quantidade, List<Car> cars) {
		this.quantidade = quantidade;
		this.cars = cars;
	}
	
    private void geraCarro(){
        int posicao = new Random().nextInt(MeshRepository.getInstance().getNodeInit().size());
        AbstractNode choose = MeshRepository.getInstance().getNodeInit().get(posicao);
        Car car = new Car(choose);
        cars.add(car);
        car.start();
    }
	
	@Override
	public void run() {
		for(int i= 0; i < quantidade; i++) {
			geraCarro();
		}
		Thread.currentThread().interrupt();
	}
}
