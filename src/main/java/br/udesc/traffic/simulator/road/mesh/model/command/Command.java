package br.udesc.traffic.simulator.road.mesh.model.command;

import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public interface Command {

    void execute();
    void undo();
}
