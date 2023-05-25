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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import br.udesc.traffic.simulator.road.mesh.component.TrafficSimulatorTableView;
import br.udesc.traffic.simulator.road.mesh.controller.TrafficSimulatorController;
import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.observer.ObserverNode;
import br.udesc.traffic.simulator.road.mesh.model.road.PieceModel;
import br.udesc.traffic.simulator.road.mesh.model.thread.Car;

public class TrafficSimulatorView extends JFrame implements ObserverNode {
	
	private static final long serialVersionUID = 1L;
	private TrafficSimulatorController controller;
	private JLabel lblNumeroThreadAtual;
	private TrafficSimulatorTableView board;

	public TrafficSimulatorView(int type){
		super();
		controller = new TrafficSimulatorController(type);
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
	    board = new TrafficSimulatorTableView(controller);
	    
	    GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    // Labels
	    JLabel lblTituloNumeroThread = new JLabel("N° Threads");
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
        lblNumeroThreadAtual = new JLabel();
        lblNumeroThreadAtual.setText("0");
        lblNumeroThreadAtual.setPreferredSize(new Dimension(GlobalContants.LARGURA_TELA/6, GlobalContants.LARGURA_COLUNA_GRID));
	    
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
        
        constraints.gridx = 3;
	    constraints.gridx = 1;
	    panLinhasBotoes.add(lblNumeroThreadAtual, constraints);
	    
	    constraints.insets = new Insets(0, 0, GlobalContants.MARGEM_BOTOES, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panLinhasBotoes.add(btnIniciar, constraints);
        constraints.insets = new Insets(0, GlobalContants.MARGEM_BOTOES, GlobalContants.MARGEM_BOTOES, 0);
        constraints.gridx = 1;
        panLinhasBotoes.add(btnEncerrar, constraints);
        constraints.gridx = 2;
	    
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
	    
	    btnIniciar.addActionListener(e -> {
            controller.onIniciar(txtNumeroThreads.getText().toString());
            btnEncerrar.setEnabled(true);
            btnIniciar.setEnabled(false);
        });
        btnEncerrar.addActionListener(e -> {
            controller.onEncerrarCarros();
            btnIniciar.setEnabled(true);
            btnEncerrar.setEnabled(false);
        });
	}
	
    public synchronized void aumentarlThread() {
        lblNumeroThreadAtual.setText(String.valueOf(Integer.parseInt(lblNumeroThreadAtual.getText())+1));
    }
    
    public synchronized void diminuirThread() {
        lblNumeroThreadAtual.setText(String.valueOf(Math.max(Integer.parseInt(lblNumeroThreadAtual.getText()) - 1, 0)));
    }


	@Override
	public void notifyStartCar(int line, int column) {
		TableModel model = board.getModel();
		PieceModel pieceAtual = (PieceModel) model.getValueAt(line, column);		
		pieceAtual.setPossuiCar(true);		
		model.setValueAt(pieceAtual, line, column);
		board.repaint();
		aumentarlThread();
	}

	@Override
	public void notifyMoveCar(int pastLine, int pastColumn, int newLine, int newColumn) {
		TableModel model = board.getModel();
		PieceModel pieceAtual = (PieceModel) model.getValueAt(pastLine, pastColumn);
		PieceModel pieceNext = (PieceModel) model.getValueAt(newLine, newColumn);
		pieceAtual.setPossuiCar(false);
		pieceNext.setPossuiCar(true);
		model.setValueAt(pieceAtual, pastLine, pastColumn);
		model.setValueAt(pieceNext, newLine, newColumn);
		board.repaint();
	}

	@Override
	public void notifyEndCar(int line, int column, Car car) {
		TableModel model = board.getModel();
		PieceModel pieceAtual = (PieceModel) model.getValueAt(line, column);		
		pieceAtual.setPossuiCar(false);		
		model.setValueAt(pieceAtual, line, column);
		board.repaint();
		diminuirThread();
	}
}
