package br.alexandre.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import br.alexandre.dao.UnidadeDAO;
import br.alexandre.model.Empresa;
import br.alexandre.model.Unidade;
import br.alexandre.model.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
public class UnidadeController {
	
	private UnidadeDAO unidadeDAO;
	private UsuarioLogado usuarioLogado;
	private Result result;
	
	protected UnidadeController(){
		this(null, null, null);
	}
	
	@Inject
	public UnidadeController(UsuarioLogado usuarioLogado, Result result, UnidadeDAO unidadeDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.unidadeDAO = unidadeDAO;
	}

	@Get
	public void telaInicial() {
		if(usuarioLogado.isLogado()) {
			Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
			ArrayList<Unidade> unidades = (ArrayList<Unidade>) unidadeDAO.listarPorEmpresa(empresa.getId());
			result.include("unidades", unidades);
		}
	}
	
	@Get("/unidade/adicionar")
	public void adicionar() {}
	
	@Post
	public void adicionar(Unidade unidade) {
		if(usuarioLogado.isLogado()) {
			Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
			
			unidade.setEmpresa(empresa);
			unidadeDAO.adicionar(unidade);
			result.redirectTo(UnidadeController.class).telaInicial();
		}
	}
}
