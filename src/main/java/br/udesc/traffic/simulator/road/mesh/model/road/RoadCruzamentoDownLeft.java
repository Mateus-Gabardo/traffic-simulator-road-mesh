package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoDownLeft extends PieceModel{

	public RoadCruzamentoDownLeft(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-down-left");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/car-down");
	}

}
