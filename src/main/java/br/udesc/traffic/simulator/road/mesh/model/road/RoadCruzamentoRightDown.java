package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoRightDown extends PieceModel{

	public RoadCruzamentoRightDown(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-right-down");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carRight");
	}

}
