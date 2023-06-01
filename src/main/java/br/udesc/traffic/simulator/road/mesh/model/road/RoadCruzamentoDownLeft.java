package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoDownLeft extends PieceModel{

	public RoadCruzamentoDownLeft(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-down-left");
	}

	@Override
	public String getPathImageCar() {
		String path = "carDown";
		if(this.getDirection() == GlobalContants.LEFT) {
			path = "carLeft";
		}
		return ImageUtils.createImagePath("/car/" + path);
	}

}
