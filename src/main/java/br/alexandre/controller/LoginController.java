package br.alexandre.controller;

import javax.inject.Inject;

import br.alexandre.dao.UsuarioDAO;
import br.alexandre.model.Usuario;
import br.alexandre.model.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
public class LoginController {

	private UsuarioDAO usuarioDAO;
	private UsuarioLogado usuarioLogado;
	private Result result;
	
	protected LoginController(){
		this(null, null, null);
	}
	
	@Inject
	public LoginController(UsuarioDAO usuarioDAO, UsuarioLogado usuarioLogado, Result result) {
		this.usuarioDAO = usuarioDAO;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
	}
	
	@Get("/usuario/login")
	public void login() {}
	
	@Post
	public void login(String login, String password) {
		Usuario usuario = usuarioDAO.pesquisar(login, password);
		
		if(usuario != null) {
			usuarioLogado.login(usuario);
			if(usuarioLogado.isLogado()) {
				result.redirectTo(EmpresaController.class).telaInicial();
			}
		}
	}
	
	@Path("/usuario/logout")
	public void logout() {
		usuarioLogado.logout();
		result.redirectTo(LoginController.class).login();
	}
}