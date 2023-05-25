package br.udesc.traffic.simulator.road.mesh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.udesc.traffic.simulator.road.mesh.component.TrafficSimulatorTableView;
import br.udesc.traffic.simulator.road.mesh.controller.TrafficSimulatorController;
import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;
import br.udesc.traffic.simulator.road.mesh.observer.ObserverView;

public class TrafficSimulatorView extends JFrame implements ObserverNode {
	
	private static final long serialVersionUID = 1L;
	private TrafficSimulatorController controller;
	private JLabel numeroTrheads;

	public TrafficSimulatorView(){
		super();
		controller = new TrafficSimulatorController(1, this);
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
	    GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    // Labels
	    JLabel lblTituloNumeroThread = new JLabel("n° Threads");
	    lblTituloNumeroThread.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
	    JLabel lblTituloThreadAtual = new JLabel("Threads em funcionamento");
	    lblTituloThreadAtual.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
	    // Buttons
	    JButton btnIniciar = new JButton("INICIAR");
	    btnIniciar.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
	    JButton btnEncerrar = new JButton("ENCERRAR");
	    btnEncerrar.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    btnEncerrar.setEnabled(false);
	    
	    // TextField
	    JTextField txtNumeroThreads = new JTextField();
	    txtNumeroThreads.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
	    // Labels
	    JLabel numeroThreads = new JLabel("0");
	    numeroThreads.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
	    // JPanel panLinhasBotoes
	    JPanel panLinhasBotoes = new JPanel();
	    panLinhasBotoes.setLayout(layout);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.insets = new Insets(0, 10, 0, 10);
	    panLinhasBotoes.add(lblTituloNumeroThread, constraints);
	    
	    constraints.gridx = 1;
	    panLinhasBotoes.add(lblTituloThreadAtual, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.insets = new Insets(10, 10, 0, 10);
	    panLinhasBotoes.add(txtNumeroThreads, constraints);
	    
	    constraints.gridx = 1;
	    panLinhasBotoes.add(numeroThreads, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.insets = new Insets(0, 10, 10, 10);
	    panLinhasBotoes.add(btnIniciar, constraints);
	    
	    constraints.gridx = 1;
	    panLinhasBotoes.add(btnEncerrar, constraints);
	    
	    // JPanel jpTraffic
	    JPanel jpTraffic = new JPanel();
	    jpTraffic.setLayout(layout);
	    jpTraffic.add(board, constraints);
	    
	    // JPanel panLayout
	    JPanel panLayout = new JPanel();
	    panLayout.setLayout(layout);
	    panLayout.setSize(GlobalContants.LARGURA_TELA, GlobalContants.ALTURA_TELA);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    panLayout.add(panLinhasBotoes, constraints);
	    
	    constraints.gridy = 1;
	    panLayout.add(jpTraffic, constraints);
	    
	    // JScrollPane scpScroll
	    JScrollPane scpScroll = new JScrollPane(panLayout);
	    
	    setTitle("Malha rodoviaria");
	    setVisible(true);
	    setSize(GlobalContants.LARGURA_TELA, GlobalContants.ALTURA_TELA);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setContentPane(scpScroll);
	}


	@Override
	public void notifyStartCar(int line, int column) {

	}

	@Override
	public void notifyMoveCar(int pastLine, int pastColumn, int newLine, int newColumn) {

	}

	@Override
	public void notifyEndCar(int line, int column, Car car) {

	}
}
