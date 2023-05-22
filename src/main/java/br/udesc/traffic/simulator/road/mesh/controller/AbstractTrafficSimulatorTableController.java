package br.udesc.traffic.simulator.road.mesh.controller;

public interface AbstractTrafficSimulatorTableController {

	public int getRowCount();

	public int getColumnCount();

	public Object getValueAt(int rowIndex, int columnIndex);
}
