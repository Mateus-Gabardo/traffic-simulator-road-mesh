package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.util.ImageUtils;

public class RoadCruzamentoUpLeft extends PieceModel{

	public RoadCruzamentoUpLeft(int tipo) {
		super(tipo);
	}
	
	@Override
	public String getPathImageIcon() {
		return ImageUtils.createImagePath("/road/cruzamento-left-up");
	}

	@Override
	public String getPathImageCar() {
		String path = "carLeft";
		if(this.getDirection() == GlobalContants.UP) {
			path = "carUp";
		}
		return ImageUtils.createImagePath("/car/" + path);
	}

}
