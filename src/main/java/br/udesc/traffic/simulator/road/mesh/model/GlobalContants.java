package br.udesc.traffic.simulator.road.mesh.model;

import java.awt.Toolkit;

public class GlobalContants {
	/// DEFINICOES DA MALHA
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	public static final int CRUZAMENTO_UP= 5;
	public static final int CRUZAMENTO_RIGHT = 6;
	public static final int CRUZAMENTO_DOWN = 7;
	public static final int CRUZAMENTO_LEFT = 8;
	public static final int CRUZAMENTO_UP_RIGHT = 9;
	public static final int CRUZAMENTO_UP_LEFT = 10;
	public static final int CRUZAMENTO_RIGHT_DOWN = 11;
	public static final int CRUZAMENTO_DOWN_LEFT = 12;
	
	///DEFINIÇÕES GERAIS
	public static final int LARGURA_COLUNA_GRID = 35;
	public static final int ALTURA_GRID = 35;

	///DEFINIÇÕES TIPO
	public static final int MONITOR = 1;
	public static final int SEMAPHOR = 2;
	
    public static final int ALTURA_TELA = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.8);
    public static final int LARGURA_TELA = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);
    public static final int LADO_QUADRADO = (int) (ALTURA_TELA*0.053);
    public static final int MARGEM_BOTOES = (int) (LARGURA_TELA * 0.00916);
}
