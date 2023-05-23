package br.udesc.traffic.simulator.road.mesh.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFileMesh {

	public static int[][] gerarRoadMesh(File arquivo) throws Exception {
		List<String> linhasArquivo = Files.readAllLines(Path.of(arquivo.getPath()));

		int linhasMalha = Integer.parseInt(linhasArquivo.get(0));
		int colunasMalha = Integer.parseInt(linhasArquivo.get(1));

		int[][] malhaRodoviaria = new int[linhasMalha][colunasMalha];

		linhasArquivo = linhasArquivo.subList(2, linhasArquivo.size());

		for (int linha = 0; linha < linhasArquivo.size(); linha++) {
			String[] colunas = linhasArquivo.get(linha).split("\t");
			for (int coluna = 0; coluna < colunas.length; coluna++) {
				malhaRodoviaria[linha][coluna] = Integer.parseInt(colunas[coluna]);
			}
		}

		return malhaRodoviaria;
	}
}
