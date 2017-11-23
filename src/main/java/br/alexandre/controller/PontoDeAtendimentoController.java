package br.alexandre.controller;

import java.util.List;

import javax.inject.Inject;

import br.alexandre.dao.PontoDeAtendimentoDAO;
import br.alexandre.dao.UnidadeDAO;
import br.alexandre.model.PontoDeAtendimento;
import br.alexandre.model.Unidade;
import br.alexandre.model.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
public class PontoDeAtendimentoController {

	private UnidadeDAO unidadeDAO;
	private PontoDeAtendimentoDAO pontoDeAtendimentoDAO;
	private UsuarioLogado usuarioLogado;
	private Result result;
	
	protected PontoDeAtendimentoController(){
		this(null, null, null, null);
	}
	
	@Inject
	public PontoDeAtendimentoController(UsuarioLogado usuarioLogado, Result result, PontoDeAtendimentoDAO pontoDeAtendimentoDAO, UnidadeDAO unidadeDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.pontoDeAtendimentoDAO = pontoDeAtendimentoDAO;
		this.unidadeDAO = unidadeDAO;
	}

	@Get("/ponto/{idUnidade}/listar")
	public void telaInicial(long idUnidade) {
		if(usuarioLogado.isLogado()) {
			List<PontoDeAtendimento> pontosDeAtendimento = pontoDeAtendimentoDAO.listarPorUnidade(idUnidade);
			Unidade unidade = unidadeDAO.pesquisarPorId(idUnidade);
			
			result.include("pontosDeAtendimento", pontosDeAtendimento);
			result.include("idUnidade", idUnidade);
			result.include("unidade", unidade);
		}
	}
	
	@Get("/ponto/{idUnidade}/adicionar")
	public void adicionar(long idUnidade) {
		result.include("idUnidade", idUnidade);
	}
	
	@Post("/ponto/adicionar")
	public void adicionar(PontoDeAtendimento ponto, long idUnidade) {
		Unidade unidade = unidadeDAO.pesquisarPorId(idUnidade);
		ponto.setUnidade(unidade);
		
		pontoDeAtendimentoDAO.adicionar(ponto);
		
		result.include("mensagem", "Ponto de atendimento cadastrado com sucesso");
		result.redirectTo(PontoDeAtendimentoController.class).telaInicial(idUnidade);
	}
}