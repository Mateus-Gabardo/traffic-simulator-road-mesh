package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadUp extends PieceModel {

	public RoadUp(int tipo) {
		super(tipo);
	}

	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/up");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carUp");
	}

}
