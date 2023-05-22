package br.udesc.traffic.simulator.road.mesh.model;

import javax.swing.table.AbstractTableModel;
import br.udesc.traffic.simulator.road.mesh.controller.AbstractTrafficSimulatorTableController;

public class TrafficSimulatorTableModel extends AbstractTableModel{
	
	private AbstractTrafficSimulatorTableController tableController;	
	private static final long serialVersionUID = 1L;

	public TrafficSimulatorTableModel(AbstractTrafficSimulatorTableController tableController) {
		super();
		this.tableController = tableController;
	}

	@Override
	public int getRowCount() {
		return tableController.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return tableController.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableController.getValueAt(rowIndex, columnIndex);
	}

}
