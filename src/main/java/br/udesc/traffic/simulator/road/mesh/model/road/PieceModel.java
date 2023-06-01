package br.udesc.traffic.simulator.road.mesh.model.road;

import javax.swing.ImageIcon;

import br.udesc.traffic.simulator.road.mesh.controller.TrafficSimulatorController;
import br.udesc.traffic.simulator.road.mesh.util.ResizedImageIconFactory;

public abstract class PieceModel {
	private int tipo;
	private boolean possuiCar;
	private int direction;

	public PieceModel(int tipo) {
		this.tipo = tipo;
		this.possuiCar = false;
		this.direction = 0;
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setPossuiCar(boolean car) {
		possuiCar = car;
	}
	
	public boolean getPossuiCar() {
		return possuiCar;
	}

	protected void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Retorna uma imagem a partir do seu controlador
	 * 
	 * @param controller
	 * @return
	 */
	public ImageIcon getImagem(TrafficSimulatorController controller) {
		int sizeIcon = (int) (this.iconSize(controller.getRowCount()));
		String path = this.getPathImageIcon();
		ImageIcon icon = ResizedImageIconFactory.create((String) path, sizeIcon, sizeIcon);
		return icon;
	}

	/**
	 * Retorna a imagem do objeto a partir de um tamanho definido sem controlador
	 * 
	 * @param rowCount
	 * @return
	 */
	public ImageIcon getImagem(int size) {
		int sizeIcon = (int) (this.iconSize(size));
		String path = this.getPathImageIcon();
		ImageIcon icon = ResizedImageIconFactory.create((String) path, sizeIcon, sizeIcon);
		return icon;
	}
	
	public ImageIcon getCar(int size){
		int sizeIcon = (int) (this.iconSize(size));
		String path = this.getPathImageCar();
		ImageIcon icon = ResizedImageIconFactory.create((String) path, sizeIcon, sizeIcon);
		return icon;
	}

	public abstract String getPathImageIcon();
	
	public abstract String getPathImageCar();
	

	private int iconSize(int countLinhas) {
		final int maximo = 550;
		return (int) (maximo / countLinhas);
	}
}
