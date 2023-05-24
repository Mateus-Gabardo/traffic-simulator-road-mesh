package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadRight extends PieceModel {

	public RoadRight(int tipo) {
		super(tipo);
	}

	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/right");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/car-right");
	}

}
