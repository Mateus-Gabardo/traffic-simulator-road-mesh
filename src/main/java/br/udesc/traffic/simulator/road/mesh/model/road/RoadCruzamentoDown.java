package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoDown extends PieceModel{

	public RoadCruzamentoDown(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-down");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carDown");
	}

}
