package br.udesc.traffic.simulator.road.mesh.observer;

public interface ObserverInitialView {
	void ativedInitialButton();

	void updateTxt(String caminho);

	void notifyErrorFile();

	void navigateNextView();
}
