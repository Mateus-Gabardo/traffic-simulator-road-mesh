package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadDown extends PieceModel{

	public RoadDown(int tipo) {
		super(tipo);
	}

	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/down");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/down");
	}

}
