package br.udesc.traffic.simulator.road.mesh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import br.udesc.traffic.simulator.road.mesh.component.TrafficSimulatorTableView;
import br.udesc.traffic.simulator.road.mesh.controller.TrafficSimulatorController;
import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;

public class TrafficSimulatorView extends JFrame implements ObserverView{
	
	private static final long serialVersionUID = 1L;
	private TrafficSimulatorController controller;

	public TrafficSimulatorView(){
		super();
		controller = new TrafficSimulatorController();
		controller.addObserver(this);
		init();
	}
	
	private void init() {
		setProperties();
		addComponents();
	}
	
	private void setProperties() {
		setTitle("Simulador de Tráfico");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA, GlobalContants.ALTURA_TELA));
		setLayout(new BorderLayout());
		pack();
	}
	
	private void addComponents() {
		TrafficSimulatorTableView board = new TrafficSimulatorTableView(controller);
		setTitle("Simulador de Malha Viária");
		setLayout(new GridBagLayout());
		// DISTANCIA ENTRE OS COMPONENTES
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        GridBagConstraints cons;
        // POSICIONAMENTO DO GRID
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 5;
        cons.gridheight = 5;
        cons.anchor = GridBagConstraints.PAGE_START;
        cons.insets = defaultInsets;
        add(board, cons);
	}

}
