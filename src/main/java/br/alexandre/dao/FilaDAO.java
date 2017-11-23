package br.alexandre.dao;

import javax.inject.Inject;

import org.hibernate.Session;

import br.alexandre.model.Fila;

public class FilaDAO {

	private final Session session;
	
	protected FilaDAO(){
		this(null);
	}

	@Inject
	public FilaDAO(Session session){
		this.session = session;
	}
	
	public void adicionar(Fila fila) {
		this.session.save(fila);
	}	
}