package br.alexandre.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.alexandre.model.PontoDeAtendimento;

@RequestScoped
public class PontoDeAtendimentoDAO {

	private final Session session;
	
	protected PontoDeAtendimentoDAO(){
		this(null);
	}

	@Inject
	public PontoDeAtendimentoDAO(Session session){
		this.session = session;
	}
	
	public void adicionar(PontoDeAtendimento pontoDeAtendimento) {
		this.session.save(pontoDeAtendimento);
	}
	
	@SuppressWarnings("unchecked")
	public List<PontoDeAtendimento> listarPorUnidade(long id){
		return this.session.createCriteria(PontoDeAtendimento.class)
				.add(Restrictions.eq("unidade.id", id))
				.list();
	}
}