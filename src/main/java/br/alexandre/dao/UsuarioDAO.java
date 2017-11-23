package br.alexandre.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.alexandre.model.Usuario;

@RequestScoped
public class UsuarioDAO {

	private final Session session;
	
	protected UsuarioDAO(){
		this(null);
	}

	@Inject
	public UsuarioDAO(Session session){
		this.session = session;
	}
	
	public void adicionar(Usuario usuario) {
		this.session.save(usuario);
	}
	
	public Usuario pesquisar(String login, String password) {
		Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("password", password))
				.uniqueResult();
		return usuario;
	}
}
