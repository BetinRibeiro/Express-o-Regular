package br.com.Bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artigo")
public class ArtigoLei {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	@Column(name = "nome",nullable=false, length=10000)
	private String nome;
	@Column(name = "conteudo",nullable=false, length=10000)
	private String conteudo;
	
	private String lei;
	
	private float prioridade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getLei() {
		return lei;
	}

	public void setLei(String lei) {
		this.lei = lei;
	}

	public float getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(float prioridade) {
		this.prioridade = prioridade;
	}
	
	
	
}
