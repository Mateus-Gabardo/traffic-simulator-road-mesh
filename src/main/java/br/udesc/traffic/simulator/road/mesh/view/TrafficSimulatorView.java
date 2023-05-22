package br.udesc.traffic.simulator.road.mesh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TrafficSimulatorView extends JFrame implements View{
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public TrafficSimulatorView(){
		super();
		setProperties();
		addComponents();
	}	
	
	private void setProperties() {
		setTitle("Simulador de Tráfico");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		pack();
	}
	
	private void addComponents() {
		JLabel titleLabel = new JLabel("Bem-vindo!");
        add(titleLabel, BorderLayout.NORTH);
	}	

}
