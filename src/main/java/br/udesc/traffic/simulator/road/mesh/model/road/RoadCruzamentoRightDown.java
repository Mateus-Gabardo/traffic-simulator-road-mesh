package br.udesc.traffic.simulator.road.mesh.model.road;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
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
		String path = "carRight";
		if(this.getDirection() == GlobalContants.DOWN) {
			path = "carDown";
		}
		return ImageUtils.createImagePath("/car/" + path);
	}

}
