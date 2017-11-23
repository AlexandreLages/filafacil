package br.alexandre.controller;

import javax.inject.Inject;

import br.alexandre.dao.EmpresaDAO;
import br.alexandre.dao.UsuarioDAO;
import br.alexandre.model.Empresa;
import br.alexandre.model.Usuario;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;

@Controller
public class EmpresaController {

	private EmpresaDAO empresaDAO;
	private UsuarioDAO usuarioDAO;
	
	protected EmpresaController() {
		this(null, null);
	}
	
	@Inject
	public EmpresaController(EmpresaDAO empresaDAO, UsuarioDAO usuarioDAO) {
		this.empresaDAO = empresaDAO;
		this.usuarioDAO = usuarioDAO;
	}
	
	@Get("/empresa/adicionar")
	public void adicionar() {}
	
	@Post
	public void adicionar(Empresa empresa, Usuario usuario) {
		usuario.setEmpresa(empresa);
		
		usuarioDAO.adicionar(usuario);
		empresaDAO.adicionar(empresa);
	}

	@Get
	public void telaInicial() {}
}