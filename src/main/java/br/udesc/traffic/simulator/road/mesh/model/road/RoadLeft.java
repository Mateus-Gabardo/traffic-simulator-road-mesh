package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadLeft extends PieceModel{

	public RoadLeft(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/left");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carLeft");
	}

}
