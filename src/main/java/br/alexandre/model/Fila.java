package br.alexandre.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Fila implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(mappedBy="filas")
	private List<PontoDeAtendimento> pontosDeAtendimento = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PontoDeAtendimento> getPontosDeAtendimento() {
		return pontosDeAtendimento;
	}

	public void setPontosDeAtendimento(List<PontoDeAtendimento> pontosDeAtendimento) {
		this.pontosDeAtendimento = pontosDeAtendimento;
	}
}