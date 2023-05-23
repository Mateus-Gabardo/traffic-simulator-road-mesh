package br.udesc.traffic.simulator.road.mesh.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.udesc.traffic.simulator.road.mesh.controller.InitialController;
import br.udesc.traffic.simulator.road.mesh.observer.ObserverInitialView;

public class InitialView extends JFrame implements ObserverInitialView {
	private static final long serialVersionUID = 1L;
	private InitialController controller;
	private JFileChooser jfcArquivo;
    private JTextField txtCaminho;
    private JButton btnProcurar;
    private JButton btnIniciar;
    
	public InitialView() {
		this.controller = new InitialController(this);
		defineProperties();
		initializeAtions();
	}
	
	private void defineProperties() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel lblTitulo = new JLabel("Escolha o arquivo de texto da malha rodoviaria");

        txtCaminho = new JTextField();
        txtCaminho.setText("Selecione um arquivo de malha rodoviaria");
        txtCaminho.setPreferredSize(new Dimension(310, 26));
        txtCaminho.setMinimumSize(new Dimension(310, 26));
        txtCaminho.setEnabled(false);

        btnProcurar = new JButton("Procurar");
        btnProcurar.setPreferredSize(new Dimension(90, 26));
        btnProcurar.setMinimumSize(new Dimension(90, 26));

        JPanel panLinhaBusca = new JPanel();
        panLinhaBusca.setLayout(layout);
        panLinhaBusca.setSize(400, 50);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        panLinhaBusca.add(txtCaminho, constraints);
        constraints.gridx = 1;
        constraints.insets = new Insets(0, 10, 0, 0);
        panLinhaBusca.add(btnProcurar, constraints);
        
        // Definimos os botões
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(90, 26));
        btnIniciar.setMinimumSize(new Dimension(90, 26));
        btnIniciar.setEnabled(false);
        
        // Definimos o arquivo de busca 
        jfcArquivo = new JFileChooser();
        jfcArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JPanel panLayout = new JPanel();
        panLayout.setLayout(layout);
        panLayout.setSize(450, 150);
        constraints.gridx = 0;
        panLayout.add(lblTitulo, constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panLayout.add(panLinhaBusca, constraints);
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 320, 0, 0);
        panLayout.add(btnIniciar, constraints);
        
        // Setamos as propriedades padrões da tela
        setTitle("Escolha a malha rodoviaria");
        setVisible(true);
        setSize(450, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panLayout);
	}
	
	private void initializeAtions() {
		btnProcurar.addActionListener(click -> {
            int i= jfcArquivo.showSaveDialog(null);
            if (i==1){
                txtCaminho.setText("Selecione um arquivo de malha rodoviaria");
                btnIniciar.setEnabled(false);
            } else {
                this.controller.updateRoadMesh(jfcArquivo.getSelectedFile());
            }
        });
        
        btnIniciar.addActionListener(click -> {
        	this.controller.navigateNextView();
        });
	}

	@Override
	public void ativedInitialButton() {
		 btnIniciar.setEnabled(true);
	}

	@Override
	public void updateTxt(String caminho) {
		txtCaminho.setText(caminho);
		
	}

	@Override
	public void notifyErrorFile() {
		JOptionPane.showMessageDialog(null, "O arquivo selecionado não possui o padrão adequado de uma malha rodoviaria",
                "Erro no arquivo", JOptionPane.ERROR_MESSAGE);
        btnIniciar.setEnabled(false);
	}

	@Override
	public void navigateNextView() {
		TrafficSimulatorView view = new TrafficSimulatorView();
		view.setVisible(true);
		this.dispose();		
	}

}
