package br.alexandre.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.alexandre.model.Empresa;

@RequestScoped
public class EmpresaDAO {

	private final Session session;
	
	protected EmpresaDAO(){
		this(null);
	}

	@Inject
	public EmpresaDAO(Session session){
		this.session = session;
	}
	
	public void adicionar(Empresa empresa) {
		this.session.save(empresa);
	}
}
