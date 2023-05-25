package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoUp extends PieceModel{

	public RoadCruzamentoUp(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-up");
	}

	@Override
	public String getPathImageCar() {
		return ImageUtils.createImagePath("/car/carUp");
	}

}
