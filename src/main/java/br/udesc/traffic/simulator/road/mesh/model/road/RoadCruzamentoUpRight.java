package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoUpRight extends PieceModel{

	public RoadCruzamentoUpRight(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-up-right");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/car-up");
	}

}
