package br.udesc.traffic.simulator.road.mesh.component;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import br.udesc.traffic.simulator.road.mesh.model.GlobalContants;
import br.udesc.traffic.simulator.road.mesh.model.road.PieceModel;

public class PersonalizaPiece implements Icon {
	private PieceModel piece;
	private int iconWidth;
	private int iconHeight;
	private ImageIcon pieceIcone;

	public PersonalizaPiece(PieceModel piece) {
		this.piece = piece;
		this.pieceIcone = this.piece.getImagem(40);
		this.iconWidth = this.pieceIcone.getIconWidth();
		this.iconHeight = this.pieceIcone.getIconHeight();
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		ImageIcon pieceImg = this.pieceIcone;
		Image newImagePiece = this.piece.getImagem(4).getImage();
		g.drawImage(newImagePiece, 0, 0, GlobalContants.LARGURA_COLUNA_GRID, GlobalContants.ALTURA_GRID, pieceImg.getImageObserver());
		if(piece.getPossuiCar()) {
			this.paintCar(g);			
		}
	}

	private void paintCar(Graphics g) {
		ImageIcon car = this.piece.getCar(iconHeight);
		this.desenhaImagem(g, car);
	}

	private void desenhaImagem(Graphics g, ImageIcon car) {
		int x = 0;
		int y = 0;
		Image newImageCar = car.getImage();
		g.drawImage(newImageCar, x, y, (car.getIconWidth()-10), (car.getIconHeight()-10), car.getImageObserver());
	}

	@Override
	public int getIconWidth() {
		return this.iconWidth;
	}

	@Override
	public int getIconHeight() {
		return this.iconHeight;
	}
}
