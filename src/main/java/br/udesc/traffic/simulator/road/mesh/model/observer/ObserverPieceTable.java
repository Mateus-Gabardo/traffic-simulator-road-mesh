package br.udesc.traffic.simulator.road.mesh.model.observer;

import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public interface ObserverPieceTable {

    void notifyStartCar(int line, int column);
    void notifyMoveCar(int pastLine, int pastColumn, int newLine, int newColumn);
    void notifyEndCar(int line, int column, Car car);
}
