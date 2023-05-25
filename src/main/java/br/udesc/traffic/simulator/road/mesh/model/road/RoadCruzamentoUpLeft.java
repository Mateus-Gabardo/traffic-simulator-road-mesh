package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoUpLeft extends PieceModel{

	public RoadCruzamentoUpLeft(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-left-up");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carLeft");
	}

}
