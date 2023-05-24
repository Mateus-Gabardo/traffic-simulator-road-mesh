package br.udesc.traffic.simulator.road.mesh.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import br.udesc.traffic.simulator.road.mesh.model.road.PieceModel;

public class PieceCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private final Integer SQUARE_SIZE;

	public PieceCellRenderer(Integer squareSize) {
		this.SQUARE_SIZE = squareSize;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.setOpaque(false);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.defineIcon(value, row, column);
		this.defineSize(table, column);
		Color gridColor = new Color(42, 94, 157);
		this.setBorder(BorderFactory.createLineBorder(gridColor, 1));
		return this;
	}

	private void defineIcon(Object value, int row, int column) {
		if (value != null && value instanceof PieceModel) {
			PieceModel newPiece = (PieceModel) value;
			PersonalizaPiece iconePersonalizado = new PersonalizaPiece(newPiece);
			this.setIcon(iconePersonalizado);
		} else {
			this.setIcon(null);
		}
	}

	protected void defineSize(JTable table, int column) {
		if (this.SQUARE_SIZE != null) {
			int columnSize = this.SQUARE_SIZE;
			TableColumnModel tableColumnModel = table.getColumnModel();
			TableColumn columnModel = tableColumnModel.getColumn(column);
			columnModel.setWidth(columnSize);
			columnModel.setMinWidth(columnSize);
			columnModel.setMaxWidth(columnSize);
			columnModel.setPreferredWidth(columnSize);
		}
	}

}
