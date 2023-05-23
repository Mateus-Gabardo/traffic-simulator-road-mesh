package br.udesc.traffic.simulator.road.mesh.component;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JTable;

import br.udesc.traffic.simulator.road.mesh.controller.AbstractTrafficSimulatorTableController;
import br.udesc.traffic.simulator.road.mesh.model.TrafficSimulatorTableModel;

public class TrafficSimulatorTableView extends JTable{
	private static final long serialVersionUID = 1L;
	private AbstractTrafficSimulatorTableController trafficController;
	private TrafficSimulatorTableModel model;
	
    private void init(){
        this.defineProperties();
        this.iniciaComponentes();
        this.addComponentes();
     }

     private void defineProperties() {
         this.setOpaque(false);
         this.setBackground(new Color (92, 142, 203));
         Color gridColor = new Color(42, 94, 157);
         this.setBorder(BorderFactory.createLineBorder(gridColor));
         this.setGridColor(gridColor);
     }
     
     private void iniciaComponentes(){
        this.model = new TrafficSimulatorTableModel(this.trafficController);
        //this.celula = new DefaultTablePieceCellRenderer(this.calculateTamanhoEsquadro(), this.gameController);
     }
     
     private void addComponentes(){
         this.setModel(this.model);
         //this.setDefaultRenderer(Object.class, this.celula);
     }
     
     @Override
     protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRect(0, 0, getWidth(), getHeight());
         super.paintComponent(g);
     }
     
     public void refresh(){
         this.init();
     }

}
