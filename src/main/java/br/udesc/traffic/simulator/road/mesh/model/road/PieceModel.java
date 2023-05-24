package br.udesc.traffic.simulator.road.mesh.model.road;

import javax.swing.ImageIcon;

import br.udesc.traffic.simulator.road.mesh.controller.TrafficSimulatorController;
import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;
import br.udesc.traffic.simulator.road.mesh.util.ResizedImageIconFactory;

public abstract class PieceModel {
	private int tipo;
	private boolean possuiCar;

	public PieceModel(int tipo) {
		this.tipo = tipo;
		this.possuiCar = true;
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
	
	public ImageIcon getCar(){
		return ResizedImageIconFactory.create(ImageUtils.createImagePath(getPathImageCar()), 40, 40);
	}

	public abstract String getPathImageIcon();
	
	public abstract String getPathImageCar();
	

	private int iconSize(int countLinhas) {
		final int maximo = 550;
		return (int) (maximo / countLinhas);
	}
}
