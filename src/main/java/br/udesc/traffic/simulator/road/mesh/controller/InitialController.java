package br.udesc.traffic.simulator.road.mesh.controller;

import java.io.File;

import br.udesc.traffic.simulator.road.mesh.observer.ObserverInitialView;
import br.udesc.traffic.simulator.road.mesh.singleton.MeshRepository;
import br.udesc.traffic.simulator.road.mesh.util.ReadFileMesh;

public class InitialController {
	private ObserverInitialView observer;
	private int[][] malhaViaria;

	public InitialController(ObserverInitialView observer) {
		super();
		this.observer = observer;
	}

	public void updateRoadMesh(File arquivo) {
		try {
			malhaViaria = ReadFileMesh.gerarRoadMesh(arquivo);
			observer.ativedInitialButton();
		} catch (Exception e) {
			observer.notifyErrorFile();
		}
	}

	public void navigateNextView() {
		MeshRepository.getInstance().setRoadMesh(malhaViaria);
		observer.navigateNextView();
	}
}
