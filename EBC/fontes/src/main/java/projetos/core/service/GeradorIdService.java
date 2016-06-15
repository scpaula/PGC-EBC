package br.com.fatec.projetos.core.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class GeradorIdService {
	
	private static GeradorIdService instance;
	private Long nextId = 1L;

	private GeradorIdService() {

	}

	public static GeradorIdService getInstance() {
		if (instance == null) {
			instance = new GeradorIdService();
		}
		return instance;
	}

	public Long nextId() {
		return this.nextId++;
	}
}
