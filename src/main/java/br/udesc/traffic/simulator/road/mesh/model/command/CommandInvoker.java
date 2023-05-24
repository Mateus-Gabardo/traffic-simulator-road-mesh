package br.udesc.traffic.simulator.road.mesh.model.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    private List<Command> now = new ArrayList<>();
    private List<Command> all = new ArrayList<>();
    private List<Command> undo = new ArrayList<>();

    public void add(Command comm){
        now.add(comm);
    }

    public void execute() {
        for (Command comm: now) {
            comm.execute();
            all.add(comm);
        }
    }

    public void execute(Command comm) {
        comm.execute();
        all.add(comm);
    }

    public void undo() {
        Command comm = all.remove(all.size()-1);
        comm.undo();
        undo.add(comm);
    }

    public void print() {
        System.out.println("Log :");
        for (Command comm: all) {
            System.out.println(" " + comm);
        }
    }
}
