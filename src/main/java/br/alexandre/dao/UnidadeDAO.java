package br.alexandre.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.alexandre.model.Unidade;

@RequestScoped
public class UnidadeDAO {

private final Session session;
	
	protected UnidadeDAO(){
		this(null);
	}

	@Inject
	public UnidadeDAO(Session session){
		this.session = session;
	}
	
	public void adicionar(Unidade unidade) {
		this.session.save(unidade);
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> listarPorEmpresa(long id){
		return this.session.createCriteria(Unidade.class)
				.add(Restrictions.eq("empresa.id", id))
				.list();
	}
	
	public Unidade pesquisarPorId(long id) {
		return (Unidade) session.createCriteria(Unidade.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
}