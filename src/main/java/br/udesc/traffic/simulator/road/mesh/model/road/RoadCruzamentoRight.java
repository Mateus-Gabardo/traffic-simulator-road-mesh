package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoRight extends PieceModel{

	public RoadCruzamentoRight(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-right");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carRight");
	}

}
