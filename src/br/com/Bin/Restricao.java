package br.com.Bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "restrincao")
public class Restricao {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	Integer id;
	@Column(name = "nome")
	String nome;
	@Column(name = "classe")
	String classePalavra;
	
	
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
	public String getClassePalavra() {
		return classePalavra;
	}
	public void setClassePalavra(String classePalavra) {
		this.classePalavra = classePalavra;
	}
	
	
}
