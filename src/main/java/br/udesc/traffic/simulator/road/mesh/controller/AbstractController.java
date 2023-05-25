package br.udesc.traffic.simulator.road.mesh.controller;

import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public interface AbstractController {
	
	public void addObserver(ObserverNode observer);	
    public void notificarInicioCarro(int linha, int coluna);
    public void notificarMovimentoCarro(int linhaAntiga, int colunaAntiga, int linhaNova, int colunaNova);
    public void notificarFimCarro(int linha, int coluna, Car carro);
}
